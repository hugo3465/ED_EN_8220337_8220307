package api.game.interfaces;

public interface GameEntity {
    /**
     * Obtém o índice atual da entidade no array de vértices.
     *
     * @return O índice atual da entidade no array de vértices.
     */
    public int getIndex();

    /**
     * Define o índice da entidade no array de vértices.
     * A posição é o indice onde a entidade está no array de vértices do grafo
     *
     * @param position A nova posição da entidade.
     */
    public void setIndex(int index);
}
