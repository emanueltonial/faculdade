package aula6.produto;

public class Produto {
    protected String descricao;
    protected double valor;

    public Produto(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double calcularValorFinal() {
        return this.valor;
    }

    public void exibirProduto() {
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Valor: " + getValor());        
    }
}
