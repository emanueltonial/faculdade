package model;

public class Produto {
    private String descricao;
    private double preco;
    private int quantidade;

    public Produto(String descricao, double preco, int quantidade) {
        setDescricao(descricao);
        setPreco(preco);
        setQuantidade(quantidade);
    }

    public double calcularTotal() {
        return preco * quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descricao nao pode ser vazia.");
        }
        this.descricao = descricao.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preco nao pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }
}
