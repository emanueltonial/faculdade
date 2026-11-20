package view;

import java.util.Scanner;

import model.Produto;

public class ProdutoView {
    private Scanner entrada;

    public ProdutoView() {
        this.entrada = new Scanner(System.in);
    }

    public String solicitarNome() {
        System.out.print("Nome do produto: ");
        return entrada.nextLine();
    }

    public double solicitarPreco() {
        while (true) {
            System.out.print("Preço do produto: ");
            try {
                return Double.parseDouble(entrada.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido, digite um número (ex: 10,50).");
            }
        }
    }

    public void exibirProduto(Produto produto) {
        System.out.println("\nProduto cadastrado:");
        System.out.println("Nome: " + produto.getNome());
        System.out.printf("Preço: R$ %.2f%n", produto.getPreco());
    }
}
