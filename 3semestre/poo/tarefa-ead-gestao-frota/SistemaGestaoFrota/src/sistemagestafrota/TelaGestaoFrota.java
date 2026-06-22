package sistemagestafrota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Tela principal do Sistema de Gestão de Frota.
 * Calcula o custo estimado de viagem conforme o tipo de veículo.
 *
 * Fórmulas:
 *   Carro:    Custo = (Distância / 12) * Preço do Combustível
 *   Caminhão: Custo = (Distância / 4)  * Preço do Combustível + 50
 */
public class TelaGestaoFrota extends JFrame {

    // -------------------------------------------------------------------------
    // Componentes da interface
    // -------------------------------------------------------------------------
    private JLabel lblDistancia;
    private JLabel lblPreco;
    private JLabel lblTipoVeiculo;
    private JLabel lblCustoEstimado;

    private JTextField txtDistancia;
    private JTextField txtPreco;
    private JTextField txtCustoEstimado;

    private JComboBox<String> cmbTipoVeiculo;
    private JButton btnCalcular;

    // Variável que armazena o tipo de veículo selecionado
    private String tipoVeiculoSelecionado;

    // Constantes de consumo e taxa
    private static final double CONSUMO_CARRO    = 12.0; // km/litro
    private static final double CONSUMO_CAMINHAO =  4.0; // km/litro
    private static final double TAXA_EIXO        = 50.0; // R$

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------
    public TelaGestaoFrota() {
        initComponents();
        configurarEventos();
    }

    // -------------------------------------------------------------------------
    // Inicialização dos componentes
    // -------------------------------------------------------------------------
    private void initComponents() {
        setTitle("Sistema de Gestão de Frota");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Layout principal com padding
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painelPrincipal.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ----- Linha 0: Distância -----
        lblDistancia = new JLabel("Distância da Viagem (km):");
        lblDistancia.setFont(new Font("SansSerif", Font.PLAIN, 13));

        txtDistancia = new JTextField(20);
        txtDistancia.setFont(new Font("SansSerif", Font.PLAIN, 13));

        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        painelPrincipal.add(lblDistancia, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painelPrincipal.add(txtDistancia, gbc);

        // ----- Linha 1: Preço do Combustível -----
        lblPreco = new JLabel("Preço do Combustível (R$/l):");
        lblPreco.setFont(new Font("SansSerif", Font.PLAIN, 13));

        txtPreco = new JTextField(20);
        txtPreco.setFont(new Font("SansSerif", Font.PLAIN, 13));

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painelPrincipal.add(lblPreco, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painelPrincipal.add(txtPreco, gbc);

        // ----- Linha 2: Tipo de Veículo (ComboBox) -----
        lblTipoVeiculo = new JLabel("Tipo de Veículo:");
        lblTipoVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 13));

        String[] tiposVeiculo = {"Carro", "Caminhão"};
        cmbTipoVeiculo = new JComboBox<>(tiposVeiculo);
        cmbTipoVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tipoVeiculoSelecionado = "Carro"; // valor inicial

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painelPrincipal.add(lblTipoVeiculo, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painelPrincipal.add(cmbTipoVeiculo, gbc);

        // ----- Linha 3: Botão Calcular (centralizado) -----
        btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCalcular.setBackground(new Color(70, 130, 180));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCalcular.setPreferredSize(new Dimension(110, 32));

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(btnCalcular);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotao, gbc);

        // ----- Linha 4: Custo Estimado -----
        lblCustoEstimado = new JLabel("Custo Estimado:");
        lblCustoEstimado.setFont(new Font("SansSerif", Font.BOLD, 13));

        txtCustoEstimado = new JTextField(20);
        txtCustoEstimado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtCustoEstimado.setEditable(false);          // usuário NÃO pode editar
        txtCustoEstimado.setBackground(new Color(240, 240, 240));

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painelPrincipal.add(lblCustoEstimado, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painelPrincipal.add(txtCustoEstimado, gbc);

        // Adiciona painel ao frame e ajusta tamanho
        add(painelPrincipal);
        pack();
        setMinimumSize(new Dimension(460, 250));
        setLocationRelativeTo(null); // centraliza na tela
    }

    // -------------------------------------------------------------------------
    // Configuração dos eventos
    // -------------------------------------------------------------------------
    private void configurarEventos() {

        // Evento do ComboBox: guarda o tipo de veículo selecionado
        cmbTipoVeiculo.addActionListener((ActionEvent e) -> {
            tipoVeiculoSelecionado = (String) cmbTipoVeiculo.getSelectedItem();
        });

        // Evento do botão Calcular
        btnCalcular.addActionListener((ActionEvent e) -> {
            calcularCusto();
        });

        // Permite calcular pressionando Enter nos campos
        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calcularCusto();
                }
            }
        };
        txtDistancia.addKeyListener(enterListener);
        txtPreco.addKeyListener(enterListener);
    }

    // -------------------------------------------------------------------------
    // Lógica de cálculo e validação
    // -------------------------------------------------------------------------
    private void calcularCusto() {
        // Recupera os textos dos campos
        String textoDistancia = txtDistancia.getText().trim();
        String textoPreco     = txtPreco.getText().trim().replace(",", ".");

        // Validação: campos vazios
        if (textoDistancia.isEmpty() || textoPreco.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos antes de calcular.",
                    "Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double distancia;
        double precoLitro;

        // Validação: conversão de tipos
        try {
            distancia = Double.parseDouble(textoDistancia);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Valor inválido para Distância da Viagem.\nInforme um número válido (ex: 240).",
                    "Entrada Inválida",
                    JOptionPane.ERROR_MESSAGE);
            txtDistancia.requestFocus();
            return;
        }

        try {
            precoLitro = Double.parseDouble(textoPreco);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Valor inválido para Preço do Combustível.\nInforme um número válido (ex: 6,00).",
                    "Entrada Inválida",
                    JOptionPane.ERROR_MESSAGE);
            txtPreco.requestFocus();
            return;
        }

        // Validação: valores negativos ou zero
        if (distancia <= 0) {
            JOptionPane.showMessageDialog(this,
                    "A distância da viagem deve ser maior que zero.",
                    "Valor Inválido",
                    JOptionPane.WARNING_MESSAGE);
            txtDistancia.requestFocus();
            return;
        }

        if (precoLitro <= 0) {
            JOptionPane.showMessageDialog(this,
                    "O preço do combustível deve ser maior que zero.",
                    "Valor Inválido",
                    JOptionPane.WARNING_MESSAGE);
            txtPreco.requestFocus();
            return;
        }

        // Cálculo conforme o tipo de veículo selecionado
        double custoEstimado;

        if ("Carro".equals(tipoVeiculoSelecionado)) {
            // Custo = (Distância / 12) * Preço do Combustível
            custoEstimado = (distancia / CONSUMO_CARRO) * precoLitro;
        } else {
            // Custo = (Distância / 4) * Preço do Combustível + 50
            custoEstimado = (distancia / CONSUMO_CAMINHAO) * precoLitro + TAXA_EIXO;
        }

        // Formata o resultado em moeda brasileira (R$)
        NumberFormat formatoBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        txtCustoEstimado.setText(formatoBR.format(custoEstimado));
    }
}
