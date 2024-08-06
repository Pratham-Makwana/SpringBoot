import axios from "axios";
import { useState } from "react";

function AddChallenge({ onChallengeAdded }) {

    const [month, setMonth] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/challenges', { month, description });
            setMonth('');
            setDescription('');
            onChallengeAdded();
        } catch (error) {
            console.error("Error adding Challelnge", error);
        }
    }

    return (
        <div className="card my-5">
            <div className="card-header">Add New Challenge</div>
            <div className="card-body">
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="month" className="form-label">Month</label>
                        <input type="text" id="month" className="form-control" placeholder="e.g. January" value={month} onChange={(e) => setMonth(e.target.value)} required />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="description" className="form-label">description</label>
                        <textarea id="description" className="form-control" placeholder="describe the challenge" value={description} onChange={(e) => setDescription(e.target.value)} required />
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default AddChallenge;