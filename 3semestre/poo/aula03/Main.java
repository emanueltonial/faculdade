package aula3;

public class Main {
    public static void main(String[] args){
        Livro livro = new Livro("O matador", 150, "emanuel", 75);
        
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Preço: " + livro.getPreco());
        System.out.println("Páginas pré-lançamento");
        livro.setPaginas(350);
        System.out.println("Livro: " + livro.getNome());
    }
}
