package aula6.produto;

public class ProdutoEstadual extends Produto {
    private double imposto;
    
    public ProdutoEstadual(String descricao, double valor) {
        super(descricao, valor);
        this.imposto = 0.10; // 10% por padrão que o ex cita
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    @Override
    public double calcularValorFinal() {
        // Considera o imposto para produtos estaduais
        return this.valor + (this.valor * this.imposto);
    }
    
    @Override
    public void exibirProduto() {
        super.exibirProduto();
        System.out.println("Tipo de produto: Estadual");
        System.out.println("Imposto: " + getImposto());
    }
}
