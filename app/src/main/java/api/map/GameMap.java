package api.map;

import java.util.Random;

import api.dataStructures.Graph.WeightedGraph;
import api.game.interfaces.GameEntity;
import api.map.interfaces.IGameMap;
import exceptions.InvalidMapException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Representa o mapa do jogo, implementando a interface IGameMap.
 */
public class GameMap extends WeightedGraph<GameEntity> implements IGameMap {

    /** Distância mínima entre vértices no mapa. */
    private final int MIN_DISTANCE = 1;

    /** Distância máxima entre vértices no mapa. */
    private final int MAX_DISTANCE = 15;

    /** Nome do mapa. */
    private String mapName;

    /**
     * Construtor padrão da classe GameMap.
     */
    public GameMap() {
        super();
        this.mapName = "DefaultMap"; // Define um nome padrão
    }

    /**
     * Construtor da classe GameMap com a especificação do nome.
     *
     * @param mapName O nome do mapa.
     */
    public GameMap(String mapName) {
        super();
        this.mapName = mapName;
    }

    /**
     * Obtém o nome do mapa.
     *
     * @return O nome do mapa.
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Define o nome do mapa.
     *
     * @param mapName O novo nome do mapa.
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * Gera um mapa aleatório com as especificações fornecidas.
     *
     * @param numVertices   Número de vértices no mapa.
     * @param bidirectional Indica se o mapa é bidirecional.
     * @param density       Densidade do mapa (entre 0 e 1).
     * @throws IllegalArgumentException Se a densidade estiver fora do intervalo
     *                                  válido.
     */
    @Override
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

    /**
     * Inicializa o mapa com o número especificado de vértices.
     *
     * @param numVertices Número de vértices no mapa.
     */
    private void initializeGraph(int numVertices) {
        this.vertices = new GameEntity[numVertices];
        this.adjMatrix = new double[numVertices][numVertices];
        this.numVertices = numVertices;
    }

    /**
     * Calcula o número total de arestas no grafo com base no número de vértices,
     * na bidirecionalidade e na densidade especificada.
     *
     * @param numVertices   Número de vértices no mapa.
     * @param bidirectional Indica se o mapa é bidirecional.
     * @param density       Densidade do mapa (entre 0 e 1).
     * @return O número total de arestas no grafo.
     */
    private double calculateTotalEdges(int numVertices, boolean bidirectional, double density) {
        // Fórmula geral (N*(N-1))
        double totalEdges;
        if (bidirectional) {
            // Grafo bidirecional
            // a aresta a dividir por 2 serve para evitar a contagem dupla de arestas
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

    /**
     * Preenche a matriz de adjacência com arestas aleatórias, respeitando a
     * bidirecionalidade e a densidade especificada.
     *
     * @param numVertices   Número de vértices no mapa.
     * @param bidirectional Indica se o mapa é bidirecional.
     * @param density       Densidade do mapa (entre 0 e 1).
     * @param totalEdges    O número total de arestas no grafo.
     */
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

    /**
     * Obtém a distância entre dois vértices no mapa.
     *
     * @param fromIndex Índice do vértice de origem.
     * @param toIndex   Índice do vértice de destino.
     * @return A distância entre os vértices.
     */
    @Override
    public double getDistance(int fromIndex, int toIndex) {
        return super.adjMatrix[fromIndex][toIndex];
    }

    /**
     * Importa um mapa a partir de um ficheiro no caminho especificado.
     *
     * @param path Caminho do ficheiro do mapa.
     * @throws InvalidMapException   Se o ficheiro do mapa for inválido.
     * @throws FileNotFoundException Se o ficheiro do mapa não for encontrado.
     */
    @Override
    public void importMap(String path) throws InvalidMapException, FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            int i = 0;

            line = reader.readLine();
            initializeGraph(line.split(" ").length);

            do {

                // String[] values = line.split("\\s+");
                String[] values = line.split(" ");

                // Verifica se a matriz de adjacência foi inicializada
                if (adjMatrix == null || i >= adjMatrix.length) {
                    throw new InvalidMapException("A matriz de adjacência não foi inicializada corretamente.");
                }

                // Verifica se a linha tem o número esperado de elementos
                if (values.length != adjMatrix[i].length) {
                    throw new InvalidMapException("Número incorreto de elementos na linha " + (i + 1));
                }

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
            } while ((line = reader.readLine()) != null);

            if (i < adjMatrix.length) {
                throw new InvalidMapException("O ficheiro é muito curto. Esperava mais linhas.");
            }

        } catch (IOException e) {
            throw new FileNotFoundException("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Exporta o mapa para um ficheiro no caminho especificado.
     *
     * @param path Caminho do ficheiro do mapa.
     */
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

    /**
     * Obtém uma representação do mapa como uma string.
     *
     * @return Uma string representando o mapa.
     */
    @Override
    public String getMap() {
        String map = "";
        for (int i = 0; i < this.numVertices; i++) {
            map += this.vertices[i] + " ";
        }
        return map + "\n";
    }

    /**
     * Obtém a matriz de adjacência do mapa.
     *
     * @return A matriz de adjacência do mapa.
     */
    @Override
    public double[][] getAdjacencyMatrix() {
        return adjMatrix;
    }

    /**
     * Obtém os vértices do mapa.
     *
     * @return Os vértices do mapa.
     */
    @Override
    public GameEntity[] getVertices() {
        return this.vertices;
    }

    /**
     * Obtém o vértice no índice especificado.
     *
     * @param index Índice do vértice desejado.
     * @return O vértice no índice especificado.
     * @throws IndexOutOfBoundsException Se o índice estiver fora dos limites.
     */
    @Override
    public GameEntity getVertice(int index) {
        if (index >= 0 && index < vertices.length) {
            return vertices[index];
        }
        throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    /**
     * Obtém o índice do vértice especificado.
     *
     * @param entity O vertice cujo índice deve ser obtido.
     * @return O índice do vértice especificado.
     * @throws IllegalArgumentException Se o GameEntity especificado não for
     *                                  encontrado no mapa.
     */
    @Override
    public int getIndex(GameEntity entity) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].equals(entity)) {
                return i;
            }
        }
        throw new IllegalArgumentException("O GameEntity especificado não foi encontrado no mapa.");
    }

    /**
     * Define um vértice no índice especificado.
     *
     * @param index  Índice onde o vértice deve ser definido.
     * @param entety O vertice a ser definido.
     * 
     */
    @Override
    public void setVertice(int index, GameEntity entety) {
        vertices[index] = entety;
    }

}