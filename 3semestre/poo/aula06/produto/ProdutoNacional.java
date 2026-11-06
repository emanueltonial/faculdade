package aula6.produto;

public class ProdutoNacional extends Produto {
    private double imposto;
    private double taxa;
    
    public ProdutoNacional(String descricao, double valor) {
        super(descricao, valor);
        this.imposto = 0.10;
        this.taxa = 0.05;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    } 

    @Override
    public double calcularValorFinal() {
        // Considera a taxa e imposto para produtos nacionais
        return this.valor + (this.valor * this.imposto) + (this.valor * this.taxa);
    }
    
    @Override
    public void exibirProduto() {
        super.exibirProduto();
        System.out.println("Tipo de produto: Nacional");
        System.out.println("Imposto: " + getImposto());
        System.out.println("Taxa: " + getTaxa());
    }    
}
