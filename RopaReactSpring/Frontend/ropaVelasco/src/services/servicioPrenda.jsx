export const mostrarCaracteristcias = (caracteristicas) => {
  if (caracteristicas.length != 0) {
    const car = [];
    for (let i = 0; i < 3; i++) {
      try {
        car.push(<li key={i}>{caracteristicas[i]}</li>);
      } catch (e) {
        continue;
      }
    }
    return car;
  }
  return null;
};

export const generarZoom = () => {
  //efecto Zoom en imagen
  var container = document.querySelector(".zoom-container");
  var overlay = document.querySelector(".zoom-overlay");
  var image = document.querySelector(".zoom-image");

  container.addEventListener("mousemove", function (event) {
    var rect = image.getBoundingClientRect();
    var xPos = event.clientX - rect.left;
    var yPos = event.clientY - rect.top;

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
};
