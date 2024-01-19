package api.algorithms.interfaces;

import api.game.Bot;

public interface MovementAlgorithm<T> {

    // /**
    //  * Realiza a procura em largura (BFS), em profundidade (DFS) ou o caminho mais curto
    //  * dependendo da implementação específica.
    //  *
    //  * @param startVertex o vértice de início da busca
    //  * @return um iterador representando o resultado da busca
    //  */
    // Iterator<T> search(T startVertex);

    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot);

    public boolean hasBot(int vertex);
}
