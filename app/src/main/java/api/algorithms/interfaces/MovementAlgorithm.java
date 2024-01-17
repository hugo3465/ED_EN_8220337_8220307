package api.algorithms.interfaces;

public interface MovementAlgorithm<T> {

    // /**
    //  * Realiza a procura em largura (BFS), em profundidade (DFS) ou o caminho mais curto
    //  * dependendo da implementação específica.
    //  *
    //  * @param startVertex o vértice de início da busca
    //  * @return um iterador representando o resultado da busca
    //  */
    // Iterator<T> search(T startVertex);

    public int getNextMovement(int currentIndex, int endIndex);

    public boolean hasBot(int vertex);
}
