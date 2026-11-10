param(
    [ValidateRange(1024, 65535)][int]$Port = 4000,
    [switch]$NoBrowser
)

# A loopback-only static server. Works with Windows PowerShell 5.1 and PowerShell 7.
# No Ruby, Node, package installation, administrator rights, or deployment needed.
$ErrorActionPreference = 'Stop'
$siteRoot = [IO.Path]::GetFullPath($PSScriptRoot)
$listener = [Net.Sockets.TcpListener]::new([Net.IPAddress]::Loopback, $Port)
$utf8 = [Text.UTF8Encoding]::new($false)
$mimeTypes = @{ '.html' = 'text/html; charset=utf-8'; '.css' = 'text/css; charset=utf-8'; '.js' = 'text/javascript; charset=utf-8'; '.svg' = 'image/svg+xml'; '.png' = 'image/png'; '.jpg' = 'image/jpeg'; '.ico' = 'image/x-icon'; '.pdf' = 'application/pdf' }

function Send-Response($Stream, [int]$Status, [string]$Reason, [string]$ContentType, [byte[]]$Body, [bool]$HeadOnly, [string]$Extra = '') {
    $header = "HTTP/1.1 $Status $Reason`r`nContent-Type: $ContentType`r`nContent-Length: $($Body.Length)`r`nCache-Control: no-store`r`nX-Content-Type-Options: nosniff`r`nConnection: close`r`n$Extra`r`n"
    $headerBytes = [Text.Encoding]::ASCII.GetBytes($header)
    $Stream.Write($headerBytes, 0, $headerBytes.Length)
    if (-not $HeadOnly) { $Stream.Write($Body, 0, $Body.Length) }
}

try {
    $listener.Start()
    $previewUrl = "http://localhost:$Port/"
    Write-Host "`nPortfolio preview: $previewUrl" -ForegroundColor Green
    Write-Host "Edit a file, save, then refresh your browser. Press Ctrl+C to stop.`n"
    if (-not $NoBrowser) { Start-Process $previewUrl }
    while ($true) {
        # Polling permits Ctrl+C even while no browser requests are arriving.
        if (-not $listener.Pending()) { Start-Sleep -Milliseconds 75; continue }
        $client = $listener.AcceptTcpClient()
        try {
            $client.ReceiveTimeout = 3000
            $client.SendTimeout = 3000
            $stream = $client.GetStream()
            $reader = [IO.StreamReader]::new($stream, [Text.Encoding]::ASCII, $false, 4096, $true)
            $request = $reader.ReadLine()
            if (-not $request) { continue }
            $parts = $request.Split(' ')
            $headOnly = $parts[0] -eq 'HEAD'
            if ($parts[0] -notin @('GET', 'HEAD')) {
                Send-Response $stream 405 'Method Not Allowed' 'text/plain' ($utf8.GetBytes('Use GET or HEAD.')) $headOnly "Allow: GET, HEAD`r`n"
                continue
            }
            $path = [Uri]::UnescapeDataString(($parts[1] -split '\?')[0]).Replace('\', '/')
            # Also support the repository prefix so GitHub Pages paths can be checked locally.
            if ($path -eq '/brunogdzz') { $path = '/brunogdzz/' }
            if ($path.StartsWith('/brunogdzz/')) { $path = $path.Substring(10) }
            $relative = $path.TrimStart('/')
            $candidate = [IO.Path]::GetFullPath((Join-Path $siteRoot $relative))
            $inside = $candidate.StartsWith($siteRoot + [IO.Path]::DirectorySeparatorChar, [StringComparison]::OrdinalIgnoreCase) -or $candidate -eq $siteRoot
            # Only serve public site files; repository internals and old builds stay private.
            $publicPath = $relative -match '^(index\.html|404\.html|style\.css|script\.js|resume\.pdf|bxg7293@mavs\.uta\.edu-9980c5d4\.jpg|assets/[^.].*|projects/[^.].*)?$'
            $hiddenSegment = $relative -match '(^|/)[._]' -or $relative.Contains(':')
            if (-not $inside -or -not $publicPath -or $hiddenSegment) {
                Send-Response $stream 404 'Not Found' 'text/plain' ($utf8.GetBytes('Not found.')) $headOnly
                continue
            }
            if ([IO.Directory]::Exists($candidate)) {
                if (-not $path.EndsWith('/')) {
                    Send-Response $stream 301 'Moved Permanently' 'text/plain' ([byte[]]@()) $headOnly "Location: $path/`r`n"
                    continue
                }
                $candidate = Join-Path $candidate 'index.html'
            }
            if (-not [IO.File]::Exists($candidate)) {
                $missingPage = Join-Path $siteRoot '404.html'
                if ([IO.File]::Exists($missingPage)) { $body = [IO.File]::ReadAllBytes($missingPage) }
                else { $body = $utf8.GetBytes('Not found.') }
                Send-Response $stream 404 'Not Found' 'text/html; charset=utf-8' $body $headOnly
                continue
            }
            $extension = [IO.Path]::GetExtension($candidate).ToLowerInvariant()
            if (-not $mimeTypes.ContainsKey($extension)) {
                Send-Response $stream 404 'Not Found' 'text/plain' ($utf8.GetBytes('Not found.')) $headOnly
                continue
            }
            Send-Response $stream 200 'OK' $mimeTypes[$extension] ([IO.File]::ReadAllBytes($candidate)) $headOnly
        }
        catch { Write-Verbose $_.Exception.Message }
        finally { $client.Dispose() }
    }
}
finally { $listener.Stop() }
