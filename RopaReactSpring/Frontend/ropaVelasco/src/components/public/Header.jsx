import { motion } from "framer-motion";
import { useState } from "react";
import { findByTerm } from "../../services/Repository";
import { Link } from "react-router-dom";

export const Header = () => {
  const [search, setSearch] = useState("");
  const change = (e) => {
    setSearch(e.target.value);
  };

  const submit = (e) => {
    e.preventDefault();
    findByTerm(search);
  };

  return (
    <>
      <header>
        <ul className="menu">
          <motion.li
            animate={{
              rotate: -5,
              scale: 1.2,
              x: 100,
            }}
            whileHover={{
              scale: 1.5,
            }}>
            <Link to={"/"}>Home</Link>
          </motion.li>
          <motion.li
            animate={{
              rotate: 5,
              scale: 1.2,
              x: -100,
            }}
            whileHover={{
              scale: 1.5,
            }}>
            <Link to={"/prendas"}>Prendas</Link>
          </motion.li>
        </ul>
        <Link to={"/"} className="logo-nombre">
          <div id="logo" />
          <h2 id="nombre">
            <span>VELASCO</span>
            <span>NATURALS</span>
          </h2>
        </Link>
        <ul className="menu">
          <motion.li
            animate={{
              rotate: -5,
              x: 100,
              scale: 1.2,
            }}
            whileHover={{
              scale: 1.5,
            }}>
            <Link to={"/login"}>Iniciar Sesion</Link>
          </motion.li>
          <motion.li
            animate={{
              rotate: 5,
              scale: 1.2,
              x: -100,
            }}
            whileHover={{
              scale: 1.5,
            }}>
            <Link>Perfil</Link>
          </motion.li>
        </ul>
      </header>
      <form action="#" onSubmit={submit} id="search">
        <i className="bi bi-search"></i>
        <input
          onChange={change}
          className="input"
          id="search"
          type="text"></input>
      </form>
    </>
  );
};
