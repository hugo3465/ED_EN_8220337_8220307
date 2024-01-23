package api.game.interfaces;

public interface GameEntity {
    /**
     * Obtém o índice atual da entidade no array de vértices.
     *
     * @return O índice atual da entidade no array de vértices.
     */
    int getCurrentIndex();

    /**
     * Define o índic atual da entidade no array de vértices.
     * A posição é o indice onde a entidade está no array de vértices do grafo
     *
     * @param index A nova posição da entidade.
     */
    void setCurrentIndex(int index);

}
