package application;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.Player;
import api.map.GameMap;
import application.forms.BotOptionsDialog;
import application.forms.MapOptionsDialog;
import exceptions.InvalidMapException;

public class game extends javax.swing.JFrame {

    public GameMap map;
    public Player player1;
    public Player player2;
    public Bot[] botsPlayer1;
    public Bot[] botsPlayer2;

    /**
     * Creates new form game
     */
    public game() {
        initComponents();

        this.map = new GameMap();
        this.player1 = null;
        this.player2 = null;
        this.botsPlayer1 = null;
        this.botsPlayer2 = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        startGame = new javax.swing.JButton();
        Player1Name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        numBots = new java.awt.Choice();
        Player2Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        InserirBotsPlayer1 = new javax.swing.JButton();
        InserirBotsPlayer2 = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        LabelBots1 = new javax.swing.JLabel();
        LabelBots2 = new javax.swing.JLabel();
        importButtom = new javax.swing.JButton();
        exportButtom1 = new javax.swing.JButton();
        generateButtom2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startGame.setText("Novo Jogo");
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });

        Player1Name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome Jogador1");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome Jogador2");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bots por player");

        InserirBotsPlayer1.setText("Inserir Bots ");
        InserirBotsPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserirBotsPlayer1ActionPerformed(evt);
            }
        });

        InserirBotsPlayer2.setText("Inserir Bots ");
        InserirBotsPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserirBotsPlayer2ActionPerformed(evt);
            }
        });

        warningLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        warningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningLabel.setToolTipText("");

        LabelBots1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelBots1.setText("bots inseridos");

        LabelBots2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelBots2.setText("bots inseridos");

        importButtom.setText("Importar Mapa");
        importButtom.setActionCommand("");
        importButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtomActionPerformed(evt);
            }
        });

        exportButtom1.setText("Exportar Mapa");
        exportButtom1.setActionCommand("");
        exportButtom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtom1ActionPerformed(evt);
            }
        });

        generateButtom2.setText("Gerar Mapa");
        generateButtom2.setActionCommand("");
        generateButtom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtom2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(LabelBots1, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(InserirBotsPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        94, Short.MAX_VALUE)
                                                .addComponent(Player1Name)))
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(exportButtom1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 109,
                                                                Short.MAX_VALUE)
                                                        .addComponent(generateButtom2,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(144, 144, 144)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(jLabel2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 94,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(InserirBotsPlayer2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(Player2Name))
                                                        .addComponent(LabelBots2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(65, 65, 65))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(numBots,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(importButtom)
                                                        .addComponent(jLabel3,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startGame, javax.swing.GroupLayout.DEFAULT_SIZE, 134,
                                                Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Player1Name,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Player2Name,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(21, 21, 21)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(InserirBotsPlayer2,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(InserirBotsPlayer1)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(numBots, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(importButtom)
                                                .addGap(18, 18, 18)
                                                .addComponent(generateButtom2)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(LabelBots1)
                                                .addComponent(exportButtom1))
                                        .addComponent(LabelBots2))
                                .addGap(18, 18, 18)
                                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26,
                                        Short.MAX_VALUE)
                                .addComponent(startGame)
                                .addGap(25, 25, 25)));

        numBots.add("1");
        numBots.add("2");
        numBots.add("3");
        numBots.add("4");
        LabelBots1.setVisible(false);
        LabelBots2.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startGameActionPerformed
        String namePlayer1 = Player1Name.getText();
        String namePlayer2 = Player2Name.getText();
        if (map != null && botsPlayer1 != null && botsPlayer2 != null && namePlayer1 != "" && namePlayer2 != "") {
            player1 = new Player(namePlayer1, null, null, botsPlayer1);
            player2 = new Player(namePlayer2, null, null, botsPlayer2);
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel inicair o jogo porque ainda ha campos por responder",
                    "Falta de Informacao",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_startGameActionPerformed

    private void importButtomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_importButtomActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(null);
        File path = null;

        try {
            if (returnVal == jFileChooser1.APPROVE_OPTION) {
                // abrir janela para escolher o ficheiro
                path = jFileChooser1.getSelectedFile();

                // importar mapa
                map.importMap(path.getAbsolutePath());

                // mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Mapa importado com sucesso", "Mapa importado",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar o mapa: " + e.getMessage(), "Erro de Importação",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InvalidMapException e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar o mapa: " + e.getMessage(), "Erro de Importação",
                    JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_importButtomActionPerformed

    private void exportButtom1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exportButtom1ActionPerformed
        int returnVal = jFileChooser1.showSaveDialog(null);

        if (map != null && returnVal == jFileChooser1.APPROVE_OPTION) {
            File selectedFile = jFileChooser1.getSelectedFile();

            // Certifique-se de que a extensão do arquivo seja .txt
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".txt")) {
                selectedFile = new File(filePath + ".txt");
            }

            try {
                map.exportMap(selectedFile.getAbsolutePath());
                JOptionPane.showMessageDialog(null, "Mapa exportado com sucesso!", "Exportação Concluída",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao exportar o mapa: " + e.getMessage(), "Erro de Exportação",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_exportButtom1ActionPerformed

    private void generateButtom2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_generateButtom2ActionPerformed
        MapOptionsDialog optionsDialog = new MapOptionsDialog(this, true);
        optionsDialog.setVisible(true);

        // Opções selecionadas
        boolean bidirectional = optionsDialog.isBidirectional();
        int numVertices = optionsDialog.getNumVertices();
        double density = optionsDialog.getDensity();

        try {
            map.generateRandomMap(numVertices, bidirectional, density);

            // mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Mapa gerado com sucesso", "Mapa gerado",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o mapa: " + e.getMessage(), "Erro de geração",
                    JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_generateButtom2ActionPerformed

    private void InserirBotsPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_InserirBotsPlayer1ActionPerformed
        boolean operationCanceled = false;
        Integer numeroBots = Integer.parseInt(numBots.getSelectedItem());

        botsPlayer1 = new Bot[numeroBots];

        if (map != null) {
            for (int i = 0; i < numeroBots; i++) {
                BotOptionsDialog botOptionsDialog = new BotOptionsDialog(this, true, map);
                botOptionsDialog.setVisible(true);

                // Verifica se o diálogo foi cancelado
                if (botOptionsDialog.isDialogCanceled()) {
                    operationCanceled = true;
                    JOptionPane.showMessageDialog(null, "Operação cancelada, não serão inseridos bots",
                            "Operação cancelada",
                            JOptionPane.CANCEL_OPTION);
                    break; // Sai do loop se o utilizador cancelou
                }

                // Obtém os parametros configurados pelo utilizador
                String botName = botOptionsDialog.getName();
                MovementAlgorithm botMovementAlgorithm = botOptionsDialog.getMovementAlgorithm();

                // criar um bot para o player 1 com as informações introduzidas
                Bot newBot = new Bot(botName, botMovementAlgorithm, null); // TODO Substitua o "null" pela bandeira e
                                                                           // colocar addBot no Player
                                                                           // apropriada

                botsPlayer1[i] = newBot;
            }

            // Exibe uma mensagem de sucesso caso não se tenha cancelado a operação
            if (!operationCanceled) {
                JOptionPane.showMessageDialog(this, "Bots inseridos com sucesso", "Bot Inserido",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }// GEN-LAST:event_InserirBotsPlayer1ActionPerformed

    private void InserirBotsPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_InserirBotsPlayer2ActionPerformed
        boolean operationCanceled = false;
        Integer numeroBots = Integer.parseInt(numBots.getSelectedItem());

        botsPlayer2 = new Bot[numeroBots];

        if (map != null) {
            for (int i = 0; i < numeroBots; i++) {
                BotOptionsDialog botOptionsDialog = new BotOptionsDialog(this, true, map);
                botOptionsDialog.setVisible(true);

                // Verifica se o diálogo foi cancelado
                if (botOptionsDialog.isDialogCanceled()) {
                    operationCanceled = true;
                    JOptionPane.showMessageDialog(null, "Operação cancelada, não serão inseridos bots",
                            "Operação cancelada",
                            JOptionPane.CANCEL_OPTION);
                    break; // Sai do loop se o utilizador cancelou
                }

                // Obtém os parametros configurados pelo utilizador
                String botName = botOptionsDialog.getName();
                MovementAlgorithm botMovementAlgorithm = botOptionsDialog.getMovementAlgorithm();

                // criar um bot para o player 1 com as informações introduzidas
                Bot newBot = new Bot(botName, botMovementAlgorithm, null); // TODO Substitua o "null" pela bandeira e
                                                                           // colocar addBot no Player
                                                                           // apropriada
                botsPlayer2[i] = newBot;
            }

            // Exibe uma mensagem de sucesso caso não se tenha cancelado a operação
            if (!operationCanceled) {
                JOptionPane.showMessageDialog(this, "Bots inseridos com sucesso", "Bot Inserido",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }// GEN-LAST:event_InserirBotsPlayer2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InserirBotsPlayer1;
    private javax.swing.JButton InserirBotsPlayer2;
    private javax.swing.JLabel LabelBots1;
    private javax.swing.JLabel LabelBots2;
    private javax.swing.JTextField Player1Name;
    private javax.swing.JTextField Player2Name;
    private javax.swing.JButton exportButtom1;
    private javax.swing.JButton generateButtom2;
    private javax.swing.JButton importButtom;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private java.awt.Choice numBots;
    private javax.swing.JButton startGame;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
