# Bruno De Souza — Portfolio

A personal portfolio featuring Android, Java, and web projects, with individual case studies, responsive layouts, light and dark themes, and a small rhythm game.

## Preview locally — no push required

Open PowerShell in this folder and run:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\preview.ps1
```

The script opens **http://localhost:4000/** in your default browser. Keep the terminal running. Edit a file, save it, and refresh the browser to see the change. Press **Ctrl+C** in the terminal when you are finished.

The execution-policy option applies only to this process; it does not change your machine's policy. The server listens only on your computer's loopback address. It does not publish the site or contact GitHub.

If port 4000 is already in use:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\preview.ps1 -Port 4001
```

Use `-NoBrowser` if you want to open the preview URL yourself. No administrator access, Ruby, Bundler, Node, or dependency installation is needed. Use the server for previewing so project directory links resolve correctly.

## Files to edit

| File | Purpose |
| --- | --- |
| `index.html` | Homepage content, project cards, education, skills, and contact links |
| `style.css` | Shared layout, colors, themes, responsive styles, and game appearance |
| `script.js` | Theme preference, project filters, mobile navigation, email copying, and active sections |
| `projects/*/index.html` | Individual project case studies |
| `assets/game/game.js` | Canvas rhythm game, keyboard and touch controls, optional audio, and local best score |
| `assets/images/` | Project images, optimized portrait, and SVG monogram/favicon |
| `resume.pdf` | Downloadable résumé |
| `404.html` | Self-contained missing-page screen |
| `preview.ps1` | Windows local preview server |

The content is ordinary HTML and works without JavaScript. JavaScript enhances filtering, navigation, theme switching, copying the email address, and the game. The expanded coursework uses the browser's native details element.

## Hosting and dependencies

The site is plain **HTML, CSS, and JavaScript**. GitHub Pages can serve the files directly; `.nojekyll` disables Jekyll processing. There is no build step or package installation, and no external fonts, analytics, or runtime services.

Relative links support both the domain root and the existing `/brunogdzz/` repository path. The preview server supports that prefix too: `http://localhost:4000/brunogdzz/`.

This replaces the older Jekyll version. The unused Gemfiles, theme configuration, layouts, and GitHub Skills tutorial workflows have been retired. `PersonalProjects2025/` contains the original project source and is independent of the portfolio frontend. The existing `_site/` folder is legacy generated output and is no longer used; it is excluded from the local preview. Local preview artifacts are ignored under `.preview/`.

Previewing and editing never push changes. Review the site locally before committing or publishing.

## Interaction notes

- Theme and game best-score preferences are stored locally in the visitor's browser. The site continues to work when storage is unavailable.
- The rhythm game starts only when requested. Use **A / S / D / F**, tap the canvas, or press the four lane buttons. Sound is off by default. Closing the dialog or switching browser tabs pauses the round.
- Keyboard focus stays within the game dialog; Escape closes it and returns focus to the launcher.
- The copy-email button uses the Clipboard API on localhost or HTTPS, with a helpful message when copying is unavailable.
- Reduced-motion preferences disable smooth scrolling and decorative transitions.

## Contact

[GitHub](https://github.com/brunogdzz) · [LinkedIn](https://www.linkedin.com/in/bruno-garbuio-de-souza-48610524a/) · [Email](mailto:23bdesouza@gmail.com)
