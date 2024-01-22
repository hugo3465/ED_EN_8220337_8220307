package application;

import java.io.FileNotFoundException;

import api.algorithms.LongestPathAlgorithm;
import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.map.GameMap;
import application.forms.GameInterface;
import exceptions.InvalidMapException;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        GameMap map = new GameMap("Meu mapa");
        map.generateRandomMap(15, false, 0.2);
        // map.exportMap("C:/Users/hugui/Desktop/export.txt");
        // try {
        //     map.importMap("C:/Users/hugui/Desktop/export.txt");
        // } catch (FileNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (InvalidMapException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        MovementAlgorithm algorithmA1 = new ShortestPathAlgorithm(map);
        MovementAlgorithm algorithmR1 = new RandomMovementAlgorithm(map);
        MovementAlgorithm algorithmL1 = new LongestPathAlgorithm(map);
        MovementAlgorithm algorithmA2 = new ShortestPathAlgorithm(map);
        MovementAlgorithm algorithmR2 = new RandomMovementAlgorithm(map);
        MovementAlgorithm algorithmL2 = new LongestPathAlgorithm(map);

        Flag flagPlayer1 = new Flag(1);
        Flag flagPlayer2 = new Flag(10);

        Bot bot = new Bot("Raul", algorithmA1, flagPlayer2);
        Bot bot2 = new Bot("Raul2", algorithmR1, flagPlayer2);
        Bot bot3 = new Bot("Raul3", algorithmL1, flagPlayer2);
        Bot bot4 = new Bot("Casemiro", algorithmA2, flagPlayer1);
        Bot bot5 = new Bot("Casemiro2", algorithmR2, flagPlayer1);
        Bot bot6 = new Bot("Casemiro3", algorithmL2, flagPlayer1);

        // Bot[] botsPlayer1 = { bot, bot2, bot3 };
        // Bot[] botsPlayer2 = { bot4, bot5, bot6 };

        Bot[] botsPlayer1 = { bot };
        Bot[] botsPlayer2 = { bot4 };

        Player player1 = new Player("Hugo", flagPlayer1, flagPlayer2, botsPlayer1);
        Player player2 = new Player("Pedro", flagPlayer2, flagPlayer1, botsPlayer2);

        CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);


        Thread gameInterface = new Thread(new GameInterface(game));
        gameInterface.start();

        // do {
        // //System.out.println(algorithm.getNextMovement(0, 5));
        // bot.move();
        // bot2.move();
        // scanner.nextLine();
        // Thread.sleep(1000);
        // } while (true);

    }
}
