package application.forms;

import javax.swing.*;

import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.map.GameMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotOptionsDialog extends JDialog {
    private String name;
    private MovementAlgorithm movementAlgorithm;
    private GameMap map;
    private boolean dialogCanceled;

    private JTextField nameTextField;
    private JComboBox<String> algorithmComboBox;
    private JButton okButton;
    private JButton cancelButton;

    public BotOptionsDialog(java.awt.Frame parent, boolean modal, GameMap map) {
        super(parent, modal);
        this.map = map;
        initComponents();

    }

    private void initComponents() {
        setTitle("Opções do Bot");
        setLayout(null);
        setBounds(100, 100, 400, 200);

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(20, 20, 80, 20);
        add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 20, 200, 20);
        add(nameTextField);

        JLabel algorithmLabel = new JLabel("Algoritmo de Movimento:");
        algorithmLabel.setBounds(20, 50, 150, 20);
        add(algorithmLabel);

        String[] algorithmOptions = { "Shortest Path", "Random Path" };
        algorithmComboBox = new JComboBox<>(algorithmOptions);
        algorithmComboBox.setBounds(180, 50, 140, 20);
        add(algorithmComboBox);

        okButton = new JButton("OK");
        okButton.setBounds(80, 90, 80, 30);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();

                // com base na opcao vai instanciar o algoritmo
                if (algorithmComboBox.getSelectedIndex() == 0) {
                    movementAlgorithm = new ShortestPathAlgorithm(map);
                } else if (algorithmComboBox.getSelectedIndex() == 1) {
                    movementAlgorithm = new RandomMovementAlgorithm(map);
                }

                dispose();
            }
        });
        add(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(180, 90, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // para saber se o utilizador cancelou a operação
                dialogCanceled = true;
                dispose();
            }
        });
        add(cancelButton);

        setLocationRelativeTo(null); // Centralizar a janela na tela
    }

    public String getName() {
        return name;
    }

    public MovementAlgorithm getMovementAlgorithm() {
        return movementAlgorithm;
    }

    public boolean isDialogCanceled() {
        return dialogCanceled;
    }
}