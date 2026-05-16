import axios from "axios";
import type { ITodo } from "../types/todo";

export const API_SERVER_HOST = "http://localhost:8080";

const prefix = `${API_SERVER_HOST}/api/todo`;

export const getOne = async (tno: number) => {
  const res = await axios.get<ITodo>(`${prefix}/${tno}`);
  return res.data;
};
