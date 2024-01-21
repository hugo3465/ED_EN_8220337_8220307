package application;

import api.algorithms.LongestPathAlgorithm;
import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.map.GameMap;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        GameMap map = new GameMap("Meu mapa");
        map.generateRandomMap(100, true, 0.1);
        map.exportMap("C:/Users/hugui/Desktop/export.txt");

        MovementAlgorithm algorithmA1 = new ShortestPathAlgorithm(map);
        MovementAlgorithm algorithmR1 = new RandomMovementAlgorithm(map);
        MovementAlgorithm algorithmL1 = new LongestPathAlgorithm(map);
        MovementAlgorithm algorithmA2 = new ShortestPathAlgorithm(map);
        MovementAlgorithm algorithmR2 = new RandomMovementAlgorithm(map);
        MovementAlgorithm algorithmL2 = new LongestPathAlgorithm(map);

        Flag flagPlayer1 = new Flag(1);
        Flag flagPlayer2 = new Flag(60);

        Bot bot = new Bot("Raul", algorithmA1, flagPlayer2);
        Bot bot2 = new Bot("Raul2", algorithmR1, flagPlayer2);
        Bot bot3 = new Bot("Raul3", algorithmL1, flagPlayer2);
        Bot bot4 = new Bot("Casemiro", algorithmA2, flagPlayer1);
        Bot bot5 = new Bot("Casemiro2", algorithmR2, flagPlayer1);
        Bot bot6 = new Bot("Casemiro3", algorithmL2, flagPlayer1);

        Bot[] botsPlayer1 = { bot, bot2, bot3 };
        Bot[] botsPlayer2 = { bot4, bot5, bot6 };

        Player player1 = new Player("Hugo", flagPlayer1, flagPlayer2, botsPlayer1);
        Player player2 = new Player("Pedro", flagPlayer2, flagPlayer1, botsPlayer2);

        CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

        int round = 1;
        Bot currentBot = null;
        Player currentPlayer = game.getCurrentPlayer();
        while (game.isGameOver() == -1) {
            System.out.println("-------------Ronda " + round + ": -------------");

            // addText(currentPlayer.getname() + " tinha o bot " + currentBot.getName() + "
            // no vertice "
            // + (currentBot.getPosition() + 1));

            currentBot = game.playRound(currentPlayer);

            // Imprimir visualização do mapa após cada rodada
            System.out.println(currentPlayer.getname() + " moveu o bot " + currentBot.getName() + " foi para o vertice "
                    + currentBot.getPosition());

            currentPlayer = game.nextTurn();


            try {
                // Aguardar 2 segundos antes da próxima rodada
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            round++;
        }

        // Exibir mensagem de fim de jogo
        System.out.println("Fim de jogo!!!");
        System.out.println("--------------WINNER: " + currentPlayer.getname() + " ---------------");

        // do {
        // //System.out.println(algorithm.getNextMovement(0, 5));
        // bot.move();
        // bot2.move();
        // scanner.nextLine();
        // Thread.sleep(1000);
        // } while (true);

    }
}
