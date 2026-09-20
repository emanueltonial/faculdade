export const TIPOS_DE_DADO = [4, 6, 8, 10, 12, 20, 100];

export function sortearDado(faces) {
  return Math.floor(Math.random() * faces) + 1;
}

export function notacaoLancamento({ quantidade, faces, modificador }) {
  let texto = `${quantidade}D${faces}`;
  if (modificador > 0) texto += `+${modificador}`;
  if (modificador < 0) texto += `${modificador}`;
  return texto;
}

export function criarLancamento({ quantidade, faces, modificador, alvo }) {
  const dados = Array.from({ length: quantidade }, () => sortearDado(faces));
  const somaBase = dados.reduce((soma, valor) => soma + valor, 0);
  const resultadoFinal = somaBase + modificador;

  return {
    id: Date.now() + Math.random(),
    quantidade,
    faces,
    modificador,
    alvo,
    dados,
    somaBase,
    resultadoFinal,
    atingiuAlvo: resultadoFinal >= alvo,
  };
}

export function validarNumero(texto, { min = null, max = null, permiteNegativo = false } = {}) {
  const limpo = String(texto).trim();

  if (limpo === "") return { valido: false, erro: "Preencha o campo" };

  const numero = Number(limpo);

  if (Number.isNaN(numero)) return { valido: false, erro: "Apenas números" };
  if (!Number.isInteger(numero)) return { valido: false, erro: "Sem casas decimais" };
  if (!permiteNegativo && numero < 0) return { valido: false, erro: "Não pode ser negativo" };
  if (min !== null && numero < min) return { valido: false, erro: `Mínimo ${min}` };
  if (max !== null && numero > max) return { valido: false, erro: `Máximo ${max}` };

  return { valido: true, erro: "" };
}
