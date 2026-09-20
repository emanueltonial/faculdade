// posicoes das bolinhas do D6 numa grade 3x3 (indices 0 a 8)
const PIPS = {
  1: [4],
  2: [0, 8],
  3: [0, 4, 8],
  4: [0, 2, 6, 8],
  5: [0, 2, 4, 6, 8],
  6: [0, 2, 3, 5, 6, 8],
};

function FaceComPips({ valor }) {
  const ativos = PIPS[valor] || [];

  return (
    <div className="dado__pips">
      {Array.from({ length: 9 }, (_, i) => (
        <span key={i} className={`pip ${ativos.includes(i) ? "pip--ativo" : ""}`} />
      ))}
    </div>
  );
}

export default function DadoResultado({ valor, faces }) {
  const ehMinimo = valor === 1;
  const ehMaximo = valor === faces;

  let critico = "";
  if (ehMinimo) critico = "critico--min";
  if (ehMaximo) critico = "critico--max";

  return (
    <div className="dado">
      {ehMinimo && <span className="dado__selo dado__selo--min">MIN</span>}
      {ehMaximo && <span className="dado__selo dado__selo--max">MAX</span>}

      <div className={`dado__face ${critico}`}>
        {faces === 6 ? <FaceComPips valor={valor} /> : <span className="dado__numero">{valor}</span>}
      </div>

      <span className={`dado__valor ${critico}`}>[{valor}]</span>
    </div>
  );
}
