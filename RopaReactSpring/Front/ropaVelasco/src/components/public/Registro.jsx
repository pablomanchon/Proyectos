import { useState } from "react";
import axios from "axios";

export const Registro = () => {
  const [formData, setFormData] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/registrar", formData)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <form onSubmit={handleSubmit} id="autenticacion">
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
          onChange={handleChange}
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
