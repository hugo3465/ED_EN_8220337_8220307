package application.forms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class MapOptionsDialog extends JDialog {
    private boolean bidirectional;
    private int numVertices;
    private double density;

    private JComboBox<String> bidirectionalComboBox;
    private JSlider verticesSlider;
    private JSlider densitySlider;
    private JTextField verticesTextField;
    private JFormattedTextField densityTextField;
    private JButton okButton;
    private JButton cancelButton;

    public MapOptionsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Opções do Mapa");
        setLayout(null);
        setBounds(100, 100, 450, 200);
    
        JLabel bidirectionalLabel = new JLabel("Bidirecional:");
        bidirectionalLabel.setBounds(20, 20, 80, 20);
        add(bidirectionalLabel);
    
        String[] bidirectionalOptions = {"Sim", "Não"};
        bidirectionalComboBox = new JComboBox<>(bidirectionalOptions);
        bidirectionalComboBox.setBounds(120, 20, 80, 20);
        add(bidirectionalComboBox);
    
        JLabel verticesLabel = new JLabel("Vértices:");
        verticesLabel.setBounds(20, 50, 80, 20);
        add(verticesLabel);
    
        verticesSlider = new JSlider(5, 100); // Intevalo de vertices
        verticesSlider.setBounds(120, 50, 150, 20);
        verticesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numVertices = verticesSlider.getValue();
                verticesTextField.setText(String.valueOf(numVertices));
            }
        });
        add(verticesSlider);
    
        verticesTextField = new JTextField();
        verticesTextField.setBounds(280, 50, 50, 20); // tamanho aqui
        verticesTextField.setEditable(false);
        add(verticesTextField);
    
        JLabel densityLabel = new JLabel("Densidade:");
        densityLabel.setBounds(20, 80, 80, 20);
        add(densityLabel);
    
        densitySlider = new JSlider(0, 100); // Intervalo da densidade
        densitySlider.setBounds(120, 80, 150, 20);
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
        densityTextField.setBounds(280, 80, 50, 20);
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
                bidirectional = false;
                dispose();
            }
        });
        add(cancelButton);
    
        setLocationRelativeTo(null); // Centralizar a janela na tela
    }

    public boolean isBidirectional() {
        return bidirectional;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public double getDensity() {
        return density;
    }
}