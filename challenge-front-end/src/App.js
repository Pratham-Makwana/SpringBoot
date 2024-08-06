import logo from './logo.svg';
import './App.css';
import ChallengeList from './Componets/ChallengeList';
import { useEffect, useState } from 'react';
import axios from 'axios';
import AddChallenge from './Componets/AddChallenge';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {

  const [challenges, setChallenges] = useState([]);

  useEffect(() => {
    fetchChallenge();
  }, []);

  const fetchChallenge = async () => {
    try {
      const response = await axios.get('http://localhost:8080/challenges');
      setChallenges(response.data);
    } catch (error) {
      console.error("Error Fetching challenges: ", error);
    }

  };

  const handleChallengeAdded = () =>{
    fetchChallenge();
  }

  return (
    <div className="container m-5">
      <h1 className='text-center mb-4'>Monthly Challenge</h1>
      <AddChallenge onChallengeAdded={handleChallengeAdded} />
      <ChallengeList challenges={challenges} />
    </div>
  );
}

export default App;
