package controller;

import java.util.Locale;

import model.Produto;
import view.ProdutoView;

/**
 * Controller: intermedia a comunicacao entre View e Model.
 * Recebe os eventos da View, cria/consulta o Model e atualiza a View.
 */
public class ProdutoController {
    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private ProdutoView view;

    public ProdutoController(ProdutoView view) {
        this.view = view;
        this.view.addCalcularListener(e -> calcularTotal());
    }

    public void iniciar() {
        view.setVisible(true);
    }

    private void calcularTotal() {
        try {
            String descricao = view.getDescricao();
            double preco = Double.parseDouble(view.getPreco().trim().replace(",", "."));
            int quantidade = Integer.parseInt(view.getQuantidade().trim());

            Produto produto = new Produto(descricao, preco, quantidade);
            double total = produto.calcularTotal();

            view.setResultado(String.format(PT_BR, "Total: R$ %.2f", total));
        } catch (NumberFormatException e) {
            view.mostrarMensagem("Preco e quantidade devem ser numeros validos.");
        } catch (IllegalArgumentException e) {
            view.mostrarMensagem(e.getMessage());
        }
    }
}
