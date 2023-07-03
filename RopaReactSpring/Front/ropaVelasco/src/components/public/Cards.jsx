import axios from "axios";
import { useEffect, useState } from "react";
import { Card } from "./Card";
import "../../App.css";

export const Cards = () => {
  const [prendas, setPrendas] = useState([]);
  useEffect(() => {
    getCards();
  }, []);
  const getCards = async () => {
    const result = await axios.get("http://localhost:8080/prendas/lista");
    setPrendas(result.data);
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
