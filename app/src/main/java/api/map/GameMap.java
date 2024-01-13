package api.map;

import java.util.Random;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.map.interfaces.IGameMap;

public class GameMap extends WeightedGraph<Integer> implements IGameMap {
    /**
     * Mapa do jogo
     * O identificador do nó é um inteiro
     */
    public NetworkADT<Integer> map;

    public GameMap() {
        super();
    }

    public void generateRandomMap(int numLocations, boolean bidirectional, double density) {
        Random random = new Random();
        this.map = new WeightedGraph<>();

        // adjacencyMatrix = new int[numLocations][numLocations];
        // distances = new int[numLocations];

        // // Preenche a matriz de adjacências com arestas aleatórias baseadas na densidade
        // for (int i = 0; i < numLocations; i++) {
        //     for (int j = 0; j < numLocations; j++) {
        //         if (i != j && random.nextDouble() < density) {
        //             int distance = random.nextInt(15) + 1; // Distância aleatória entre 1 e 15 quilômetros
        //             adjacencyMatrix[i][j] = distance;

        //             if (bidirectional) {
        //                 adjacencyMatrix[j][i] = distance;
        //             }
        //         }
        //     }
        // }
    }

    @Override
    public double getDistance(int fromIndex, int toIndex) {
        return super.adjMatrix[fromIndex][toIndex];
    }

    @Override
    public void importMap(String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importMap'");
    }

    @Override
    public void exportMap(String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportMap'");
    }

    @Override
    public NetworkADT<Integer> getMap() {
        return this.map;
    }
}