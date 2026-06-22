package aula5.empresa;

public class Teste {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario();
        f1.setNome("Jose Antonio");
        f1.setCpf("1111");
        f1.setSalario(1000);
        System.out.println("Funcionario");
        System.out.println("Nome: " + f1.getNome());
        System.out.println("Cpf: " + f1.getCpf());
        System.out.println("Salário: " + f1.getSalario());
        System.out.println("Bonificação: " + f1.calcularBonificacao());

        Gerente f2 = new Gerente();
        f2.setNome("Pedro Henrique");
        f2.setCpf("2222");
        f2.setSenha(4321);
        f2.setSalario(5000);
        if (f2.autentica(4321)) {
            System.out.println("Gerente");
            System.out.println("Nome: " + f2.getNome());
            System.out.println("Cpf: " + f2.getCpf());
            System.out.println("Salário: " + f2.getSalario());
            System.out.println("Bonificação: " + f2.calcularBonificacao());
        } else {
            System.out.println("A senha está incorreta");
        }

        // Demonstração de polimorfismo:
        Gerente gerente = new Gerente();
        Funcionario funcionario = gerente; // referência como Funcionario
        funcionario.setSalario(5000.0);
        // A chamada abaixo invocará o getBonificacao() do objeto Gerente em tempo de execução:
        System.out.println("Bonificação via referência Funcionario: " + funcionario.calcularBonificacao());

        // Uso do ControleDeBonificacoes
        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        controle.registra(f1); 
        controle.registra(f2); 
        System.out.println("O total de bonificações é: " + controle.getTotalDeBonificacoes());
    }
}