package application;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

import api.DataStructures.ArrayList.UnorderedArrayList.ConvertibleUnnorderedArrayList;
import api.algorithms.LongestPathAlgorithm;
import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.map.GameMap;
import application.forms.BotOptionsDialog;
import application.forms.GameInterface;
import application.forms.MapOptionsDialog;
import exceptions.InvalidMapException;

public class App extends javax.swing.JFrame {

    public GameMap map;
    public Player player1;
    public Player player2;
    public Flag flagPlayer1;
    public Flag flagPlayer2;
    public Bot[] botsPlayer1;
    public Bot[] botsPlayer2;

    private int minVertex = 1, maxVertex = 0;

    /**
     * Creates new form game
     */
    public App() {
        initComponents();

        this.map = new GameMap();
        this.player1 = null;
        this.player2 = null;
        this.flagPlayer1 = null;
        this.flagPlayer2 = null;
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
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        importButtom = new javax.swing.JButton();
        exportButtom1 = new javax.swing.JButton();
        generateButtom2 = new javax.swing.JButton();
        PlaceFlagPlayer1 = new javax.swing.JButton();
        PlaceFlagPlayer2 = new javax.swing.JButton();

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
        InserirBotsPlayer1.setEnabled(false);
        InserirBotsPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserirBotsPlayer1ActionPerformed(evt);
            }
        });

        InserirBotsPlayer2.setText("Inserir Bots ");
        InserirBotsPlayer2.setEnabled(false);
        InserirBotsPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InserirBotsPlayer2ActionPerformed(evt);
            }
        });

        warningLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        warningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningLabel.setToolTipText("");

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

        PlaceFlagPlayer1.setText("Colocar Bandeira");
        PlaceFlagPlayer1.setEnabled(false);
        PlaceFlagPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaceFlagPlayer1ActionPerformed(evt);
            }
        });

        PlaceFlagPlayer2.setText("Colocar Bandeira");
        PlaceFlagPlayer2.setEnabled(false);
        PlaceFlagPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaceFlagPlayer2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Player1Name)
                                        .addComponent(InserirBotsPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PlaceFlagPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(exportButtom1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addComponent(importButtom,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(generateButtom2,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(numBots,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(InserirBotsPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PlaceFlagPlayer2, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Player2Name, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(48, 48, 48))
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
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(Player1Name,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(generateButtom2)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Player2Name,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(numBots, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(PlaceFlagPlayer1)
                                                        .addComponent(PlaceFlagPlayer2))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InserirBotsPlayer1)
                                                        .addComponent(InserirBotsPlayer2)))
                                        .addComponent(importButtom))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportButtom1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15,
                                        Short.MAX_VALUE)
                                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(startGame)
                                .addGap(25, 25, 25)));

        numBots.add("1");
        numBots.add("2");
        numBots.add("3");
        numBots.add("4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlaceFlagPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PlaceFlagPlayer1ActionPerformed
        boolean flagPlaced = false;
        while (!flagPlaced) {

            try {
                // solicitar ao utilizador para que insira a sua bandeira
                int startingVertex = Integer.parseInt(
                        JOptionPane.showInputDialog(this, "Insira o vértice onde a sua bandeira vai ficar."));

                // Verifica se o valor está dentro do intervalo desejado
                if (startingVertex >= minVertex && startingVertex <= maxVertex) {
                    // Verifica se o valor é diferente de flagPlayer2
                    if (flagPlayer2 == null || startingVertex != (flagPlayer2.getIndex() + 1)) {

                        flagPlayer1 = new Flag(startingVertex);

                        // mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Bandeira introduzida com sucesso", "Bandeira inserida",
                                JOptionPane.INFORMATION_MESSAGE);

                        // Colocar o botão de inserir bots como ativado
                        InserirBotsPlayer1.setEnabled(true);
                        flagPlaced = true;
                    }else {
                        // Mensagem de erro se o valor for igual a flagPlayer1
                        JOptionPane.showMessageDialog(null,
                                "Erro: O valor deve ser diferente do vértice da bandeira do Player 2",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // Mensagem de erro se o valor não estiver dentro do intervalo
                    JOptionPane.showMessageDialog(null,
                            "Erro: O valor deve estar entre " + minVertex + " e " + maxVertex, "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(startingVertex);
                    flagPlaced = false;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_PlaceFlagPlayer1ActionPerformed

    private void PlaceFlagPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_PlaceFlagPlayer2ActionPerformed
        boolean flagPlaced = false;
        while (!flagPlaced) {

            try {
                // solicitar ao utilizador para que insira a sua bandeira
                int startingVertex = Integer.parseInt(
                        JOptionPane.showInputDialog(this, "Insira o vértice onde a sua bandeira vai ficar."));
                        
                // Verifica se o valor está dentro do intervalo desejado
                if (startingVertex >= minVertex && startingVertex <= maxVertex) {
                    // Verifica se o valor é diferente de flagPlayer1
                    if (flagPlayer1 == null || startingVertex != (flagPlayer1.getIndex() + 1)) {

                        flagPlayer2 = new Flag(startingVertex);

                        // mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Bandeira introduzida com sucesso", "Bandeira inserida",
                                JOptionPane.INFORMATION_MESSAGE);

                        // Colocar o botão de inserir bots como ativado
                        InserirBotsPlayer2.setEnabled(true);
                        flagPlaced = true;
                    } else {
                        // Mensagem de erro se o valor for igual a flagPlayer1
                        JOptionPane.showMessageDialog(null,
                                "Erro: O valor deve ser diferente do vértice da bandeira do Player 1",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // Mensagem de erro se o valor não estiver dentro do intervalo
                    JOptionPane.showMessageDialog(null,
                            "Erro: O valor deve estar entre " + minVertex + " e " + maxVertex, "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(startingVertex);
                    flagPlaced = false;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_PlaceFlagPlayer2ActionPerformed

    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startGameActionPerformed
        String namePlayer1 = Player1Name.getText();
        String namePlayer2 = Player2Name.getText();

        // GameMap map = new GameMap("Meu mapa");
        // map.generateRandomMap(15, true, 0.4);
        // map.exportMap("C:/Users/hugui/Desktop/export.txt");

        // ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(map);
        // RandomMovementAlgorithm algorithm2 = new RandomMovementAlgorithm(map);
        // ShortestPathAlgorithm algorithm3 = new ShortestPathAlgorithm(map);
        // RandomMovementAlgorithm algorithm4 = new RandomMovementAlgorithm(map);

        // Flag flagPlayer1 = new Flag(1);
        // Flag flagPlayer2 = new Flag(15);

        // Bot bot = new Bot("Raul", algorithm, flagPlayer2);
        // Bot bot2 = new Bot("Raul2", algorithm2, flagPlayer2);
        // Bot bot3 = new Bot("Casemiro", algorithm3, flagPlayer1);
        // Bot bot4 = new Bot("Casemiro2", algorithm4, flagPlayer1);

        // Bot[] botsPlayer1 = { bot, bot2 };
        // Bot[] botsPlayer2 = { bot3, bot4 };

        // Player player1 = new Player("Hugo", flagPlayer1, flagPlayer2, botsPlayer1);
        // Player player2 = new Player("Pedro", flagPlayer2, flagPlayer1, botsPlayer2);

        // CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

        // Thread gameInterface = new Thread(new GameInterface(game));
        // // GameInterface gameInterface = new GameInterface(game);
        // gameInterface.start();

        boolean condition = map != null && botsPlayer1 != null && botsPlayer2 != null
                && !namePlayer1.isEmpty()
                && !namePlayer2.isEmpty() && flagPlayer1 != null && flagPlayer2 != null;

        if (condition) {
            player1 = new Player(namePlayer1, flagPlayer1, flagPlayer2, botsPlayer1);
            player2 = new Player(namePlayer2, flagPlayer2, flagPlayer1, botsPlayer2);

            CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

            Thread gameInterface = new Thread(new GameInterface(game));
            gameInterface.start();
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
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                // abrir janela para escolher o ficheiro
                path = jFileChooser1.getSelectedFile();

                // importar mapa
                map.importMap(path.getAbsolutePath());

                // mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Mapa importado com sucesso", "Mapa importado",
                        JOptionPane.INFORMATION_MESSAGE);

                // colocar botões como ativos
                PlaceFlagPlayer1.setEnabled(true);
                PlaceFlagPlayer2.setEnabled(true);

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

        if (map != null && returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
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
        // Experiencia
        maxVertex = numVertices;
        double density = optionsDialog.getDensity();

        try {
            map.generateRandomMap(numVertices, bidirectional, density);

            // mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Mapa gerado com sucesso", "Mapa gerado",
                    JOptionPane.INFORMATION_MESSAGE);

            // colocar botões como ativos
            PlaceFlagPlayer1.setEnabled(true);
            PlaceFlagPlayer2.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o mapa: " + e.getMessage(), "Erro de geração",
                    JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_generateButtom2ActionPerformed

    private void InserirBotsPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_InserirBotsPlayer1ActionPerformed
        botsPlayer1 = inserirBotsActionPerformed(botsPlayer1, flagPlayer2);
    }// GEN-LAST:event_InserirBotsPlayer1ActionPerformed

    private void InserirBotsPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_InserirBotsPlayer2ActionPerformed
        botsPlayer2 = inserirBotsActionPerformed(botsPlayer2, flagPlayer1);
    }// GEN-LAST:event_InserirBotsPlayer2ActionPerformed

    private Bot[] inserirBotsActionPerformed(Bot[] botsPlayer, Flag enemyFlag) {
        boolean operationCanceled = false;
        Integer numeroBots = Integer.parseInt(numBots.getSelectedItem());

        botsPlayer = new Bot[numeroBots];

        // Opções disponíveis para o algoritmo
        ConvertibleUnnorderedArrayList<String> algorithmOptions = new ConvertibleUnnorderedArrayList<>();
        addAlgorithmOptions(algorithmOptions);
        String[] options;

        if (map != null) {
            for (int i = 0; i < numeroBots; i++) {
                options = algorithmOptions.toArray(String.class);
                BotOptionsDialog botOptionsDialog = new BotOptionsDialog(this, true, map, options);
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

                // criar um bot com as informações introduzidas
                Bot newBot = new Bot(botName, botMovementAlgorithm, enemyFlag);
                botsPlayer[i] = newBot;

                // remove a opção escolhida
                removeAlgorithmOption(botMovementAlgorithm, algorithmOptions);

                if (algorithmOptions.isEmpty()) {
                    addAlgorithmOptions(algorithmOptions);
                }
            }

            // Exibe uma mensagem de sucesso caso não se tenha cancelado a operação
            if (!operationCanceled) {
                JOptionPane.showMessageDialog(this, "Bots inseridos com sucesso", "Bot Inserido",
                        JOptionPane.INFORMATION_MESSAGE);

                // desligar o painel para escolha no numero de bots para segurança
                numBots.setEnabled(false);
            }
        }

        return botsPlayer; // retorna o array modificado
    }

    private void addAlgorithmOptions(ConvertibleUnnorderedArrayList<String> algorithmOptions) {
        algorithmOptions.addToFront("Shortest Path");
        algorithmOptions.addToFront("Random Path");
        algorithmOptions.addToFront("Longest Path");
    }

    private void removeAlgorithmOption(MovementAlgorithm algorithm,
            ConvertibleUnnorderedArrayList<String> algorithmOptions) {

        if (algorithm instanceof ShortestPathAlgorithm) {
            algorithmOptions.remove("Shortest Path");
        } else if (algorithm instanceof RandomMovementAlgorithm) {
            algorithmOptions.remove("Random Path");
        } else if (algorithm instanceof LongestPathAlgorithm) {
            algorithmOptions.remove("Longest Path");
        }
    }

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InserirBotsPlayer1;
    private javax.swing.JButton InserirBotsPlayer2;
    private javax.swing.JButton PlaceFlagPlayer1;
    private javax.swing.JButton PlaceFlagPlayer2;
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
