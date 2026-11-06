package aula1;

public class Item {
  // atributos
  // variavel de instancia = variavel global (visivel em toda a classe)
  private String codigo;
  private String descricao;
  private int quantidade;
  private double desconto;
  private double valorUnitario;

  // metodos
  // metodo construtor
  public Item(String codigo, String descricao, int quantidade, double desconto, double valorUnitario) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.quantidade = quantidade;
    this.desconto = desconto;
    this.valorUnitario = valorUnitario;
  }
  
  public double calcularValor() {
    double valor = this.quantidade * this.valorUnitario;
    double valorDesconto = calcularValorDesconto();
    valor = valor - valorDesconto;
    return valor;
  }

  public double calcularValorDesconto() {
    double valor = this.quantidade * this.valorUnitario;
    double valorDesconto = valor * (this.desconto / 100);
    return valorDesconto;
  }

  // getters
  public String getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public double getDesconto() {
    return desconto;
  }

  public double getValorUnitario() {
    return valorUnitario;
  }

  // setter
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void setDesconto(double desconto) {
    this.desconto = desconto;
  }

  public void setValorUnitario(double valorUnitario) {
    this.valorUnitario = valorUnitario;
  }
}
