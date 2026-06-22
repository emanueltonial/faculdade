package aula3;

public class Livro extends Produto{
    private String autor;
    private int paginas;

    public Livro(String nome, double preco, String autor, int paginas){
        super(nome, preco); 
        this.autor = autor;
        this.paginas = paginas;
    }

    // Getters e Setters
    public String getAutor(){ 
        return autor; 
    }
    
    public void setAutor(String autor){ 
        this.autor = autor; 
    }

    public int getPaginas(){ 
        return paginas; 
    }
    
    public void setPaginas(int paginas){ 
        this.paginas = paginas; 
    }

    public boolean ehGrande(){
        return this.paginas > 200;
    }
    
    @Override
    public String toString() {
        return "Livro: " + getNome() + " | Autor: " + autor + " | Páginas: " + paginas;
    }
}