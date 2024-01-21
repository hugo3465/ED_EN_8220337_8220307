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

        while (game.isGameOver() == -1) {
            addText("-------------Ronda " + round + ": -------------");

            // addText(currentPlayer.getname() + " tinha o bot " + currentBot.getName() + "
            // no vertice "
            // + (currentBot.getPosition() + 1));

            currentBot = game.playRound(currentPlayer);

            // Imprimir visualização do mapa após cada rodada
            addText(currentPlayer.getname() + " moveu o bot " + currentBot.getName() + " foi para o vertice "
                    + (currentBot.getPosition() + 1));

            currentPlayer = game.nextTurn();

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
        }
    }
}