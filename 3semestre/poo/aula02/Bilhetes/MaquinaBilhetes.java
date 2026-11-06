package aula2.Bilhetes;
/**
 * Representa uma máquina de venda de bilhetes de trem.
 * Gerencia o preço do bilhete, o saldo inserido pelo cliente
 * e o total de dinheiro coletado pela máquina.
 */
public class MaquinaBilhetes {
    private double preco;
    private double saldo;
    private double total;

    
    public MaquinaBilhetes(double preco){
        this.preco = preco;
        this.saldo = 0;
        this.total = 0;
    }

    // region getters
    public double getPreco(){
        return preco;
    }

    public double getSaldo(){
        return saldo;
    }

    public double getTotal(){
        return total;
    }
    
    // region setters
    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public void setTotal(double total){
        this.total = total;
    }

    // region metódos
    public void inserirDinheiro(double valor){
        this.saldo = saldo + valor;
    }

    public void imprimirBilhete(){
        System.out.println("###################");
        System.out.println("Bilhete de trem");
        System.out.println("# Preço R$: " + this.preco + " #");
        System.out.println("###################");

        this.total = this.total + this.preco;
        this.saldo = this.saldo - this.preco;
    }
    

}

