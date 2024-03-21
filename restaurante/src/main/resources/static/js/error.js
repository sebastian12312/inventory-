document.addEventListener("DOMContentLoaded", function () {
const circleContainer = document.querySelector(".circle-container");

for (let i = 0; i < 100; i++) {
const circle = document.createElement("div");
circle.className = "circle";
circle.style.width = Math.random() * 20 + "px";
circle.style.height = circle.style.width;
circle.style.top = Math.random() * 100 + "vh";
circle.style.left = Math.random() * 100 + "vw";
circle.style.animationDuration = Math.random() * 5 + 2 + "s";
circleContainer.appendChild(circle);
}
});