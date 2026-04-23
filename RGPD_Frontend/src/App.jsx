import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Auth from "./pages/Auth";
import Students from "./pages/Students";
import StudentDetail from "./pages/StudentDetail";

function App() {
  const token = localStorage.getItem("token");

  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path="/" element={<Auth />} />

        {/* Protected */}
        <Route
          path="/students"
          element={token ? <Students /> : <Navigate to="/" />}
        />
        <Route
          path="/students/:id"
          element={token ? <StudentDetail /> : <Navigate to="/" />}
        />

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
