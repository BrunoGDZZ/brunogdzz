---
layout: default
---

<link rel="stylesheet" href="style.css">
<script src="script.js" defer></script>

<h1>Welcome to My Portfolio</h1>

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
    <img src="IMG_0615.jpg" alt="Bruno Garbuio De Souza" width="200" 
         style="border-radius: 50%; display: block; margin: 20px auto;">
    <h2>About Me</h2>
    <p>Highly motivated and results-oriented Computer Science student passionate about technology, software development, and problem-solving.</p>
</div>

<!-- Projects Section -->
<div id="projects" class="tab-content">
    <h2>Projects</h2>
    <div class="projects-container">
        <div class="project">
            <h3>Robots Recreation</h3>
            <p>Recreated the Classic Robots game using Java OOP, later equipping it with SwiftUI.</p>
            <a href="https://github.com/BrunoGDZZ/brunogdzz/tree/main/PersonalProjects2025/RobotsGame" target="_blank">View Project</a>
        </div>
        <div class="project">
            <h3>Portfolio Website</h3>
            <p>Designed and developed my personal portfolio using Jekyll and GitHub Pages.</p>
            <a href="https://brunogdzz.github.io" target="_blank">View Website</a>
        </div>
    </div>
</div>

<!-- Coursework Section -->
<div id="coursework" class="tab-content">
    <h2>Relevant Coursework</h2>
    <ul>
        <li><strong>Data Structures & Algorithms</strong> - University of Texas at Arlington</li>
        <li><strong>Operating Systems</strong> - University of Texas at Arlington</li>
    </ul>
</div>

<!-- Contact Section -->
<div id="contact" class="tab-content">
    <h2>Contact Me</h2>
    <p>Email: 23bdesouza@gmail.com</p>
    <p>GitHub: <a href="https://github.com/brunogdzz" target="_blank">View Profile</a></p>
</div>

<footer>
    <p>&copy; 2025 Bruno Garbuio De Souza. All rights reserved.</p>
</footer>
