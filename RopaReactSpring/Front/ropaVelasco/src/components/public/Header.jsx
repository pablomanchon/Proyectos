import { motion } from "framer-motion";

export const Header = () => {
  return (
    <header>
      <ul className="menu">
        <motion.li
          whileInView={{
            rotate: -5,
            scale: 1.2,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          Home
        </motion.li>
        <motion.li
          whileInView={{
            rotate: 5,
            scale: 1.2,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          Prendas
        </motion.li>
      </ul>
      <div className="logo-nombre">
        <div id="logo" />
        <h2 id="nombre">
          <span>VELASCO</span>
          <span>NATURALS</span>
        </h2>
      </div>
      <ul className="menu">
        <motion.li
          whileInView={{
            rotate: -5,
            scale: 1.2,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          Iniciar Sesion
        </motion.li>
        <motion.li
          whileInView={{
            rotate: 5,
            scale: 1.2,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          Perfil
        </motion.li>
      </ul>
    </header>
  );
};
