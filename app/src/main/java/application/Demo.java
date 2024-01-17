package application;

import api.algorithms.ShortestPathAlgorithm;
import api.map.GameMap;

public class Demo {
    public static void main(String[] args) {
        GameMap map = new GameMap("Meu mapa");
        map.generateRandomMap(10, true, 0.8);
        map.exportMap("C:/Users/User/Desktop/export.txt");

        ShortestPathAlgorithm algorithm = new ShortestPathAlgorithm(map);

        
        System.out.println(algorithm.getNextMovement(0, 5));
    }
}
