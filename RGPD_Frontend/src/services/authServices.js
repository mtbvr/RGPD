import api from "../api/axios";

export const login = async (data) => {
    const res = await api.post("/auth/login", data);
    localStorage.setItem("token", res.data.token);
    return res.data;
};

export const signup = async (data) => {
    const res = await api.post("/auth/signup", data);
    localStorage.setItem("token", res.data.token);
    return res.data;
};