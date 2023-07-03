/* eslint-disable react/prop-types */
import "../../App.css";

export const Item = ({ prenda }) => {
  return (
    <>
      <div className="item-car">
        <div className="image">
          <img src={prenda.imagenes[0]} alt={prenda.nombre} />
        </div>
        <div className="caracteristicas">
          <h3 id="titulo">{prenda.nombre}</h3>
          <ul>{mostrarCaracteristcias(prenda.caracteristicas)}</ul>
          <h3 id="precio">${prenda.precio}</h3>
          <div className="botones">
            <button className="boton-carrito">Agregar al carrito</button>
            <button className="boton-detalles">Detalles</button>
          </div>
        </div>
      </div>
    </>
  );
};
const mostrarCaracteristcias = (caracteristicas) => {
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
