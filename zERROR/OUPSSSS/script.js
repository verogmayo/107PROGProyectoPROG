const textElement = document.getElementById("text");
const text = "OUPSSSS....";
let index = 0;
let isDeleting = false;

function typeEffect() {
    if (!isDeleting) {
        textElement.textContent = text.substring(0, index++);
        if (index > text.length) {
            isDeleting = true;
            setTimeout(typeEffect, 1000); // Espera antes de borrar
            return;
        }
    } else {
        textElement.textContent = text.substring(0, index--);
        if (index === 0) {
            isDeleting = false;
        }
    }
    setTimeout(typeEffect, isDeleting ? 100 : 200);
}

typeEffect();