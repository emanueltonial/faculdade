package aula4.sistemapessoa;

public class Empregado extends Pessoa {
    private int codigoSetor;
    private double impostos;
    private double salarioBase;
    
    public Empregado(String nomePessoa, String endereco, String telefone, int codigoSetor, double salarioBase,
            double impostos) {
        super(nomePessoa, endereco, telefone);
        this.codigoSetor = codigoSetor;
        this.salarioBase = salarioBase;
        this.impostos = impostos;
    }
    public int getCodigoSetor() {
        return codigoSetor;
    }

    public void setCodigoSetor(int codigoSetor) {
        this.codigoSetor = codigoSetor;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    
    public double getImpostos() {
        return impostos;
    }

    public void setImpostos(double impostos) {
        this.impostos = impostos;
    }

    public double calcularSalario() {
        return this.salarioBase - this.impostos;
    }

    @Override
    public void printarInformacoes() {
        super.printarInformacoes();
        System.out.println("Codigo setor: " + this.codigoSetor);
        System.out.println("Salario base: " + this.salarioBase);
        System.out.println("Impostos: " + this.impostos);
    }
}
