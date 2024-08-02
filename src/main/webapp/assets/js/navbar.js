let isSidebarFull = false;

const sidebarFullTag = document.getElementById("sidebar-full");
const contentBodyTag = document.getElementById("content-body");

if (screen.width > 768) {
    sidebarFullTag.classList.remove("hidden");
    isSidebarFull = true;
    contentBodyTag.classList.add("pl-328");
}

const handleToggleSidebar = () => {
    if (isSidebarFull) {
        sidebarFullTag.classList.add("hidden");
        isSidebarFull = false;
        contentBodyTag.classList.remove("pl-328");
    } else {
        sidebarFullTag.classList.remove("hidden");
        isSidebarFull = true;
        contentBodyTag.classList.add("pl-328");
    }
    removeActiveHome()
};

const sidebarLinkFull = document.getElementById("sidebarLinkFull");
const sidebarLinkShorten = document.getElementById("sidebarLinkShorten");

const url = window.location.href;

const removeActiveHome = () => {
    sidebarLinkFull.children[0].classList.remove("sidebar-linkItem-active");
    sidebarLinkFull.children[0].classList.remove("sidebar-icon-active");
};