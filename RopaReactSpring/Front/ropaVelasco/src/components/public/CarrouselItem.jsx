import React from "react";

export const CarrouselItem = ({ prenda }) => {
  console.log(prenda);
  console.log(prenda);
  return (
    <div className="carousel-item">
      <div className="image">
        <img src={prenda.imagenes[0]} alt={prenda.nombre}></img>
      </div>
    </div>
  );
};
