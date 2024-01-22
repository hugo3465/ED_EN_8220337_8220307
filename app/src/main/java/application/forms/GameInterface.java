package application.forms;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;

import api.game.Bot;
import api.game.Player;
import api.game.interfaces.ICaptureTheFlag;

public class GameInterface implements Runnable {
    private JFrame frame;
    private JTextArea textArea;

    private ICaptureTheFlag game;

    public GameInterface(ICaptureTheFlag game) {
        frame = new JFrame("Capture the flag");
        textArea = new JTextArea(10, 30);

        textArea.setEnabled(false);
        frame.add(new JScrollPane(textArea));
        frame.setSize(400, 700);
        frame.setVisible(true);

        this.game = game;
    }

    private void addText(String message) {
        textArea.append("\n" + message + "\n");
    }

    public void close() {
        frame.dispose();
    }

    @Override
    public void run() {
        int round = 1;
        Player currentPlayer = game.getCurrentPlayer();
        Bot currentBot = null;
        Timer timer = new Timer(2000, null); // 2000 milliseconds (2 seconds)

        do {
            currentPlayer = game.nextTurn();

            addText("-------------Ronda " + round + ": -------------");
            System.out.println("-------------Ronda " + round + ": -------------");

            // addText(currentPlayer.getname() + " tinha o bot " + currentBot.getName() + "
            // no vertice "
            // + (currentBot.getPosition() + 1));

            currentBot = game.playRound(currentPlayer);

            // Imprimir visualização do mapa após cada rodada
            addText(currentPlayer.getname() + " moveu o bot " + currentBot.getName() + " foi para o vertice "
                    + (currentBot.getIndex() + 1));

            System.out.println(currentPlayer.getname() + " moveu o bot " + currentBot.getName() + " foi para o vertice "
                    + (currentBot.getIndex() + 1));

            System.out.println(game.getGameMap().getMap());
            addText(game.getGameMap().getMap());

            try {
                // Aguardar 2 segundos antes da próxima rodada
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (game.isGameOver() != -1) {
                // Stop the timer when the game is over
                timer.stop();
            }
            round++;
        } while (game.isGameOver() == -1);

        showEndGameMessages(currentPlayer);

    }

    private void showEndGameMessages(Player currentPlayer) {
        // Exibir mensagem de fim de jogo
        System.out.println("Fim de jogo!!!");
        addText("Fim de jogo!!!");

        switch (game.isGameOver()) {
            case -1:
                break;
            case 0:
                addText("\n-----Empate------\n");
                System.out.println("\n-----Empate------\n");
                break;
            case 1:
            case 2:
                System.out.println("\n--------------WINNER: " + currentPlayer.getname() + " ---------------\n");
                addText("\n--------------WINNER: " + currentPlayer.getname() + " ---------------\n");
                break;

            default:
                System.out.println("Não é suposto vir para aqui");
                addText("Não é suposto vir para aqui");
                break;
        }
    }
}
