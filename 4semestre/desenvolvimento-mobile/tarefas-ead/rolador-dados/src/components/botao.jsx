export default function Botao({ type = "primary", text, icone, funcao, largura = "auto", desabilitado = false, aria }) {
  const variantes = {
    primary: "botao--primary",
    secondary: "botao--secondary",
    success: "botao--success",
    error: "botao--error",
  };

  return (
    <button
      type="button"
      className={`botao ${variantes[type]}`}
      style={{ width: largura }}
      onClick={funcao}
      disabled={desabilitado}
      aria-label={aria}
    >
      {icone && <span className="botao__icone">{icone}</span>}
      {text && <span>{text}</span>}
    </button>
  );
}
