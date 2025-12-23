import axios from "axios";

export const api = axios.create({
  // If you're running Expo in the browser or emulator on the SAME computer:
  baseURL: "http://localhost:8080/api",

  // Later, if you run Expo Go on your PHONE, change this to your PC's LAN IP:
  // baseURL: "http://192.168.x.x:8080/api",
});
export const addItem = async (item: {
  name: string;
  brand: string;
  sizeText: string;
}) => {
  return api.post("/items", item);
};
