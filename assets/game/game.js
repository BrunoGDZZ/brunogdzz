(() => {
  'use strict';
  const launchers = [...document.querySelectorAll('[data-open-game]')];
  if (!launchers.length) return;
  const dialog = document.createElement('dialog');
  dialog.className = 'game-dialog';
  dialog.setAttribute('aria-labelledby', 'game-title');
  dialog.setAttribute('aria-describedby', 'game-help');
  dialog.innerHTML = `<div class="game-inner">
    <div class="game-heading"><div><h2 id="game-title">Bruno’s tiny rhythm game</h2><p>30 SECONDS. FOUR LANES. FIND YOUR RHYTHM.</p></div><button type="button" class="icon-button game-close" aria-label="Close game">×</button></div>
    <div class="game-stats"><span class="game-score">Score: 0</span><span class="game-combo">Combo: 0×</span><span class="game-best">Best: 0</span><span class="game-time">30s</span></div>
    <canvas class="game-board" width="600" height="320" role="img" aria-label="Four rhythm lanes. Hit notes at the horizontal target line using A, S, D, F or the buttons below."></canvas>
    <div class="game-lanes" role="group" aria-label="Play a lane"><button type="button" data-lane="0" aria-label="Hit lane A">A</button><button type="button" data-lane="1" aria-label="Hit lane S">S</button><button type="button" data-lane="2" aria-label="Hit lane D">D</button><button type="button" data-lane="3" aria-label="Hit lane F">F</button></div>
    <div class="game-controls"><button type="button" class="button button-primary game-start">Start playing ↗</button><button type="button" class="game-sound" aria-pressed="false">Sound: off</button></div>
    <p class="game-help" id="game-help">Press A / S / D / F, tap a lane, or use the buttons as notes reach the line. Miss a note and your combo resets. Escape closes the game.</p>
    <p class="game-message" role="status" aria-live="polite">Ready when you are.</p></div>`;
  document.body.append(dialog);
  const $ = selector => dialog.querySelector(selector);
  const canvas = $('.game-board');
  const ctx = canvas.getContext('2d');
  if (!ctx) { launchers.forEach(button => { button.hidden = true; }); dialog.remove(); return; }
  const keys = ['A', 'S', 'D', 'F'];
  const colors = ['#c4f179', '#8bc9cb', '#cab0e2', '#e3b285'];
  const target = 260;
  let notes = [], score = 0, combo = 0, best = 0;
  let elapsed = 0, previous = 0, spawnClock = 0, frame = 0;
  let running = false, paused = false, sound = false, audio;
  let lastLauncher;
  let flashes = [0, 0, 0, 0];
  try { best = Math.max(0, Number(localStorage.getItem('brunoGuitarHighScore')) || 0); } catch (_) {}
  const update = () => {
    $('.game-score').textContent = `Score: ${score}`;
    $('.game-combo').textContent = `Combo: ${combo}×`;
    $('.game-best').textContent = `Best: ${best}`;
    $('.game-time').textContent = `${Math.max(0, Math.ceil(30 - elapsed))}s`;
  };
  const storeBest = () => {
    if (score > best) {
      best = score;
      try { localStorage.setItem('brunoGuitarHighScore', String(best)); } catch (_) {}
    }
  };
  const draw = () => {
    ctx.fillStyle = '#111810'; ctx.fillRect(0, 0, 600, 320);
    for (let lane = 0; lane < 4; lane++) {
      ctx.fillStyle = lane % 2 ? '#192018' : '#151c14'; ctx.fillRect(lane * 150, 0, 150, 320);
      if (flashes[lane] > 0) { ctx.fillStyle = '#c4f11922'; ctx.fillRect(lane * 150, 0, 150, 320); }
      ctx.strokeStyle = '#303d2c'; ctx.beginPath(); ctx.moveTo(lane * 150, 0); ctx.lineTo(lane * 150, 320); ctx.stroke();
      ctx.font = 'bold 16px monospace'; ctx.textAlign = 'center'; ctx.fillStyle = colors[lane]; ctx.fillText(keys[lane], lane * 150 + 75, 302);
      ctx.strokeStyle = colors[lane]; ctx.lineWidth = 2; ctx.beginPath(); ctx.arc(lane * 150 + 75, target, 18, 0, Math.PI * 2); ctx.stroke();
    }
    ctx.strokeStyle = '#8f9d8266'; ctx.lineWidth = 1; ctx.beginPath(); ctx.moveTo(0, target); ctx.lineTo(600, target); ctx.stroke();
    notes.forEach(note => {
      ctx.fillStyle = colors[note.lane]; ctx.beginPath(); ctx.arc(note.lane * 150 + 75, note.y, 13, 0, Math.PI * 2); ctx.fill();
      ctx.fillStyle = '#142010'; ctx.font = 'bold 12px monospace'; ctx.fillText(keys[note.lane], note.lane * 150 + 75, note.y + 4);
    });
    if (!running && !paused && elapsed === 0) { ctx.fillStyle = '#e3ebd8'; ctx.font = '15px monospace'; ctx.fillText('Press Start to find your rhythm', 300, 135); }
  };
  const tone = lane => {
    if (!sound) return;
    try {
      audio ||= new (window.AudioContext || window.webkitAudioContext)();
      if (audio.state === 'suspended') audio.resume().catch(() => {});
      const oscillator = audio.createOscillator(), gain = audio.createGain();
      oscillator.frequency.value = [261.63, 329.63, 392, 523.25][lane];
      oscillator.type = 'triangle'; gain.gain.setValueAtTime(.08, audio.currentTime);
      gain.gain.exponentialRampToValueAtTime(.001, audio.currentTime + .12);
      oscillator.connect(gain); gain.connect(audio.destination); oscillator.start(); oscillator.stop(audio.currentTime + .12);
      oscillator.onended = () => { oscillator.disconnect(); gain.disconnect(); };
    } catch (_) { /* Audio is optional. */ }
  };
  function hit(lane) {
    if (!running || !dialog.open) return;
    flashes[lane] = .12;
    const candidates = notes.filter(note => note.lane === lane && Math.abs(note.y - target) < 36);
    candidates.sort((a, b) => Math.abs(a.y - target) - Math.abs(b.y - target));
    if (candidates.length) {
      combo++; score += 100 + Math.min(combo, 20) * 5;
      notes.splice(notes.indexOf(candidates[0]), 1); tone(lane); storeBest();
    } else combo = 0;
    update();
  }
  function loop(now) {
    if (!running) return;
    const dt = Math.min((now - previous) / 1000, .1); previous = now; elapsed += dt; spawnClock += dt;
    if (spawnClock >= .48 && elapsed < 28.5) { notes.push({ lane: Math.floor(Math.random() * 4), y: -15 }); spawnClock = 0; }
    notes.forEach(note => { note.y += dt * 205; });
    notes = notes.filter(note => { if (note.y > target + 36) { combo = 0; return false; } return true; });
    flashes = flashes.map(flash => Math.max(0, flash - dt));
    if (elapsed >= 30) {
      running = false; elapsed = 30; storeBest();
      $('.game-message').textContent = `Nice session! You scored ${score} points. Play again to beat your best.`;
      $('.game-start').textContent = 'Play again ↗';
    }
    draw(); update();
    if (running) frame = requestAnimationFrame(loop);
  }
  function start() {
    cancelAnimationFrame(frame);
    if (!paused) { notes = []; score = 0; combo = 0; elapsed = 0; spawnClock = 0; flashes = [0, 0, 0, 0]; }
    paused = false; running = true; previous = performance.now();
    $('.game-message').textContent = 'Find the line. Keep the combo going.';
    $('.game-start').textContent = 'Restart ↻'; update();
    frame = requestAnimationFrame(loop);
  }
  function pause() {
    if (!running) return;
    running = false; paused = true; cancelAnimationFrame(frame);
    $('.game-start').textContent = 'Resume ↗';
    $('.game-message').textContent = 'Paused while you were away. Resume when you’re ready.';
  }
  launchers.forEach(button => button.addEventListener('click', () => {
    lastLauncher = button;
    dialog.showModal(); document.body.classList.add('modal-open'); update(); draw(); $('.game-start').focus();
  }));
  $('.game-start').addEventListener('click', start);
  $('.game-close').addEventListener('click', () => dialog.close());
  dialog.addEventListener('close', () => {
    pause(); document.body.classList.remove('modal-open'); lastLauncher?.focus();
    if (audio?.state === 'running') audio.suspend().catch(() => {});
  });
  dialog.addEventListener('click', event => {
    const rect = dialog.getBoundingClientRect();
    if (event.target === dialog && (event.clientX < rect.left || event.clientX > rect.right || event.clientY < rect.top || event.clientY > rect.bottom)) dialog.close();
  });
  dialog.addEventListener('keydown', event => {
    const lane = keys.indexOf(event.key.toUpperCase());
    if (lane >= 0 && running && !event.ctrlKey && !event.metaKey && !event.altKey) { event.preventDefault(); if (!event.repeat) hit(lane); }
  });
  dialog.querySelectorAll('[data-lane]').forEach(button => button.addEventListener('click', () => hit(Number(button.dataset.lane))));
  canvas.addEventListener('pointerdown', event => {
    event.preventDefault(); const rect = canvas.getBoundingClientRect();
    hit(Math.max(0, Math.min(3, Math.floor((event.clientX - rect.left) / rect.width * 4))));
  });
  $('.game-sound').addEventListener('click', () => {
    sound = !sound; $('.game-sound').textContent = `Sound: ${sound ? 'on' : 'off'}`; $('.game-sound').setAttribute('aria-pressed', String(sound));
    if (sound) tone(0);
  });
  document.addEventListener('visibilitychange', () => { if (document.hidden) pause(); });
  draw(); update();
})();
