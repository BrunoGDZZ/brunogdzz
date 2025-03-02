document.addEventListener("DOMContentLoaded", function () {
    // Tab switching logic
    const tabs = document.querySelectorAll(".tab-link");
    const contents = document.querySelectorAll(".tab-content");

    tabs.forEach(tab => {
        tab.addEventListener("click", function (event) {
            event.preventDefault();
            const target = this.getAttribute("data-tab");

            tabs.forEach(t => t.classList.remove("active"));
            this.classList.add("active");

            contents.forEach(content => {
                content.classList.remove("active");
            });

            document.getElementById(target).classList.add("active");
        });
    });

    // Ensure first tab is active on load
    if (tabs.length > 0) {
        tabs[0].classList.add("active");
        contents[0].classList.add("active");
    }

    // Sidebar toggle functions
    window.openNav = function() {
        document.getElementById("sidebar").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    };

    window.closeNav = function() {
        document.getElementById("sidebar").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    };
});
