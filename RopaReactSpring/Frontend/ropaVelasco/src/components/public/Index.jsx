import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Item } from "./Item";
import Section from "./Section";
import { motion } from "framer-motion";
import { getRandomCards } from "../../services/Repository";

export const Index = () => {
  const [prendas1, setPrendas1] = useState([]);
  const [prendas2, setPrendas2] = useState([]);
  const [prendas3, setPrendas3] = useState([]);
  const [loading, setLoading] = useState(true);
  const ref = useRef(null);

  useEffect(() => {
    getRandomCards()
      .then((response) => {
        setPrendas1(response.data);
        setLoading(false);
      })
      .catch(() => {
        alert("Error al conectar con la Base de Datos");
      });
    getRandomCards()
      .then((response) => {
        setPrendas2(response.data);
        setLoading(false);
      })
      .catch(() => {
        alert("Error al conectar con la Base de Datos");
      });
    getRandomCards()
      .then((response) => {
        setPrendas3(response.data);
        setLoading(false);
      })
      .catch(() => {
        alert("Error al conectar con la Base de Datos");
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
    <>
      <div
        id="myCarousel"
        className="carousel slide mb-6 pointer-event"
        data-bs-ride="carousel"
        data-bs-theme="light">
        <div className="carousel-indicators">
          <button
            type="button"
            data-bs-target="#myCarousel"
            data-bs-slide-to="0"
            className=""
            aria-label="Slide 1"></button>
          <button
            type="button"
            data-bs-target="#myCarousel"
            data-bs-slide-to="1"
            aria-label="Slide 2"
            className="active"
            aria-current="true"></button>
          <button
            type="button"
            data-bs-target="#myCarousel"
            data-bs-slide-to="2"
            aria-label="Slide 3"
            className=""></button>
        </div>
        <div className="carousel-inner">
          <div className="carousel-item">
            <Section>{prendasCarrousel(prendas1)}</Section>
          </div>
          <div className="carousel-item active">
            <Section>{prendasCarrousel(prendas2)}</Section>
          </div>
          <div className="carousel-item">
            <Section>{prendasCarrousel(prendas3)}</Section>
          </div>
        </div>
        <button
          className="carousel-control-prev"
          type="button"
          data-bs-target="#myCarousel"
          data-bs-slide="prev">
          <span
            className="carousel-control-prev-icon"
            aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button
          className="carousel-control-next"
          type="button"
          data-bs-target="#myCarousel"
          data-bs-slide="next">
          <span
            className="carousel-control-next-icon"
            aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>
    </>
  );

  function prendasCarrousel(prendas) {
    return prendas.map((prenda, i) => {
      if ((i + 1) % 2 == 0) {
        return (
          <motion.div
            key={i}
            ref={ref}
            className="card-left"
            style={{
              opacity: 0,
              x: 200,
            }}
            whileInView={{
              opacity: 1,
              x: 0,
              transition: "all 0.9s cubic-bezier(0.17, 0.55, 0.55, 1) 0.3s",
            }}>
            <Item prenda={prenda} />
          </motion.div>
        );
      } else {
        return (
          <motion.div
            key={i}
            className="card-right"
            style={{
              opacity: 0,
              x: 200,
            }}
            whileInView={{
              opacity: 1,
              x: 0,
              transition: "all 0.9s cubic-bezier(0.17, 0.55, 0.55, 1) 0.3s",
            }}>
            <Item prenda={prenda} />
          </motion.div>
        );
      }
    });
  }
};
