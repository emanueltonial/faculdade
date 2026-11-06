package aula6.produto;

public class ProdutoImportado extends Produto {
    private double imposto;
    private double taxa;
    private double taxaImportacao;
    
    public ProdutoImportado(String descricao, double valor) {
        super(descricao, valor);
        this.imposto = 0.10;
        this.taxa = 0.05;
        this.taxaImportacao = 0.05;
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

    public double getTaxaImportacao() {
        return taxaImportacao;
    }

    public void setTaxaImportacao(double taxaImportacao) {
        this.taxaImportacao = taxaImportacao;
    }

    @Override
    public double calcularValorFinal() {
        // Considera o imposto, taxa e taxa de importação para produtos importados
        return this.valor + (this.valor * this.imposto) + (this.valor * this.taxa) + (this.valor * this.taxaImportacao);
    }
    
    @Override
    public void exibirProduto() {
        super.exibirProduto();
        System.out.println("Tipo de produto: Importado");
        System.out.println("Imposto : " + getImposto());
        System.out.println("Taxa: " + getTaxa());
        System.out.println("Taxa de importação: " + getTaxaImportacao());
    }
}
