import controller.ProdutoController;
import view.ProdutoView;

public class Main {
    public static void main(String[] args) {
        ProdutoView view = new ProdutoView();
        ProdutoController controller = new ProdutoController(view);
        controller.cadastrarProduto();
    }
}
