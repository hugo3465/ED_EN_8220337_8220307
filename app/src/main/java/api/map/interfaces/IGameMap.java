package api.map.interfaces;

import java.io.FileNotFoundException;

import api.game.interfaces.GameEntity;
import exceptions.InvalidMapException;

public interface IGameMap {

    /**
     * Gera um mapa aleatório com as especificações fornecidas.
     *
     * @param numLocations   Número de vértices no mapa.
     * @param bidirectional Indica se o mapa é bidirecional.
     * @param density       Densidade do mapa (entre 0 e 1).
     * @throws IllegalArgumentException Se a densidade estiver fora do intervalo
     *                                  válido.
     */
    public void generateRandomMap(int numLocations, boolean bidirectional, double density);

    /**
     * Obtém a distância entre dois vértices no mapa.
     *
     * @param fromIndex Índice do vértice de origem.
     * @param toIndex   Índice do vértice de destino.
     * @return A distância entre os vértices.
     */
    public double getDistance(int fromIndex, int toIndex);

    /**
     * Importa um mapa a partir de um ficheiro no caminho especificado.
     *
     * @param path Caminho do ficheiro do mapa.
     * @throws InvalidMapException   Se o ficheiro do mapa for inválido.
     * @throws FileNotFoundException Se o ficheiro do mapa não for encontrado.
     */
    public void importMap(String path) throws InvalidMapException, FileNotFoundException;

    /**
     * Exporta o mapa para um ficheiro no caminho especificado.
     *
     * @param path Caminho do ficheiro do mapa.
     */
    public void exportMap(String path) throws FileNotFoundException;

    /**
     * Obtém uma representação do mapa como uma string.
     *
     * @return Uma string representando o mapa.
     */
    public String getMap();

    /**
     * Obtém a matriz de adjacência do mapa.
     *
     * @return A matriz de adjacência do mapa.
     */
    public double[][] getAdjacencyMatrix();

    /**
     * Obtém os vértices do mapa.
     *
     * @return Os vértices do mapa.
     */
    public GameEntity[] getVertices();

    /**
     * Obtém o vértice no índice especificado.
     *
     * @param index Índice do vértice desejado.
     * @return O vértice no índice especificado.
     * @throws IndexOutOfBoundsException Se o índice estiver fora dos limites.
     */
    public GameEntity getVertice(int index);

    /**
     * Obtém o índice do vértice especificado.
     *
     * @param entity O vértice cujo índice deve ser obtido.
     * @return O índice do vértice especificado.
     * @throws IllegalArgumentException Se o GameEntity especificado não for
     *                                  encontrado no mapa.
     */
    public int getIndex(GameEntity entity);

    /**
     * Define um vértice no índice especificado.
     *
     * @param index  Índice onde o vértice deve ser definido.
     * @param entety O vertice a ser definido.
     */
    public void setVertice(int index, GameEntity entety);
}
