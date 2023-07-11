import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { Cards } from "./components/public/Cards/Cards";
import { Index } from "./components/public/Index";
import "./App.css";
import { Header } from "./components/public/Header";
import { Prenda } from "./components/public/Prenda";
import { Registro } from "./components/public/Registro";
import { Login } from "./components/public/Login";
import { Footer } from "./components/public/Footer";

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/prendas" element={<Cards />} />
        <Route path="/prenda/:id" element={<Prenda />} />
        <Route path="/registrar" element={<Registro />} />
        <Route path="/login" element={<Login />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
