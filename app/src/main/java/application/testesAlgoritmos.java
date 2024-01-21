package application;

import api.algorithms.LongestPathAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;

public class testesAlgoritmos {
    public static void main(String[] args) {
        GameMap map = new GameMap();
        map.generateRandomMap(5, true, 0.6);
        map.exportMap("C:/Users/User/Desktop/cosa.txt");

        MovementAlgorithm algorithm = new LongestPathAlgorithm(map);
        Bot bot = new Bot("bot1", algorithm, new Flag(4));

        algorithm.getNextMovement(0, 4, bot);
    }
}
