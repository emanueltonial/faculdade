import { useState } from "react";

import "./styles.css";

import { TIPOS_DE_DADO, criarLancamento } from "./utils";
import { validarNumero } from "./utils";

import Botao from "./components/botao";
import SeletorNumero from "./components/seletorNumero";
import DadoResultado from "./components/dadoResultado";
import CartaHistorico from "./components/cartaHistorico";

export default function App() {
  // campos digitaveis ficam como texto para validar a digitacao; a conversao acontece no lancamento
  const [quantidade, setQuantidade] = useState("3");
  const [faces, setFaces] = useState(6);
  const [modificador, setModificador] = useState("2");
  const [alvo, setAlvo] = useState("12");

  const [historico, setHistorico] = useState([]);
  const [indiceAtivo, setIndiceAtivo] = useState(0);

  const validacaoQuantidade = validarNumero(quantidade, { min: 1, max: 100 });
  const validacaoModificador = validarNumero(modificador, { permiteNegativo: true });
  const validacaoAlvo = validarNumero(alvo, { permiteNegativo: true });

  const formularioValido = validacaoQuantidade.valido && validacaoModificador.valido && validacaoAlvo.valido;

  function lancarDados() {
    if (!formularioValido) return;

    const novoLancamento = criarLancamento({
      quantidade: Number(quantidade),
      faces,
      modificador: Number(modificador),
      alvo: Number(alvo),
    });

    setHistorico([novoLancamento, ...historico].slice(0, 10));
    setIndiceAtivo(0);
  }

  const lancamento = historico[indiceAtivo];

  return (
    <div className="app">
      <header className="cabecalho">
        <span className="cabecalho__logo">🎲</span>
        <h1 className="cabecalho__titulo">Rolador de Dados</h1>
      </header>

      <main className="conteudo">
        <section className="painel painel--config">
          <h2 className="painel__titulo">Configuração do lançamento</h2>

          <div className="config__grade">
            <SeletorNumero
              titulo="Quantidade de dados"
              valor={quantidade}
              funcao={setQuantidade}
              min={1}
              max={100}
              textoAjuda="Apenas números, mínimo 1"
              invalido={!validacaoQuantidade.valido}
              textoErro={validacaoQuantidade.erro}
            />

            <div className="campo">
              <label className="campo__titulo">Tipo de dado</label>

              <select className="campo__select" value={faces} onChange={(e) => setFaces(Number(e.target.value))}>
                {TIPOS_DE_DADO.map((f) => (
                  <option key={f} value={f}>D{f}</option>
                ))}
              </select>

              <div className="tipos-rapidos">
                {TIPOS_DE_DADO.map((f) => (
                  <button
                    key={f}
                    type="button"
                    className={`tipo-rapido ${f === faces ? "tipo-rapido--ativo" : ""}`}
                    onClick={() => setFaces(f)}
                  >
                    D{f}
                  </button>
                ))}
              </div>
            </div>

            <SeletorNumero
              titulo="Modificador"
              valor={modificador}
              funcao={setModificador}
              textoAjuda="Aceita números positivos ou negativos"
              invalido={!validacaoModificador.valido}
              textoErro={validacaoModificador.erro}
            />

            <SeletorNumero
              titulo="Valor alvo"
              valor={alvo}
              funcao={setAlvo}
              textoAjuda="Atingido quando o resultado final for maior ou igual"
              invalido={!validacaoAlvo.valido}
              textoErro={validacaoAlvo.erro}
            />
          </div>

          <Botao type="primary" text="Lançar dados" icone="🎲" largura="100%" funcao={lancarDados} desabilitado={!formularioValido} />
        </section>

        <section className="painel painel--resultado">
          <h2 className="painel__titulo">Último lançamento</h2>

          {!lancamento ? (
            <div className="resultado-vazio">
              <span className="resultado-vazio__icone">🎲</span>
              <p>Nenhum lançamento ainda.</p>
              <p>Configure os dados e clique em <strong>Lançar dados</strong>.</p>
            </div>
          ) : (
            <div className="resultado">
              <div className="resultado__dados">
                {lancamento.dados.map((valor, i) => (
                  <DadoResultado key={i} valor={valor} faces={lancamento.faces} />
                ))}
              </div>

              <div className="resultado__contas">
                <p>
                  Soma base: <strong>{lancamento.somaBase}</strong>{" "}
                  <span className="suave">({lancamento.dados.join(" + ")})</span>
                </p>
                <p>
                  Modificador:{" "}
                  <strong>{lancamento.modificador >= 0 ? `+${lancamento.modificador}` : lancamento.modificador}</strong>
                </p>
                <p className="resultado__final">
                  Resultado final: <strong>{lancamento.resultadoFinal}</strong>
                </p>
              </div>

              <div className={`banner ${lancamento.atingiuAlvo ? "banner--sucesso" : "banner--erro"}`}>
                {lancamento.atingiuAlvo ? "ALVO ATINGIDO! ✓" : "ALVO NÃO ATINGIDO ✗"}
              </div>
            </div>
          )}
        </section>
      </main>

      <section className="painel">
        <h2 className="painel__titulo">Histórico de lançamentos</h2>

        {historico.length === 0 ? (
          <p className="historico-vazio">O histórico aparece aqui após o primeiro lançamento.</p>
        ) : (
          <div className="carrossel">
            <Botao
              type="secondary"
              text="‹"
              funcao={() => setIndiceAtivo(Math.max(0, indiceAtivo - 1))}
              desabilitado={indiceAtivo === 0}
              aria="Mais recente"
            />

            <div className="carrossel__trilha">
              {historico.map((item, i) => (
                <CartaHistorico
                  key={item.id}
                  lancamento={item}
                  numero={i + 1}
                  ativo={i === indiceAtivo}
                  funcao={() => setIndiceAtivo(i)}
                />
              ))}
            </div>

            <Botao
              type="secondary"
              text="›"
              funcao={() => setIndiceAtivo(Math.min(historico.length - 1, indiceAtivo + 1))}
              desabilitado={indiceAtivo === historico.length - 1}
              aria="Mais antigo"
            />
          </div>
        )}
      </section>
    </div>
  );
}
