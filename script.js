(() => {
  'use strict';
  const root = document.documentElement;
  const themeButton = document.querySelector('.theme-toggle');
  const syncTheme = () => {
    const light = root.dataset.theme === 'light';
    themeButton?.setAttribute('aria-label', `Switch to ${light ? 'dark' : 'light'} theme`);
    themeButton?.setAttribute('aria-pressed', String(light));
    document.querySelector('meta[name="theme-color"]')?.setAttribute('content', light ? '#f5f8ff' : '#101827');
  };
  syncTheme();
  themeButton?.addEventListener('click', () => {
    root.dataset.theme = root.dataset.theme === 'light' ? 'dark' : 'light';
    try { localStorage.setItem('bruno-theme', root.dataset.theme); } catch (_) { /* Storage may be disabled. */ }
    syncTheme();
  });
  const menu = document.querySelector('.menu-toggle');
  const nav = document.querySelector('.nav-links');
  const closeMenu = () => {
    nav?.classList.remove('open');
    menu?.setAttribute('aria-expanded', 'false');
    menu?.setAttribute('aria-label', 'Open navigation');
  };
  menu?.addEventListener('click', () => {
    const open = menu.getAttribute('aria-expanded') !== 'true';
    nav?.classList.toggle('open', open);
    menu.setAttribute('aria-expanded', String(open));
    menu.setAttribute('aria-label', open ? 'Close navigation' : 'Open navigation');
  });
  nav?.querySelectorAll('a').forEach(link => link.addEventListener('click', closeMenu));
  document.addEventListener('keydown', event => {
    if (event.key === 'Escape' && menu?.getAttribute('aria-expanded') === 'true') { closeMenu(); menu.focus(); }
  });
  document.addEventListener('click', event => { if (!event.target.closest('.nav')) closeMenu(); });
  matchMedia('(min-width: 601px)').addEventListener('change', closeMenu);
  const filters = [...document.querySelectorAll('[data-filter]')];
  const projects = [...document.querySelectorAll('.project-card')];
  filters.forEach(button => button.addEventListener('click', () => {
    const category = button.dataset.filter;
    filters.forEach(filter => { filter.classList.toggle('active', filter === button); filter.setAttribute('aria-pressed', String(filter === button)); });
    let count = 0;
    projects.forEach(project => { project.hidden = category !== 'all' && project.dataset.category !== category; if (!project.hidden) count++; });
    document.querySelector('.project-count').textContent = `${count} project${count === 1 ? '' : 's'}`;
  }));
  let copyTimer;
  document.querySelector('.copy-email')?.addEventListener('click', async () => {
    const status = document.querySelector('.copy-status');
    try { await navigator.clipboard.writeText('23bdesouza@gmail.com'); status.textContent = 'Email address copied.'; }
    catch (_) { status.textContent = 'Select the address to copy it, or click it to email me.'; }
    clearTimeout(copyTimer);
    copyTimer = setTimeout(() => { status.textContent = ''; }, 5000);
  });
  const sectionLinks = [...document.querySelectorAll('[data-section]')];
  const sections = sectionLinks.map(link => document.getElementById(link.dataset.section)).filter(Boolean);
  if (sections.length) {
    let scheduled = false;
    const updateSection = () => {
      let active;
      sections.forEach(section => { if (section.getBoundingClientRect().top <= innerHeight * .45) active = section.id; });
      if (innerHeight + scrollY >= document.documentElement.scrollHeight - 10) active = sections.at(-1).id;
      sectionLinks.forEach(link => { if (link.dataset.section === active) link.setAttribute('aria-current', 'location'); else link.removeAttribute('aria-current'); });
      scheduled = false;
    };
    addEventListener('scroll', () => { if (!scheduled) { scheduled = true; requestAnimationFrame(updateSection); } }, { passive: true });
    addEventListener('resize', updateSection);
    updateSection();
  }
  document.querySelectorAll('[data-year]').forEach(element => { element.textContent = new Date().getFullYear(); });
})();
