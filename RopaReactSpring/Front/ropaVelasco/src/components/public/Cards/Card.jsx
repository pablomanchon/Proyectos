/* eslint-disable react/prop-types */
import { motion } from "framer-motion";

export const Card = ({ prenda }) => {
  return (
    <>
      <motion.div className="card">
        <motion.img src={prenda.imagenes[0]} />
        <motion.div>
          <motion.p>{prenda.nombre}</motion.p>
          <motion.p>{prenda.marca}</motion.p>
          <motion.p id="precio">${prenda.precio}</motion.p>
        </motion.div>
        <div className="botones">
          <button className="boton-carrito">Agregar al carrito</button>
          <a href={`/prenda/${prenda.id}`}>
            <button className="boton-detalles">Detalles</button>
          </a>
        </div>
      </motion.div>
    </>
  );
};
