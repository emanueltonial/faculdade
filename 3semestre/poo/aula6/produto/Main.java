package aula6.produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<ProdutoEstadual> estaduais = new ArrayList<>();
    static ArrayList<ProdutoNacional> nacionais = new ArrayList<>();
    static ArrayList<ProdutoImportado> importados = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarEstadual();
                case 2 -> cadastrarNacional();
                case 3 -> cadastrarImportado();
                case 4 -> exibirEstaduais();
                case 5 -> exibirNacionais();
                case 6 -> exibirImportados();
                case 7 -> exibirTodos();
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 9);

        sc.close();
    }

    static void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Cadastrar Produto Estadual");
        System.out.println("2 - Cadastrar Produto Nacional");
        System.out.println("3 - Cadastrar Produto Importado");
        System.out.println("4 - Exibir Produtos Estaduais");
        System.out.println("5 - Exibir Produtos Nacionais");
        System.out.println("6 - Exibir Produtos Importados");
        System.out.println("7 - Exibir Todos os Produtos");
        System.out.println("9 - Sair");
        System.out.print("Escolha: ");
    }

    static void cadastrarEstadual() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Valor base: ");
        double valor = Double.parseDouble(sc.nextLine());

        ProdutoEstadual p = new ProdutoEstadual(desc, valor);
        estaduais.add(p);
        System.out.println("Produto Estadual cadastrado! Valor final: R$ " + String.format("%.2f", p.calcularValorFinal()));
    }

    static void cadastrarNacional() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Valor base: ");
        double valor = Double.parseDouble(sc.nextLine());

        ProdutoNacional p = new ProdutoNacional(desc, valor);
        nacionais.add(p);
        System.out.println("Produto Nacional cadastrado! Valor final: R$ " + String.format("%.2f", p.calcularValorFinal()));
    }

    static void cadastrarImportado() {
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Valor base: ");
        double valor = Double.parseDouble(sc.nextLine());

        ProdutoImportado p = new ProdutoImportado(desc, valor);
        importados.add(p);
        System.out.println("Produto Importado cadastrado! Valor final: R$ " + String.format("%.2f", p.calcularValorFinal()));
    }

    static void exibirEstaduais() {
        if (estaduais.isEmpty()) { System.out.println("Nenhum produto estadual cadastrado."); return; }
        System.out.println("\n--- Produtos Estaduais ---");
        for (ProdutoEstadual p : estaduais) {
            p.exibirProduto();
            System.out.println("Valor Final: R$ " + String.format("%.2f", p.calcularValorFinal()));
            System.out.println("-------------------------");
        }
    }

    static void exibirNacionais() {
        if (nacionais.isEmpty()) { System.out.println("Nenhum produto nacional cadastrado."); return; }
        System.out.println("\n--- Produtos Nacionais ---");
        for (ProdutoNacional p : nacionais) {
            p.exibirProduto();
            System.out.println("Valor Final: R$ " + String.format("%.2f", p.calcularValorFinal()));
            System.out.println("-------------------------");
        }
    }

    static void exibirImportados() {
        if (importados.isEmpty()) { System.out.println("Nenhum produto importado cadastrado."); return; }
        System.out.println("\n--- Produtos Importados ---");
        for (ProdutoImportado p : importados) {
            p.exibirProduto();
            System.out.println("Valor Final: R$ " + String.format("%.2f", p.calcularValorFinal()));
            System.out.println("-------------------------");
        }
    }

    static void exibirTodos() {
        System.out.println("\n===== TODOS OS PRODUTOS =====");
        exibirEstaduais();
        exibirNacionais();
        exibirImportados();
    }
}