import { useState } from "react";
import { login, signup } from "../services/authServices.js";
import "./auth.css";

export default function Auth() {
    const [isLogin, setIsLogin] = useState(true);
    const [form, setForm] = useState({
        login: "",
        password: "",
    });

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (isLogin) {
                await login(form);
            } else {
                await signup(form);
            }

            window.location.href = "/students";
        } catch (err) {
            alert("Erreur auth");
            console.error(err);
        }
    };

    return (
        <div className="auth-container">
            <form className="auth-card" onSubmit={handleSubmit}>
                <h2>{isLogin ? "Connexion" : "Inscription"}</h2>

                <input
                    type="text"
                    placeholder="Email / Login"
                    onChange={(e) => setForm({ ...form, login: e.target.value })}
                    required
                />

                <input
                    type="password"
                    placeholder="Mot de passe"
                    onChange={(e) => setForm({ ...form, password: e.target.value })}
                    required
                />

                <button type="submit">
                    {isLogin ? "Se connecter" : "Créer un compte"}
                </button>

                <p className="switch">
                    {isLogin ? "Pas de compte ?" : "Déjà un compte ?"}
                    <span onClick={() => setIsLogin(!isLogin)}>
            {isLogin ? " S'inscrire" : " Se connecter"}
          </span>
                </p>
            </form>
        </div>
    );
}