package application;

import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.map.GameMap;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        GameMap map = new GameMap("Meu mapa");
        map.generateRandomMap(15, false, 0.4);
        map.exportMap("C:/Users/hugui/Desktop/export.txt");

        ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(map);
        RandomMovementAlgorithm algorithm2 = new RandomMovementAlgorithm(map);
        ShortestPathAlgorithm algorithm3 = new ShortestPathAlgorithm(map);
        RandomMovementAlgorithm algorithm4 = new RandomMovementAlgorithm(map);
        
        

        Flag flagPlayer1 = new Flag(1);
        Flag flagPlayer2 = new Flag(15);

        Bot bot = new Bot("Raul", algorithm, flagPlayer2);
        Bot bot2 = new Bot("Raul2", algorithm2, flagPlayer2);
        Bot bot3 = new Bot("Casemiro", algorithm3, flagPlayer1);
        Bot bot4 = new Bot("Casemiro2", algorithm4, flagPlayer1);

        

        Bot[] botsPlayer1 = {bot, bot2};
        Bot[] botsPlayer2 = {bot3, bot4};

        Player player1 = new Player("Hugo", flagPlayer1, flagPlayer2, botsPlayer1);
        Player player2 = new Player("Pedro", flagPlayer2, flagPlayer1, botsPlayer2);

        CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

       game.startGame(player1, player2);
        

        // do {
        //     //System.out.println(algorithm.getNextMovement(0, 5));
        //     bot.move();
        //     bot2.move();
        //     scanner.nextLine();
        //     Thread.sleep(1000);
        // } while (true);
        
    }
}
