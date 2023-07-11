import axios from "axios";

export const getCards = async () => {
  return await axios.get("http://localhost:8080/prendas/lista");
};

export const getById = async (id) => {
  return await axios.get(`http://localhost:8080/prendas/${id}`);
};

export const getRandomCards = async () => {
  return await axios.get("http://localhost:8080/prendas/twoRandom");
};
export const findByTerm = async (term) => {
  const palabras = term.split(" ");

  await palabras.forEach((palabra) => {
    axios
      .get("http://localhost:8080/prendas/buscarPorTermino", {
        params: { term: palabra },
      })
      .then((response) => {
        console.log(response.data);
      });
  });
};

export const registrar = (username) => {
  console.log(username);
};
