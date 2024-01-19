package application;

import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        GameMap map = new GameMap("Meu mapa");
        map.generateRandomMap(10, true, 0.8);
        map.exportMap("C:/Users/User/Desktop/export.txt");

        ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(map);
        RandomMovementAlgorithm algorithm2 = new RandomMovementAlgorithm(map);
        
        

        Bot bot = new Bot("Raul", algorithm, new Flag(5));
        Bot bot2 = new Bot("Raul", algorithm2, new Flag(5));
        bot.setPosition(0);
        bot2.setPosition(0);

        do {
            //System.out.println(algorithm.getNextMovement(0, 5));
            bot.move();
            bot2.move();
            scanner.nextLine();
            Thread.sleep(1000);
        } while (true);
        
    }
}
