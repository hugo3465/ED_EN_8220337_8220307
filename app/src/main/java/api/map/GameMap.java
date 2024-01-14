package api.map;

import java.util.Random;

import api.DataStructures.Graph.WeightedGraph;
import api.game.Bot;
import api.game.Entity;
import api.game.Flag;
import api.game.Position;
import api.map.interfaces.IGameMap;
import exceptions.InvalidMapException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameMap extends WeightedGraph<Entity> implements IGameMap {

    private final int MIN_DISTANCE = 1;
    private final int MAX_DISTANCE = 15;

    private String mapName; // Adiciona um campo para armazenar o nome do mapa

    public GameMap() {
        super();
        this.mapName = "DefaultMap"; // Define um nome padrão
    }
    // Adiciona um construtor para permitir a criação de um mapa com um nome específico
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

        initializeGraph(numVertices);

        // fillVertices(numVertices); // Preenche os vértices, se calhar nem precisa
        // disto

        // Calcula o número total de arestas com base na densidade para um grafo
        // direcionado
        double totalEdges = calculateTotalEdges(numVertices, bidirectional, density);

        // Preenche a matriz de adjacências com arestas baseadas na densidade
        fillAdjacencyMatrix(numVertices, bidirectional, density, totalEdges);

        // TODO VERIFICAR SE ESTÁ CONECTADO

    }

    private void initializeGraph(int numVertices) {
        this.vertices = new Entity[numVertices];
        this.adjMatrix = new double[numVertices][numVertices];
    }

    // private void fillVertices(int numVertices) {
    // for (int i = 0; i < numVertices; i++) {
    // this.vertices[i] = String.valueOf(i); // TODO: reformular isto maybe para
    // ficar com bots
    // }
    // }

    private double calculateTotalEdges(int numVertices, boolean bidirectional, double density) {
        double totalEdges;
        if (bidirectional) {
            totalEdges = (numVertices * (numVertices - 1)) * 0.5 * density;
        } else {
            totalEdges = (numVertices - 1) * density;

            if (numVertices > 2) {
                totalEdges += (numVertices - 2) * density;
            }
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
    }

    @Override
    public double getDistance(int fromIndex, int toIndex) {
        return super.adjMatrix[fromIndex][toIndex];
    }

    @Override
    public void importMap(String path) throws InvalidMapException, FileNotFoundException {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            line = reader.readLine();

            String[] values = line.split("\\s+");
            initializeGraph(numVertices);

            for (int i = 0; i < adjMatrix.length; i++) {

                // TODO fazer a revisão lá com aquela fórmula do stor
                if (line == null) {
                    throw new InvalidMapException("O ficheiro é muito curto. Esperava mais linhas.");
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
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    @Override
    public void exportMap(String path) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {


            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    // Convert int to String before writing
                    writer.write(String.valueOf((int) adjMatrix[i][j])); // TODO COLOCAR PARA DOUBLE

                    // Add a space or delimiter if needed
                    writer.write(" ");
                }
                // Add a new line after each row
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

    public void placeFlag(Entity flag) {
        // Position flagPosition = flag.getPosition();
        //int flagIndex = getIndex(flag);

        // Assumindo que você tem um método para adicionar um vértice (flag) ao mapa
        addVertex(flag);
    }

}