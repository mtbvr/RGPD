import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/axios";
import "./studentDetail.css";

function Field({ label, value }) {
  return (
      <div className="detail-field">
        <label>{label}</label>
        <span>{value ?? "—"}</span>
      </div>
  );
}

export default function StudentDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [student, setStudent] = useState(null);
  const [sensitive, setSensitive] = useState(null);
  const [sensitiveError, setSensitiveError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get(`/student/${id}`)
        .then((res) => setStudent(res.data))
        .catch(() => navigate("/students"))
        .finally(() => setLoading(false));

    api.get(`/sensitive/${id}`)
        .then((res) => setSensitive(res.data))
        .catch((err) => setSensitiveError(err.response?.status));
  }, [id]);

  if (loading) return <div className="page-state">Chargement...</div>;

  return (
      <div className="detail-container">
        <button className="back-btn" onClick={() => navigate("/students")}>
          ← Retour
        </button>

        {/* Identité */}
        <section className="detail-card">
          <h2>Identité</h2>
          <div className="detail-grid">
            <Field label="Nom" value={student?.name} />
            <Field label="Prénom" value={student?.firstname} />
            <Field label="Email" value={student?.email} />
            <Field label="Téléphone" value={student?.phoneNumber} />
          </div>
        </section>

        {/* Parent */}
        <section className="detail-card">
          <h2>Parent / Tuteur</h2>
          <div className="detail-grid">
            <Field label="Nom" value={student?.parentName} />
            <Field label="Email" value={student?.parentEmail} />
            <Field label="Téléphone" value={student?.parentPhone} />
          </div>
        </section>

        {/* Scolaire */}
        <section className="detail-card school-section">
          <h2>Informations scolaires</h2>
          {student?.school?.length > 0 ? (
              <>
                <div className="notes-header">
                  <span>Note</span>
                  <span>Option</span>
                </div>
                {student.school.map((s, i) => (
                    <div key={i} className="notes-row">
                      <span className="note-value">{s.notes ?? "—"}</span>
                      <span className="note-badge">{s.options ?? "—"}</span>
                    </div>
                ))}
              </>
          ) : (
              <p className="empty">Aucune note enregistrée.</p>
          )}
        </section>

        {/* Données sensibles */}
        <section className="detail-card sensitive-section">
          <h2><span className="sensitive-badge">🔒 Données sensibles</span></h2>

          {sensitiveError === 403 ? (
              <div className="forbidden-panel">
                <div className="forbidden-icon">⛔</div>
                <h3>Accès non autorisé</h3>
                <p>Vous ne disposez pas des droits nécessaires pour consulter les données sensibles de cet étudiant.</p>
              </div>
          ) : sensitiveError ? (
              <div className="forbidden-panel">
                <p>Erreur lors du chargement des données sensibles.</p>
              </div>
          ) : (
              <div className="detail-grid">
                <Field label="Allergie" value={sensitive?.allergy} />
              </div>
          )}
        </section>
      </div>
  );
}
