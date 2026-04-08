package aula3;

public class Produto {
    // Com protected as classes que herdam tem acesso
    protected String nome;
    protected double preco;

    public Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
    }

    public String getNome(){
        return nome;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public boolean ehCaro() {
        return (preco > 100);
    }
}
    


