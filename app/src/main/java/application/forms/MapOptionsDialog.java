package application.forms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JDialog que representa uma caixa de diálogo para configurar o mapa no jogo.
 */
public class MapOptionsDialog extends JDialog {
    private boolean bidirectional;
    private int numVertices;
    private double density;
    private boolean wasPannelCancel;

    private JComboBox<String> bidirectionalComboBox;
    private JSlider verticesSlider;
    private JSlider densitySlider;
    private JTextField verticesTextField;
    private JFormattedTextField densityTextField;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Construtor da classe MapOptionsDialog.
     *
     * @param parent O JFrame pai da caixa de diálogo.
     * @param modal  Indica se a caixa de diálogo deve ser modal.
     */
    public MapOptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.wasPannelCancel = false;

        // Adicionar um WindowListener para detectar o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // tratar o fechamento da janela
                wasPannelCancel = true;
            }
        });
    }

    /**
     * Inicializa os componentes da caixa de diálogo.
     */
    private void initComponents() {
        setTitle("Opções do Mapa");
        setLayout(null);
        setBounds(100, 100, 450, 200);

        JLabel bidirectionalLabel = new JLabel("Bidirecional:");
        bidirectionalLabel.setBounds(20, 20, 80, 20);
        add(bidirectionalLabel);

        String[] bidirectionalOptions = { "Sim", "Não" };
        bidirectionalComboBox = new JComboBox<>(bidirectionalOptions);
        bidirectionalComboBox.setBounds(120, 20, 80, 20);
        add(bidirectionalComboBox);

        JLabel verticesLabel = new JLabel("Vértices:");
        verticesLabel.setBounds(20, 50, 80, 20);
        add(verticesLabel);

        verticesSlider = new JSlider(15, 100); // Intevalo de vertices
        verticesSlider.setBounds(120, 50, 150, 25);
        verticesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numVertices = verticesSlider.getValue();
                verticesTextField.setText(String.valueOf(numVertices));
            }
        });
        add(verticesSlider);

        verticesTextField = new JTextField();
        verticesTextField.setBounds(280, 50, 50, 25); // tamanho aqui
        verticesTextField.setEditable(false);
        add(verticesTextField);

        JLabel densityLabel = new JLabel("Densidade:");
        densityLabel.setBounds(20, 80, 80, 20);
        add(densityLabel);

        densitySlider = new JSlider(50, 80); // Intervalo da densidade
        densitySlider.setBounds(120, 80, 150, 25);
        densitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                density = densitySlider.getValue() / 100.0;
                densityTextField.setText(String.format("%.2f", density));
            }
        });
        add(densitySlider);

        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        doubleFormat.setMinimumFractionDigits(2);
        doubleFormat.setMaximumFractionDigits(2);
        NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
        doubleFormatter.setValueClass(Double.class);
        doubleFormatter.setMinimum(0.00);
        doubleFormatter.setMaximum(1.00);
        densityTextField = new JFormattedTextField(doubleFormatter);
        densityTextField.setBounds(280, 80, 50, 25);
        add(densityTextField);

        okButton = new JButton("OK");
        okButton.setBounds(80, 120, 80, 30);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bidirectional = bidirectionalComboBox.getSelectedIndex() == 0;
                dispose();
            }
        });
        add(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(180, 120, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wasPannelCancel = true;

                bidirectional = false;
                dispose();
            }
        });
        add(cancelButton);

        setLocationRelativeTo(null); // Centralizar a janela na tela
    }

    /**
     * Verifica se o mapa deve ser bidirecional.
     *
     * @return true se bidirecional, false caso contrário.
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Obtém o número de vértices configurado para o mapa.
     *
     * @return O número de vértices.
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Obtém a densidade configurada para o mapa.
     *
     * @return A densidade do mapa.
     */
    public double getDensity() {
        return density;
    }

    /**
     * Verifica se o utilizador cancelou a operação na caixa de diálogo.
     *
     * @return true se o utilizador cancelou, false caso contrário.
     */
    public boolean getWasPannelCancel() {
        return wasPannelCancel;
    }
}