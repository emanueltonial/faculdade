import "./styles.css";

import Botao from "./components/Botao";

export default function App() {
  const x = [1, 2, "r"];
  return (
    <div className="App">
      <h1>Hello {x}</h1>
      <Botao nome="Bom dia " type="primary" size="small" sombra="on" />
      <Botao nome="Boa tarde" type="success" size="medium" sombra="off" />
      <Botao nome="Boa noite" type="error" size="big" sombra="on" />
      <Botao nome="Boa madrugada" type="secondary" size="extra" sombra="on" />
    </div>
  );
}
