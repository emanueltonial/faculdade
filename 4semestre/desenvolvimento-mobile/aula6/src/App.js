import {useEffect, useState} from "react";
import "./styles.css";

export default function App() {
  const [v, setV] = useState(0);
  const [load,setLoad] = useState(0);
  
  useEffect(() => {
    setLoad(localStorage.getItem("banana"))
  }, []);

  useEffect (() => {
    setV(parseInt(load));
  }, [load]);

  return (
    <div a="a" b={10} className="App">
      <button 
        onClick={() => {
          setV(v + 1);
        }
        }>Clique
      </button>
      <h1>{v}</h1>
      <h1>Valor carregado do Storage: {load}</h1>
      <h2>Start editing to see some magic happen!</h2>
      <button onClick={() => localStorage.setItem('banana', v)}>save</button>
    </div>
  );
}
