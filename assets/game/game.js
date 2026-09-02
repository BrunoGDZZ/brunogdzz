(() => {
  const modal = document.getElementById('bruno-game-modal');
  const launcher = document.getElementById('bruno-game-launcher');
  const close = document.getElementById('bruno-game-close');
  const canvas = document.getElementById('bruno-guitar-game');
  const ctx = canvas.getContext('2d');
  const scoreEl = document.getElementById('bruno-score');
  const comboEl = document.getElementById('bruno-combo');
  const timeEl = document.getElementById('bruno-time');
  const restart = document.getElementById('bruno-restart');
  const lanes = ['A','S','D','F'];
  let notes = [], score = 0, combo = 0, running = false, started = 0, last = 0, raf = 0, spawnTimer = 0;
  let high = Number(localStorage.getItem('brunoGuitarHighScore') || 0);
  const laneX = i => (i + .5) * canvas.width / 4;
  function resize(){ const w=Math.min(648,Math.floor(window.innerWidth*.9)); canvas.width=w; canvas.height=Math.round(w*.56); }
  function reset(){ notes=[]; score=0; combo=0; started=performance.now(); last=started; spawnTimer=0; running=true; update(); cancelAnimationFrame(raf); raf=requestAnimationFrame(loop); }
  function update(){scoreEl.textContent=`Score: ${score}  •  Best: ${high}`;comboEl.textContent=`Combo: ${combo}x`;timeEl.textContent=`Time: ${Math.max(0,30-Math.floor((performance.now()-started)/1000))}s`;}
  function spawn(){ notes.push({lane:Math.floor(Math.random()*4), y:-20, hit:false, speed:230+Math.random()*55}); }
  function hit(lane){ if(!running)return; const target=canvas.height-62; let best=null, dist=Infinity; for(const n of notes){const d=Math.abs(n.y-target);if(n.lane===lane&&!n.hit&&d<dist){best=n;dist=d;}} if(best&&dist<42){best.hit=true;combo++;score+=100+Math.min(combo,20)*5; if(score>high){high=score;localStorage.setItem('brunoGuitarHighScore',high)} tone(440+lane*90,.055); } else {combo=0; tone(110,.04)} update(); }
  function tone(freq,dur){try{const a=new AudioContext(),o=a.createOscillator(),g=a.createGain();o.frequency.value=freq;o.type='square';g.gain.setValueAtTime(.035,a.currentTime);g.gain.exponentialRampToValueAtTime(.001,a.currentTime+dur);o.connect(g);g.connect(a.destination);o.start();o.stop(a.currentTime+dur)}catch(e){}}
  function draw(){ctx.clearRect(0,0,canvas.width,canvas.height);ctx.fillStyle='#080b14';ctx.fillRect(0,0,canvas.width,canvas.height); const lw=canvas.width/4; for(let i=0;i<4;i++){ctx.fillStyle=i%2?'#0d1322':'#0a0f1b';ctx.fillRect(i*lw,0,lw,canvas.height);ctx.strokeStyle='#27324a';ctx.beginPath();ctx.moveTo(i*lw,0);ctx.lineTo(i*lw,canvas.height);ctx.stroke();ctx.fillStyle='#e5e7eb';ctx.font='bold 16px system-ui';ctx.textAlign='center';ctx.fillText(lanes[i],laneX(i),canvas.height-15)} const target=canvas.height-62;ctx.strokeStyle='#6b7280';ctx.lineWidth=3;ctx.beginPath();ctx.moveTo(0,target);ctx.lineTo(canvas.width,target);ctx.stroke(); for(const n of notes){if(n.hit)continue;ctx.fillStyle='#f59e0b';ctx.beginPath();ctx.arc(laneX(n.lane),n.y,13,0,Math.PI*2);ctx.fill();ctx.fillStyle='#111827';ctx.font='bold 12px system-ui';ctx.fillText(lanes[n.lane],laneX(n.lane),n.y+4)} }
  function loop(now){ if(!running)return; const dt=Math.min(.04,(now-last)/1000);last=now;spawnTimer+=dt;if(spawnTimer>.48){spawn();spawnTimer=0}for(const n of notes)n.y+=n.speed*dt;notes=notes.filter(n=>!n.hit&&n.y<canvas.height+30);if(now-started>=30000){running=false;draw();update();return}draw();update();raf=requestAnimationFrame(loop); }
  function open(){modal.classList.add('open');resize();reset();}
  function shut(){modal.classList.remove('open');running=false;cancelAnimationFrame(raf)}
  launcher.addEventListener('click',open);close.addEventListener('click',shut);restart.addEventListener('click',reset);window.addEventListener('resize',resize);modal.addEventListener('click',e=>{if(e.target===modal)shut()});window.addEventListener('keydown',e=>{const i=lanes.indexOf(e.key.toUpperCase());if(i>=0){e.preventDefault();hit(i)}if(e.key==='Escape')shut()});canvas.addEventListener('pointerdown',e=>{const r=canvas.getBoundingClientRect();hit(Math.max(0,Math.min(3,Math.floor((e.clientX-r.left)/r.width*4))))});
})();
