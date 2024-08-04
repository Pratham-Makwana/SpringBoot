
import './App.css';
import ChallengeList from './components/ChallengeList'
import { useState } from 'react';

function App() {

  const [challenges,setChalleges] = useState([
    {id:1,month:'january',description:'First Challenge description'},
    {id:2,month:'February',description:'Second Challenge description'}
  ]);
  return (
    <div className="App">
      <h1>Montly Challenges</h1>
      <ChallengeList challenges={challenges}/>
    </div>
  );
}

export default App;
