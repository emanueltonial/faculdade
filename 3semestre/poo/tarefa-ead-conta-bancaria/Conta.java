public class Conta {

    private int numero;
    private String dono;
    private double saldo;
    private double limite;

    // Construtor
    public Conta(int numero, String dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    // Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    // Método sacar: permite saque se saldo - valor >= -limite
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return false;
        }
        if ((saldo - valor) >= -limite) {
            saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado. Novo saldo: R$ %.2f%n", valor, saldo);
            return true;
        } else {
            System.out.printf("Saque de R$ %.2f negado. Saldo insuficiente (saldo: R$ %.2f, limite: R$ %.2f)%n",
                    valor, saldo, limite);
            return false;
        }
    }

    // Método depositar
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido.");
            return;
        }
        saldo += valor;
        System.out.printf("Depósito de R$ %.2f realizado. Novo saldo: R$ %.2f%n", valor, saldo);
    }

    @Override
    public String toString() {
        return String.format("Conta{numero=%d, dono='%s', saldo=R$ %.2f, limite=R$ %.2f}",
                numero, dono, saldo, limite);
    }
}