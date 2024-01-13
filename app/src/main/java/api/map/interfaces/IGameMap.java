package api.map.interfaces;

import api.DataStructures.Graph.NetworkADT;

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
    public void importMap(String path);

    /**
     * Exporta mapa para um ficheiro
     * @param path
     */
    public void exportMap(String path);

    public NetworkADT<Integer> getMap();
}
