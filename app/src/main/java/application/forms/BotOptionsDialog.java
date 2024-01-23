package application.forms;

import javax.swing.*;

import api.algorithms.LongestPathAlgorithm;
import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.map.GameMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JDialog que representa uma caixa de diálogo para configurar os bots do jogo.
 */
public class BotOptionsDialog extends JDialog {
    private String name;
    private MovementAlgorithm movementAlgorithm;
    private GameMap map;
    private String[] algorithmOptions;
    private boolean dialogCanceled;

    private JTextField nameTextField;
    private JComboBox<String> algorithmComboBox;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Construtor da classe BotOptionsDialog.
     *
     * @param parent           O JFrame pai da caixa de diálogo.
     * @param modal            Indica se a caixa de diálogo deve ser modal.
     * @param map              O mapa do jogo.
     * @param algorithmOptions As opções de algoritmos disponíveis.
     */
    public BotOptionsDialog(java.awt.Frame parent, boolean modal, GameMap map, String[] algorithmOptions) {
        super(parent, modal);
        this.map = map;
        this.algorithmOptions = algorithmOptions;
        this.dialogCanceled = false;
        
        initComponents();

        // Adicionar um WindowListener para detectar o fechamento da janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // tratar o fechamento da janela
                dialogCanceled = true;
            }
        });

    }

    /**
     * Inicializa os componentes da caixa de diálogo.
     */
    private void initComponents() {
        setTitle("Opções do Bot");
        setLayout(null);
        setBounds(100, 100, 400, 200);

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(20, 20, 80, 20);
        add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 20, 200, 25);
        add(nameTextField);

        JLabel algorithmLabel = new JLabel("Algoritmo de Movimento:");
        algorithmLabel.setBounds(20, 50, 150, 25);
        add(algorithmLabel);

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
                if (algorithmComboBox.getSelectedItem().equals("Shortest Path")) {
                    movementAlgorithm = new ShortestPathAlgorithm(map);
                } else if (algorithmComboBox.getSelectedItem().equals("Random Path")) {
                    movementAlgorithm = new RandomMovementAlgorithm(map);
                } else if (algorithmComboBox.getSelectedItem().equals("Longest Path")) {
                    movementAlgorithm = new LongestPathAlgorithm(map);
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

    /**
     * Obtém o nome do bot.
     *
     * @return O nome do bot.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o algoritmo de movimento configurado para o bot.
     *
     * @return O algoritmo de movimento do bot.
     */
    public MovementAlgorithm getMovementAlgorithm() {
        return movementAlgorithm;
    }

    /**
     * Verifica se o utilizador cancelou a operação na caixa de diálogo.
     *
     * @return true se o utilizador cancelou, false caso contrário.
     */
    public boolean isDialogCanceled() {
        return dialogCanceled;
    }
}