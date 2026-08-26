const types = {
  primary: "blue",
  success: "green",
  error: "red",
  secondary: "gray",
};

const size = {
  small: {
    width: "150px",
    height: "30px",
  },
  medium: {
    width: "250px",
    height: "50px",
  },
  big: {
    width: "350px",
    height: "70px",
  },
  extra: {
    width: "400px",
    height: "100px",
  },
};

const text = {
  small: {
    fontSize: "12px",
  },
  medium: {
    fontSize: "16px",
  },
  big: {
    fontSize: "20px",
  },
  extra: {
    fontSize: "24px",
  },
};

const shadow = {
  on: "0px 4px 6px rgba(0, 0, 0, 0.3)",
  off: "none",
};

const div_centralizada = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  cursor: "pointer",
};

export default function Botao({ type, size: tamanho, nome, sombra }) {
  const style = {
    ...div_centralizada,
    backgroundColor: types[type],
    color: "white",
    width: size[tamanho].width,
    height: size[tamanho].height,
    fontSize: text[tamanho].fontSize,
    boxShadow: shadow[sombra],
  };

  return (
    <div style={style} onClick={() => alert(nome)}>
      {nome}
    </div>
  );
}
