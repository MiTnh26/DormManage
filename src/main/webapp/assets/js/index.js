let showModalLogin = false;

$(document).ready(function () {
    if ('@Request["isShowLogin"]' === 'true') {
        handleShowModal();
    }
});

if (window.location.href.includes("Home")) {
    loginModalTag.classList.remove("hidden");
}

// QA

const listQA = document.getElementById("QA-content");

const showQA = (index) => {
    if (
        !listQA.children[index].children[1].classList["value"].includes("hidden")
    ) {
        listQA.children[index].children[1].classList.add("hidden");
    } else {
        listQA.children[index].children[1].classList.remove("hidden");
    }
};

// Get the button
let scrollTop = document.getElementById("scrollTop");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.documentElement.scrollTop > 500) {
        scrollTop.style.display = "block";
    } else {
        scrollTop.style.display = "none";
    }
}

const smoothScroll = () => {
    let h = document.documentElement.scrollTop;
    let myInterval = setInterval(function () {
        document.documentElement.scrollTo(0, h);
        h -= 100;
        if (h <= 0) {
            clearInterval(myInterval);
            document.documentElement.scrollTo(0, 0);
        }
    }, 10);
};

let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides((slideIndex += n));
}

function currentSlide(n) {
    showSlides((slideIndex = n));
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");

    if (n > slides.length) {
        slideIndex = 1;
    }
    if (n < 1) {
        slideIndex = slides.length;
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    slides[slideIndex - 1].style.display = "block";
}

setInterval(() => plusSlides(1), 3000);

