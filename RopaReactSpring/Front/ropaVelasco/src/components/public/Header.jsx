import { motion } from "framer-motion";

export const Header = () => {
  return (
    <header>
      <ul className="menu">
        <motion.li
          whileInView={{
            rotate: -5,
            scale: 1.2,
            x: 100,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          <a href="/">Home</a>
        </motion.li>
        <motion.li
          whileInView={{
            rotate: 5,
            scale: 1.2,
            x: -100,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          <a href="/prendas">Prendas</a>
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
            x: 100,
            scale: 1.2,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          <a href="/login">Iniciar Sesion</a>
        </motion.li>
        <motion.li
          whileInView={{
            rotate: 5,
            scale: 1.2,
            x: -100,
          }}
          whileHover={{
            scale: 1.5,
          }}>
          <a href="#">Perfil</a>
        </motion.li>
      </ul>
    </header>
  );
};
