import { useState } from "react";
import axios from "axios";

export const Login = () => {
  const [formData, setFormData] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/logincheck", formData)
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
    <form
      method="POST"
      action="http://localhost:8080/logincheck"
      id="autenticacion">
      <div>
        <label>Usuario</label>
        <input
          onChange={handleChange}
          type="text"
          placeholder="Usuario"
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
      <div className="botones">
        <button type="submit" className="btn btn-primary principal">
          Login
        </button>
        <a href="/registrar">
          <button type="button" className="btn btn-secondary">
            Registrarme
          </button>
        </a>
      </div>
    </form>
  );
};
