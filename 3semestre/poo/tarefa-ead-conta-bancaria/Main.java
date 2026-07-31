
public class Main {

    public static void main(String[] args) {

        // Instanciando dois objetos
        Conta conta1 = new Conta(1001, "Alice", 250.00, 200.00);
        Conta conta2 = new Conta(1002, "Bruno", 1000.00, 500.00);

        // Exibindo saldos iniciais
        System.out.println("=== Saldos Iniciais ===");
        System.out.printf("Conta 1 - %s: R$ %.2f%n", conta1.getDono(), conta1.getSaldo());
        System.out.printf("Conta 2 - %s: R$ %.2f%n", conta2.getDono(), conta2.getSaldo());

        System.out.println();

        // Operações
        System.out.println("=== Operações ===");
        conta1.sacar(500.00);
        conta2.depositar(1700.00);

        System.out.println();

        // Exibindo saldos finais
        System.out.println("=== Saldos Finais ===");
        System.out.printf("Conta 1 - %s: R$ %.2f%n", conta1.getDono(), conta1.getSaldo());
        System.out.printf("Conta 2 - %s: R$ %.2f%n", conta2.getDono(), conta2.getSaldo());
    }
}

