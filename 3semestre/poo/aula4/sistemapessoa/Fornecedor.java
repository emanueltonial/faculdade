package aula4.sistemapessoa;

public class Fornecedor extends Pessoa {
    private double valorCredito;
    private double valorDivida;

    public Fornecedor(
        String nomePessoa,
        String endereco, 
        String telefone, 
        double valorCredito, 
        double valorDivida
    ) {
        super(nomePessoa, endereco, telefone);
        this.valorCredito = valorCredito;
        this.valorDivida = valorDivida;
    }

    // Getters e Setters
    public double getValorCredito() {
        return this.valorCredito;
    }

    public double getValorDivida() {
        return this.valorDivida;
    }

    public void setValorCredito(double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public void setValorDivida(double valorDivida) {
    this.valorDivida = valorDivida;
    }

    public double obterSaldo() {
        return valorCredito - valorDivida;
    }


    @Override
    public void printarInformacoes() {
        super.printarInformacoes();
        System.out.println("Valor de Crédito: " + this.valorCredito);
        System.out.println("Valor da Dívida: " + this.valorDivida);
        System.out.println("Saldo Final: " + this.obterSaldo());
    }

}
