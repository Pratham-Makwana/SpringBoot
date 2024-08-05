import logo from './logo.svg';
import './App.css';
import ChallengeList from './Componets/ChallengeList';
import { useEffect, useState } from 'react';
import axios from 'axios';
import AddChallenge from './Componets/AddChallenge';

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
    <div className="App">
      <h1>Monthly Challenge</h1>
      <AddChallenge onChallengeAdded={handleChallengeAdded} />
      <ChallengeList challenges={challenges} />
    </div>
  );
}

export default App;
