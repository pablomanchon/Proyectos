import { useState } from "react";
import axios from "axios";

export const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/logincheck", { email, password })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit} id="autenticacion">
      <div>
        <label>Usuario</label>
        <input
          onChange={(e) => setEmail(e.target.value)}
          type="text"
          value={email}
          placeholder="Usuario"
          name="email"
        />
      </div>
      <div>
        <label>Password</label>
        <input
          onChange={(e) => setPassword(e.target.value)}
          value={password}
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
