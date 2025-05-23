---
layout: default
---

<link rel="stylesheet" href="style.css">
<script src="script.js" defer></script>

<h1>Bruno Garbuio De Souza | UTA Computer Science INT 2027</h1>

<!-- Hamburger Menu Button -->
<button class="menu-btn" onclick="openNav()">
    &#9776; <!-- Hamburger icon (☰) -->
</button>

<!-- Sidebar -->
<div id="mySidebar" class="sidebar">
    <button class="closebtn" onclick="closeNav()">&times;</button>
    <ul class="tabs">
        <li><a href="#" class="tab-link active" data-tab="about">About</a></li>
        <li><a href="#" class="tab-link" data-tab="projects">Projects</a></li>
        <li><a href="#" class="tab-link" data-tab="coursework">Coursework</a></li>
        <li><a href="#" class="tab-link" data-tab="contact">Contact</a></li>
    </ul>
</div>

<!-- About Section -->
<div id="about" class="tab-content active">
  <img src="IMG_0615.jpg" alt="Bruno Garbuio De Souza" class="profile-img">
    <h2>About Me</h2>
    <p>I'm a Computer Science student at UTA who is genuinely excited about tackling new coding challenges and learning from experienced professionals in real-world environments. I'm looking for an opportunity to contribute my growing skills and be part of a team that's building innovative technology. This is my portfolio where I will be uploading side projects of mine and ideas I've been working on. </p>
</div>

<!-- Projects Section -->
<div id="projects" class="tab-content">
    <h2>Projects</h2>
    <div class="projects-container">
        <div class="project">
            <h3>Robots Recreation</h3>
            <p>Recreated the Classic Robots game using Java OOP, later equipping it with SwiftUI. [IN PROGRESS] </p>
            <p class="project-date">Date: Februrary 20 2025</p>
            <a href="https://github.com/BrunoGDZZ/brunogdzz/tree/main/PersonalProjects2025/RobotsGame" target="_blank">View Project</a>
        </div>
        <div class="project">
            <h3>Portfolio Website</h3>
            <p>Designed and developed my personal portfolio using Jekyll and GitHub Pages. [IN PROGRESS] </p>
            <p class="project-date">Date: March 2 2025</p>
            <a href="https://github.com/BrunoGDZZ/brunogdzz" target="_blank">View Website</a>
        </div>
        <div class="project">
            <h3>abUTA</h3>
            <p>Developed a text-based social media system with threaded public posts, private messaging to users, account muting, and blocking. White Implementing key                 object-oriented programming concepts using Java.
            [IN PROGRESS] </p>
            <p class="project-date">Date: March 4 2025</p>
            <a href="https://github.com/BrunoGDZZ/brunogdzz/tree/main/PersonalProjects2025/abUTA/baseline" target="_blank">View Project</a>
        </div>
    </div>
</div>

<!-- Coursework Section -->
<div id="coursework" class="tab-content">
    <h2>Relevant Coursework</h2>
    <ul>
        <li><strong>Introduction to Computer Science & Engineering</strong> - University of Texas at Arlington (CSE 1106)</li>
        <li><strong>Introduction to Computers & Programming</strong> - University of Texas at Arlington (CSE 1310)</li>
        <li><strong>Intermediate Programming</strong> - University of Texas at Arlington (CSE 1320)</li>
        <li><strong>Object Oriented Programming (Java and C++)</strong> - University of Texas at Arlington (CSE 1325) [CURRENTLY]</li>
        <li><strong>Computer Organization & Assembly Language</strong> - University of Texas at Arlington (CSE 2312) [CURRENTLY]</li>
    </ul>
</div>

<!-- Contact Section -->
<div id="contact" class="tab-content">
    <h2>Contact Me</h2>
    <p>Email: 23bdesouza@gmail.com</p>
    <p>GitHub: <a href="https://github.com/brunogdzz" target="_blank">View Profile</a></p>
    <p>LinkedIn: <a href="https://www.linkedin.com/in/bruno-garbuio-de-souza" target="_blank">View Profile</a></p>
</div>

<footer>
    <p>&copy; 2025 Bruno Garbuio De Souza. All rights reserved.</p>
</footer>
