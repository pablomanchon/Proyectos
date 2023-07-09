import { useState } from "react";
import { registrar } from "../../services/Repository";

export const Registro = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    email: "",
    password: "",
    password2: "",
  });
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    console.log(formData);
  };
  return (
    <form onSubmit={registrar()} id="autenticacion">
      <div>
        <label>Usuario</label>
        <input
          onChange={handleChange}
          type="text"
          placeholder="Usuario"
          name="nombre"
        />
      </div>
      <div>
        <label>Email</label>
        <input
          onChange={handleChange}
          type="email"
          placeholder="Email"
          name="email"
        />
      </div>
      <div>
        <label>Password</label>
        <input
          onChange={handleChange}
          type="password"
          placeholder="Password"
          name="password"
        />
      </div>
      <div>
        <label>Repetir Password</label>
        <input
          type="password"
          placeholder="Repetir Password"
          name="password2"
        />
      </div>
      <div className="botones">
        <button type="submit" className="btn btn-primary principal">
          Registrarme
        </button>
        <a href="/login">
          <button type="button" className="btn btn-secondary">
            Login
          </button>
        </a>
      </div>
    </form>
  );
};
