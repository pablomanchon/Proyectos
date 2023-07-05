import axios from "axios";

export const getCards = async () => {
  return await axios.get("http://localhost:8080/prendas/lista");
};

export const getById = async (id) => {
  return await axios.get(`http://localhost:8080/prendas/${id}`);
};
