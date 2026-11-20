import javax.swing.SwingUtilities;

import controller.ProdutoController;
import view.ProdutoView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoView view = new ProdutoView();
            ProdutoController controller = new ProdutoController(view);
            controller.iniciar();
        });
    }
}
