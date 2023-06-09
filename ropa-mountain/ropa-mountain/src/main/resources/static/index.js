//efecto Zoom en imagen
var container = document.querySelector(".zoom-container");
var overlay = document.querySelector(".zoom-overlay");
var image = document.querySelector(".zoom-image");

container.addEventListener("mousemove", function (event) {
  var rect = image.getBoundingClientRect();
  var xPos = event.clientX - rect.left;
  var yPos = event.clientY - rect.top;
  console.log("xPos: " + xPos + ", yPos: " + yPos);

  console.log(rect);
  console.log(container.offSetLeft);
  var xPercent = (xPos / container.offsetWidth) * 100;
  var yPercent = (yPos / container.offsetHeight) * 100;

  overlay.style.left = xPos - overlay.offsetWidth / 2 + "px";
  overlay.style.top = yPos - overlay.offsetHeight / 2 + "px";

  overlay.style.backgroundImage = "url(" + image.src + ")";
  overlay.style.backgroundPosition = xPercent + "% " + yPercent + "%";
  overlay.style.backgroundSize = "60rem";
  overlay.style.opacity = 1;
});

container.addEventListener("mouseleave", function () {
  overlay.style.opacity = 0;
});

//selección de imagen
const imagenes = document.querySelectorAll(".imagen");
const imgPrincipal = document.querySelector("#imgPrincipal");
imagenes.forEach((imagen) => {
  console.log(imagen);
  if (imgPrincipal.src == imagen.src) {
    imagen.id = "seleccionada";
  }
  imagen.addEventListener("click", () => {
    imgPrincipal.src = imagen.src;
    imagenes.forEach((img) => {
      if (imgPrincipal.src == img.src) {
        img.id = "seleccionada";
      } else {
        img.id = "";
      }
    });
  });
});
