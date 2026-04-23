import { useEffect, useState } from "react";
import api from "../api/axios";

export default function Sensitive() {
    const [data, setData] = useState([]);

    useEffect(() => {
        api.get("/sensitive")
            .then(res => setData(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div>
            <h1>Données sensibles</h1>
            {data.map(s => (
                <div key={s.id}>
                    {s.id} - {s.allergy}
                </div>
            ))}
        </div>
    );
}