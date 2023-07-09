import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { Cards } from "./components/public/Cards/Cards";
import { Index } from "./components/public/Index";
import "./App.css";
import { Header } from "./components/public/Header";
import { Prenda } from "./components/public/Prenda";
import { Registro } from "./components/public/Registro";

function App() {
  return (
    <>
      <Router>
        <Header />
        <Routes>
          <Route exact path="/" element={<Index />} />
          <Route exact path="/prendas" element={<Cards />} />
          <Route exact path="/prenda/:id" element={<Prenda />} />
          <Route exact path="/registrar" element={<Registro />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
