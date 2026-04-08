package aula4.sistemapessoa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String resposta = "sim";

        while (resposta.equalsIgnoreCase("sim")) {
            System.out.println("Deseja criar: 1 - Pessoa | 2 - Fornecedor | 3 - Empregado");
            int criar = s.nextInt();
            s.nextLine();

            if (criar == 1) {
                System.out.print("Nome: ");
                String nome = s.nextLine();
                System.out.print("Endereço: ");
                String endereco = s.nextLine();
                System.out.print("Telefone: ");
                String telefone = s.nextLine();

                Pessoa pessoa = new Pessoa(nome, endereco, telefone);
                System.out.println("====");
                pessoa.printarInformacoes();

            } else if (criar == 2) {
                System.out.print("Nome: ");
                String nome = s.nextLine();
                System.out.print("Endereço: ");
                String endereco = s.nextLine();
                System.out.print("Telefone: ");
                String telefone = s.nextLine();
                System.out.print("Valor de Crédito: ");
                double valorCredito = s.nextDouble();
                System.out.print("Valor de Dívida: ");
                double valorDivida = s.nextDouble();
                s.nextLine();

                Fornecedor fornecedor = new Fornecedor(nome, endereco, telefone, valorCredito, valorDivida);
                System.out.println("====");
                fornecedor.printarInformacoes();

            } else if (criar == 3) {
                System.out.print("Nome: ");
                String nome = s.nextLine();
                System.out.print("Endereço: ");
                String endereco = s.nextLine();
                System.out.print("Telefone: ");
                String telefone = s.nextLine();
                System.out.print("Código do Setor: ");
                int codigoSetor = s.nextInt();
                System.out.print("Salário Base: ");
                double salarioBase = s.nextDouble();
                System.out.print("Impostos: ");
                double impostos = s.nextDouble();
                s.nextLine();

                Empregado empregado = new Empregado(nome, endereco, telefone, codigoSetor, salarioBase, impostos);
                System.out.println("====");
                empregado.printarInformacoes();
                System.out.println("Salário Líquido: " + empregado.calcularSalario());
            } else {
                System.out.println("Opção inválida.");
            }

            System.out.print("\nDeseja continuar? (sim/não): ");
            resposta = s.nextLine();
        }

        System.out.println("Programa encerrado.");
        s.close();
    }
}