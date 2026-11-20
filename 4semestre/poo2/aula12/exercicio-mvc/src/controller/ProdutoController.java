package controller;

import model.Produto;
import view.ProdutoView;

public class ProdutoController {
    private ProdutoView view;

    public ProdutoController(ProdutoView view) {
        this.view = view;
    }

    public void cadastrarProduto() {
        String nome = view.solicitarNome();
        double preco = view.solicitarPreco();
        Produto produto = new Produto(nome, preco);
        view.exibirProduto(produto);
    }
}
