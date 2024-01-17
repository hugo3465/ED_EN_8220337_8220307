package api.map.interfaces;

import java.io.FileNotFoundException;

import api.game.interfaces.GameEntity;
import exceptions.InvalidMapException;

public interface IGameMap {
    /**
     * Gera um mapa aleatório
     * 
     * @param numLocations
     * @param bidirectional
     * @param density
     */
    public void generateRandomMap(int numLocations, boolean bidirectional, double density);


    public double getDistance(int fromIndex, int toIndex);

    /**
     * Importa mapa de um ficheiro
     * @param path
     */
    public void importMap(String path) throws InvalidMapException, FileNotFoundException;

    /**
     * Exporta mapa para um ficheiro
     * @param path
     */
    public void exportMap(String path) throws FileNotFoundException;

    /**
     * Recebe uma String com o mapa em forma de matriz
     * @return mapa em formato de string
     */
    public String getMap();

    public double[][] getAdjacencyMatrix();

    public GameEntity[] getVertices();

    public GameEntity getVertice(int index);

    public int getIndex(GameEntity entity);
}
