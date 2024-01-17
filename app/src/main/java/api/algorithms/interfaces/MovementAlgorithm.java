package api.algorithms.interfaces;
import java.util.Iterator;

import api.algorithms.AlgorithmType;
public interface MovementAlgorithm<T> {

    /**
     * Realiza a busca em largura (BFS), em profundidade (DFS) ou o caminho mais curto
     * dependendo da implementação específica.
     *
     * @param startVertex o vértice de início da busca
     * @return um iterador representando o resultado da busca
     */
    Iterator<T> search(T startVertex);

    /**
     * Retorna o peso do caminho mais curto entre dois vértices.
     *
     * @param startVertex  o vértice de origem
     * @param targetVertex o vértice de destino
     * @return o peso do caminho mais curto
     */
    double shortestPathWeight(T startVertex, T targetVertex);

    /**
     * Retorna um iterador que contém o caminho mais curto entre dois vértices.
     *
     * @param startVertex  o vértice de origem
     * @param targetVertex o vértice de destino
     * @return um iterador que contém o caminho mais curto
     */
    Iterator<T> iteratorShortestPath(T startVertex, T targetVertex);


    AlgorithmType getAlgorithmType(); // Adicione este método


}
