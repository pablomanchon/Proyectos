import axios from "axios";
import { useEffect, useState } from "react";
import { Card } from "./Card";
import "../../App.css";
import { motion } from "framer-motion";

export const Cards = () => {
  const [prendas, setPrendas] = useState([]);
  useEffect(() => {
    getCards();
  }, []);

  const getCards = async () => {
    await axios
      .get("http://localhost:8080/prendas/lista")
      .then((response) => {
        setPrendas(response.data);
      })
      .catch(() => {
        alert("Error al extraer los datos");
      });
  };

  return (
    <>
      <div className="cartas">
        {prendas.map((prenda, i) => (
          <Card prenda={prenda} key={i} />
        ))}
      </div>
    </>
  );
};
