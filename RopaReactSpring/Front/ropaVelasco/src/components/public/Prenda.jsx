/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";
import { getById } from "../../services/Repository";
import {
  generarZoom,
  mostrarCaracteristcias,
} from "../../services/servicioPrenda";
import { useParams } from "react-router-dom";
import { motion } from "framer-motion";

export const Prenda = () => {
  const [prenda, setPrenda] = useState({});
  const [loading, setLoading] = useState(true);
  const [imagenPrenda, setImagenPrenda] = useState(undefined);
  const params = useParams();
  const id = params.id;

  useEffect(() => {
    getById(id).then((res) => {
      setPrenda(res.data);
      setLoading(false);
      setImagenPrenda(res.data.imagenes[0]);
      setTimeout(function () {
        generarZoom();
      }, 100);
    });
  }, []);

  if (loading) {
    return (
      <div className="spinner-border text-primary" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
    );
  }

  return (
    <section className="prenda">
      <div className="zoom-container">
        <img className="zoom-image" src={imagenPrenda} alt={prenda.nombre} />
        <div className="zoom-overlay"></div>
      </div>
      <div className="image">
        <div className="imagenes">
          {prenda.imagenes.map((imagen, i) => (
            <img
              onClick={() =>
                imagenPrenda !== imagen ? setImagenPrenda(imagen) : null
              }
              className="image"
              key={i}
              src={imagen}
            />
          ))}
        </div>
      </div>
      <div className="caracteristicas">
        <h3 id="titulo">{prenda.nombre}</h3>
        <ul>{mostrarCaracteristcias(prenda.caracteristicas)}</ul>
        <h3 id="precio">${prenda.precio}</h3>
        <button onClick={() => {}} className="boton-carrito">
          Agregar al carrito
        </button>
      </div>
    </section>
  );
};
