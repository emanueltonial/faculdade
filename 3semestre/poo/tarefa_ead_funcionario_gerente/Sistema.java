package tarefa_ead_funcionario_gerente;
import java.util.Scanner;

public class Sistema {
    private Funcionario[] funcionarios = new Funcionario[50];
    private int contador = 0; 
    private Scanner sc = new Scanner(System.in);

    public void executarMenu() {
        int opcao = 0;
        
        do {
            System.out.println("\n--- MENU DO SISTEMA ---");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Exibir a Lista de Funcionários");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 9:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    private void cadastrar() {
        if (contador < 50) {
            Funcionario f = new Funcionario();
            
            System.out.print("Nome: ");
            f.setNome(sc.nextLine());
            
            System.out.print("CPF: ");
            f.setCpf(sc.nextLine());
            
            System.out.print("Salário: ");
            f.setSalario(sc.nextDouble());
            
            funcionarios[contador] = f;
            contador++;
            
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Limite de 50 posições excedido.");
        }
    }

    private void listar() {
        if (contador == 0) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE FUNCIONÁRIOS ---");
        for (int i = 0; i < contador; i++) {
            Funcionario f = funcionarios[i];
            System.out.println("Nome: " + f.getNome() + 
                               " | CPF: " + f.getCpf() + 
                               " | Salário: R$ " + f.getSalario() + 
                               " | Bonificação: R$ " + f.calcularBonificacao());
        }
    }
}