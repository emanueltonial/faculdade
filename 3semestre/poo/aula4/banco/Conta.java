package aula4.banco;

public class Conta {
    protected String nomeTitular;
    protected String numeroConta;
    protected double saldo;

    public Conta(
        String nomeTitular, 
        String numeroConta, 
        double saldo) {
        this.nomeTitular = nomeTitular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    // Getters e Setters
    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valorDeposito) {
        this.saldo += valorDeposito;
    }

    public void sacar(double valorSaque) {
        if (valorSaque <= this.saldo) {
            saldo -= valorSaque;
        } else {
            System.out.print("Sem saldo para efetuar o saque.");
        }
    }

    public void printarInformacoes() {
        System.out.println("Nome do titular:" + this.nomeTitular);
        System.out.println("Numero da conta:" + this.numeroConta);
        System.out.println("Saldo:" + this.saldo);
    }
}