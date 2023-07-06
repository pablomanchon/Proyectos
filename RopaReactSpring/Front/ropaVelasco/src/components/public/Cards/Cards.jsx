import { useEffect, useState } from "react";
import { Card } from "./Card";
import { getCards } from "../../../services/Repository";

export const Cards = () => {
  const [prendas, setPrendas] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getCards().then((res) => {
      setPrendas(res.data);
      setLoading(false);
    });
    console.log(prendas);
  }, []);

  if (loading) {
    return (
      <div className="spinner-border text-primary" role="status">
        <span className="visually-hidden">Loading...</span>
      </div>
    );
  }
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
