package aula4.banco;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(
        String nomeTitular, 
        String numeroConta, 
        double saldo, 
        double limite) {
        super(nomeTitular, numeroConta, saldo); // Chama construtor da superclass (Conta)
        this.limite = limite; // Está classe apenas constroi esse cara
    }

    public double getLimite() {
        return this.limite;
    }
    
    public void setLimite(double limite) {
        this.limite = limite;
    }

    // Da um override no metodo da superclass, para implementar lógica da conta corrente
    // onde o limite é importante para realizar as transações.
    @Override
    public void sacar(double valorSaque) {
        if (valorSaque <= this.saldo + this.limite) {
        saldo -= valorSaque;
    } else {
            System.out.print("Sem saldo para efetuar o saque.");
        }
    }    
    
    @Override
    public void printarInformacoes() {
        // Chama o método da superclasse
        super.printarInformacoes(); 

        // Printa os dados especificos da classe filha
        System.out.println("Limite Disponível: " + this.limite);
        System.out.println("Saldo Total (Saldo + Limite): " + (this.saldo + this.limite));
    }
}
