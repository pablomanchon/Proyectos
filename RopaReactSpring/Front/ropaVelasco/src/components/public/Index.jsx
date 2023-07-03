import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Item } from "./Item";
import Section from "./Section";
import anime from "../../../node_modules/animejs/lib/anime.es.js";
import { useInView } from "framer-motion";

export const Index = () => {
  const [prendas, setPrendas] = useState([]);
  const [loading, setLoading] = useState(true);
  const ref = useRef(null);
  const isInView = useInView(ref, { once: true });

  useEffect(() => {
    getCards();
  }, []);

  const getCards = () => {
    axios
      .get("http://localhost:8080/prendas/lista")
      .then((response) => {
        setPrendas(response.data);
        setLoading(false);
      })
      .catch((error) => {
        alert(`alerta! : ${error.message}`);
      });
  };

  const getRandomObjects = () => {
    const arrayMezclado = prendas.sort(() => 0.5 - Math.random());
    return arrayMezclado.splice(0, 2);
  };

  const randomObjects = getRandomObjects();

  if (loading) {
    return <div>Cargando</div>;
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
            <Section>{mostrarPrendasCarrousel()}</Section>
          </div>
          <div className="carousel-item active">
            <Section>{mostrarPrendasCarrousel()}</Section>
          </div>
          <div className="carousel-item">
            <Section>{mostrarPrendasCarrousel()}</Section>
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

  function mostrarPrendasCarrousel() {
    return randomObjects.map((prenda, i) => {
      if ((i + 1) % 2 == 0) {
        return (
          <>
            <div
              ref={ref}
              className="card-left"
              style={{
                transform: "translateX(0)",
                padding: "1rem",
                opacity: 1,
                transition: "all 0.9s cubic-bezier(0.17, 0.55, 0.55, 1) 0.3s",
              }}>
              <Item prenda={prenda} />
            </div>
          </>
        );
      } else {
        return (
          <>
            <div className="card-right">
              <Item prenda={prenda} />
            </div>
          </>
        );
      }
    });
  }
};
