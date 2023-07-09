/* eslint-disable react/prop-types */
import { mostrarCaracteristcias } from "../../services/servicioPrenda";

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
          <div className="precio-botones">
            <h3 id="precio">${prenda.precio}</h3>
            <div className="botones">
              <button className="boton-carrito">Agregar al carrito</button>
              <a href={`prenda/${prenda.id}`}>
                <button className="boton-detalles">Detalles</button>
              </a>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
