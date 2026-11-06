
import java.util.ArrayList;


public class ServicoProduto {
    private ArrayList<Produto> lista = new ArrayList();
    
    public ServicoProduto() {
        
        
    }
    
    public void adicionarProduto(Produto p) {
        lista.add(p);
    }
    
    public Produto buscarProduto(int i) {
        return lista.get(i - 1);   
    }
    
    public void removerProduto(int i) {
        lista.remove(i - 1);
    }
    
    public ArrayList<Produto> listarProduto() {
        return lista;
    }
    
    public void limparLista() {
        lista.clear();
    }
}   