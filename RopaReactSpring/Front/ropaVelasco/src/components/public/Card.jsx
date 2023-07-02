import { motion } from "framer-motion";
import "../../App.css";

export const Card = ({ prenda }) => {
  return (
    <>
      <motion.div className="card">
        <motion.img src={prenda.imagenes[0]} />
        <motion.div>
          <motion.p>Nombre: {prenda.nombre}</motion.p>
          <motion.p>Marca: {prenda.marca}</motion.p>
          <motion.p>Precio: ${prenda.precio}</motion.p>
        </motion.div>
      </motion.div>
    </>
  );
};
