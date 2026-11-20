package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * View: responsavel apenas pela interface grafica.
 * Nao contem regra de negocio e nao conhece o Model nem o Controller.
 */
public class ProdutoView extends JFrame {
    private JTextField tfdDesc;
    private JTextField tfdPreco;
    private JTextField tfdQuant;
    private JButton btnCalcular;
    private JLabel lblResultado;

    public ProdutoView() {
        setTitle("Cadastro de Produtos");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Descricao"));
        tfdDesc = new JTextField();
        add(tfdDesc);

        add(new JLabel("Preco"));
        tfdPreco = new JTextField();
        add(tfdPreco);

        add(new JLabel("Quantidade"));
        tfdQuant = new JTextField();
        add(tfdQuant);

        btnCalcular = new JButton("Calcular total");
        add(btnCalcular);

        lblResultado = new JLabel("Total: R$ 0,00");
        add(lblResultado);
    }

    public String getDescricao() {
        return tfdDesc.getText();
    }

    public String getPreco() {
        return tfdPreco.getText();
    }

    public String getQuantidade() {
        return tfdQuant.getText();
    }

    public void setResultado(String resultado) {
        lblResultado.setText(resultado);
    }

    /** Permite que o Controller registre a acao do botao sem expor o componente. */
    public void addCalcularListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
