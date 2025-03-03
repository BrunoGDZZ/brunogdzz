document.addEventListener("DOMContentLoaded", function () {
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

    // Sidebar functionality
    const sidebar = document.getElementById("mySidebar");
    window.openNav = function() {
        sidebar.style.width = "250px";
        document.body.style.marginLeft = "250px";
    };
    
    window.closeNav = function() {
        sidebar.style.width = "0";
        document.body.style.marginLeft = "0";
    };
});
