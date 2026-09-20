import { notacaoLancamento } from "../utils";

export default function CartaHistorico({ lancamento, numero, ativo, funcao }) {
  return (
    <button type="button" className={`carta ${ativo ? "carta--ativa" : ""}`} onClick={funcao}>
      <span className="carta__numero">{numero}</span>
      <span className="carta__icone">🎲</span>

      <span className="carta__notacao">{notacaoLancamento(lancamento)}</span>

      <span className="carta__rotulo">Resultado</span>
      <span className="carta__resultado">{lancamento.resultadoFinal}</span>

      <span className="carta__rotulo">Alvo {lancamento.alvo}</span>
      <span className={`carta__status ${lancamento.atingiuAlvo ? "carta__status--ok" : "carta__status--falha"}`}>
        {lancamento.atingiuAlvo ? "SUCESSO" : "FALHA"}
      </span>
    </button>
  );
}
