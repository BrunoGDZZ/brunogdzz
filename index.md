---
layout: default
---

<link rel="stylesheet" href="style.css">
<script src="script.js" defer></script>

<!-- Sidebar -->
<div id="sidebar" class="sidebar">
    <button class="closebtn" onclick="closeNav()">&times;</button>
    <ul class="tabs">
        <li><a href="#" class="tab-link active" data-tab="about">About</a></li>
        <li><a href="#" class="tab-link" data-tab="projects">Projects</a></li>
        <li><a href="#" class="tab-link" data-tab="contact">Contact</a></li>
    </ul>
</div>

<!-- Main Content -->
<div id="main" class="main-content">
    <!-- Hamburger Icon to open the sidebar -->
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>

    <!-- About Section -->
    <div id="about" class="tab-content active">
        <img src="IMG_0615.jpg" alt="Bruno Garbuio De Souza" width="200" 
             style="border-radius: 50%; display: block; margin: 20px auto; box-shadow: 0px 4px 10px rgba(255,255,255,0.3);">
        <h2>About Me</h2>
        <p>Highly motivated and results-oriented undergraduate Computer Science student seeking challenging opportunities. A driven and inquisitive student with a strong desire to learn and grow within a professional environment, eager to contribute and add value to an organization building a successful career in the technology industry.</p>
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
        </div>
    </div>

    <!-- Contact Section -->
    <div id="contact" class="tab-content">
        <h2>Contact Me</h2>
        <p>Email: 23bdesouza@gmail.com</p>
        <p>LinkedIn: <a href="https://www.linkedin.com/in/bruno-garbuio-de-souza" target="_blank">View Profile</a></p>
        <p>GitHub: <a href="https://github.com/brunogdzz" target="_blank">View Profile</a></p>
    </div>

    <footer>
        <p>&copy; 2025 Bruno Garbuio De Souza. All rights reserved.</p>
    </footer>
</div>
