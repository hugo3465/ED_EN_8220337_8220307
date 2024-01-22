package api.map;

import java.util.Random;

import api.DataStructures.Graph.WeightedGraph;
import api.game.interfaces.GameEntity;
import api.map.interfaces.IGameMap;
import exceptions.InvalidMapException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameMap extends WeightedGraph<GameEntity> implements IGameMap {

    private final int MIN_DISTANCE = 1;
    private final int MAX_DISTANCE = 15;

    private String mapName; // Adiciona um campo para armazenar o nome do mapa

    public GameMap() {
        super();
        this.mapName = "DefaultMap"; // Define um nome padrão
    }

    // Adiciona um construtor para permitir a criação de um mapa com um nome
    // específico
    public GameMap(String mapName) {
        super();
        this.mapName = mapName;
    }

    // Adiciona um método para obter o nome do mapa
    public String getMapName() {
        return mapName;
    }

    // Adiciona um método para definir o nome do mapa
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void generateRandomMap(int numVertices, boolean bidirectional, double density) {

        if (density < 0 || density > 1) {
            // A densidade está fora do intervalo válido (0 a 1)
            // Você pode lançar uma exceção ou tomar outra ação apropriada
            throw new IllegalArgumentException("A densidade deve estar no intervalo de 0 a 1.");
        }

        do {
            initializeGraph(numVertices);
            double totalEdges = calculateTotalEdges(numVertices, bidirectional, density);
            fillAdjacencyMatrix(numVertices, bidirectional, density, totalEdges);

            // Check if the generated map is connected
        } while (!isConnected());

    }

    private void initializeGraph(int numVertices) {
        this.vertices = new GameEntity[numVertices];
        this.adjMatrix = new double[numVertices][numVertices];
    }

    private double calculateTotalEdges(int numVertices, boolean bidirectional, double density) {
        // Fórmula geral (N*(N-1))
        double totalEdges;
        if (bidirectional) {
            // Grafo bidirecional
            // a aresta a  dividir por 2 serve para evitar a contagem dupla de arestas 
            totalEdges = (numVertices * (numVertices - 1)) * (density / 2);

        } else {
            // Grafo direcionado

            // totalEdges = (numVertices * (numVertices - 1)) * 0.5 * density;
            totalEdges = (numVertices * (numVertices - 1)) * density;

            // Garante que cada vértice tenha pelo menos uma aresta
            totalEdges += numVertices * density;
        }
        return totalEdges;
    }

    private void fillAdjacencyMatrix(int numVertices, boolean bidirectional, double density, double totalEdges) {
        Random random = new Random();
        int generatedEdges = 0;

        while (generatedEdges < totalEdges) {
            int i = random.nextInt(numVertices);
            int j = random.nextInt(numVertices);

            if (i != j && this.adjMatrix[i][j] == 0) {
                int distance = random.nextInt(MAX_DISTANCE) + MIN_DISTANCE;
                this.adjMatrix[i][j] = distance;

                if (bidirectional) {
                    this.adjMatrix[j][i] = distance;
                }

                generatedEdges++;
            }
        }

        this.numVertices = numVertices;
    }

    @Override
    public double getDistance(int fromIndex, int toIndex) {
        return super.adjMatrix[fromIndex][toIndex];
    }

    @Override
    public void importMap(String path) throws InvalidMapException, FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {

                String[] values = line.split("\\s+");

                initializeGraph(values.length);

                if (i >= adjMatrix.length) {
                    throw new InvalidMapException("O ficheiro é muito curto. Esperava menos linhas.");
                }

                for (int j = 0; j < adjMatrix[i].length; j++) {
                    try {
                        adjMatrix[i][j] = Double.parseDouble(values[j]);
                    } catch (NumberFormatException e) {
                        throw new InvalidMapException(
                                "Valor numérico inválido no ficheiro de entrada, linha " + (i + 1) + ", coluna "
                                        + (j + 1));
                    }
                }

                i++;
            }

            if (i < adjMatrix.length) {
                throw new InvalidMapException("O ficheiro é muito curto. Esperava mais linhas.");
            }

            // Define numero de vertices depois de ler todas as linhas
            this.numVertices = adjMatrix.length;

        } catch (IOException e) {
            throw new FileNotFoundException("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    @Override
    public void exportMap(String path) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    // Converte o número para String antes de escrever
                    writer.write(String.valueOf(adjMatrix[i][j]));

                    // Adiciona um espaço ou delimitador, se necessário
                    writer.write(" ");
                }

                // Adiciona uma nova linha após cada linha da matriz
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportMap'");
    }

    @Override
    public double[][] getAdjacencyMatrix() {
        return adjMatrix;
    }

    @Override
    public GameEntity[] getVertices() {
        return this.vertices;
    }

    @Override
    public GameEntity getVertice(int index) {
        if (index >= 0 && index < vertices.length) {
            return vertices[index];
        }
        throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    @Override
    public int getIndex(GameEntity entity) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(entity)) {
                return i;
            }
        }
        throw new IllegalArgumentException("O GameEntity especificado não foi encontrado no mapa.");
    }

    @Override
    public void setVertice(int index, GameEntity entety) {
        vertices[index] = entety;
    }

}