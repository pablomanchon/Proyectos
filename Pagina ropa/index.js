document.onmousemove = mtrack;

function mtrack(e) {
  var x, y, x1, x2, y1, y2;
  fact = 1200 / 400;
  opp = 100;

  if (e == null) e = window.event;
  x = e.clientX;
  y = e.clientY;

  x1 = -opp + x * fact;
  y1 = -opp + y * fact;
  x2 = +opp + x * fact;
  y2 = +opp + y * fact;

  document.images.big.style.display = "inline";
  document.images.big.style.left = x * (1 - fact);
  document.images.big.style.top = y * (1 - fact);
  document.images.big.style.clip =
    "rect(" + y1 + "px," + x2 + "px," + y2 + "px," + x1 + "px)";
}
