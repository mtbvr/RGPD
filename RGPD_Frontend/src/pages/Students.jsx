import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import "./students.css";

export default function Students() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    api.get("/student")
        .then((res) => setStudents(res.data))
        .catch((err) => setError(err.response?.status))
        .finally(() => setLoading(false));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  if (loading) return <div className="page-state">Chargement...</div>;
  if (error === 403) return <div className="page-state forbidden">⛔ Accès non autorisé</div>;
  if (error) return <div className="page-state">Une erreur est survenue</div>;

  return (
      <div className="students-container">
        <header className="students-header">
          <h1>Étudiants</h1>
          <button className="logout-btn" onClick={handleLogout}>Déconnexion</button>
        </header>

        <div className="students-grid">
          {students.map((s) => (
              <div
                  key={s.id}
                  className="student-card"
                  onClick={() => navigate(`/students/${s.id}`)}
              >
                <div className="student-avatar">
                  {s.firstname?.[0]}{s.name?.[0]}
                </div>
                <div className="student-info">
                  <span className="student-name">{s.firstname} {s.name}</span>
                  <span className="student-sub">{s.email}</span>
                </div>
                <span className="student-arrow">→</span>
              </div>
          ))}
        </div>
      </div>
  );
}
