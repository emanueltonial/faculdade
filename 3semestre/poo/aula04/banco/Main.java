package aula4.banco;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta("João Feijão", "123", 1000.0);
        
        System.out.println("Titular: " + conta.getNomeTitular());
        System.out.println("Número:  " + conta.getNumeroConta());
        System.out.println("Saldo: " + conta.getSaldo());

        conta.setNomeTitular("Ana Paula Silva");
        conta.setNumeroConta("3232");
        conta.setSaldo(1500.0);

        System.out.print("==================================================");
        System.out.println("\nApós setters");
        System.out.print("==================================================");
        conta.printarInformacoes();

        conta.depositar(500.0);
        System.out.println("Saldo após depositar R$500" + conta.getSaldo());

        conta.sacar(200.0);
        System.out.println("Saldo após sacar R$200:" +  conta.getSaldo());

        ContaCorrente cc = new ContaCorrente("Carlii", "2424", 500.0, 1000.0);

        System.out.println("Titular: " + cc.getNomeTitular());   
        System.out.println("Número:  " + cc.getNumeroConta());   
        System.out.println("Saldo: " + cc.getSaldo());      
        System.out.println("Limite: " + cc.getLimite());     

        cc.setNomeTitular("Carlao2");
        cc.setNumeroConta("2525");
        cc.setSaldo(800.0);
        cc.setLimite(1500.0);

        System.out.print("==================================================");
        System.out.println("\nApós setters");
        System.out.print("==================================================");
        cc.printarInformacoes();

        cc.sacar(2000.0); 
        System.out.println("Saldo após sacar R$2000: " + cc.getSaldo());

    }
}