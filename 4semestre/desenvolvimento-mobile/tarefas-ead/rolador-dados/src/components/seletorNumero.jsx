import Botao from "./botao";

export default function SeletorNumero({ titulo, valor, funcao, passo = 1, min = null, max = null, textoAjuda = "", invalido = false, textoErro = "" }) {
  function limitar(numero) {
    if (min !== null && numero < min) return min;
    if (max !== null && numero > max) return max;
    return numero;
  }

  function ajustar(delta) {
    const atual = Number(valor);
    const base = Number.isNaN(atual) ? 0 : atual;
    funcao(String(limitar(base + delta)));
  }

  return (
    <div className="campo">
      <label className="campo__titulo">{titulo}</label>

      <div className="campo__controles">
        <Botao type="primary" text="+" funcao={() => ajustar(passo)} aria={`Aumentar ${titulo}`} />

        <input
          className={`campo__input ${invalido ? "campo__input--erro" : "campo__input--ok"}`}
          type="text"
          inputMode="numeric"
          value={valor}
          onChange={(e) => funcao(e.target.value)}
        />

        <Botao type="primary" text="−" funcao={() => ajustar(-passo)} aria={`Diminuir ${titulo}`} />
      </div>

      {invalido ? (
        <span className="campo__mensagem campo__mensagem--erro">{textoErro}</span>
      ) : (
        textoAjuda && <span className="campo__mensagem">{textoAjuda}</span>
      )}
    </div>
  );
}
