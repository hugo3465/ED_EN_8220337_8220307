package api.game.interfaces;

public interface GameEntity {
    /**
     * Obtém a posição atual da entidade.
     * A posição é o indice onde a entidade está no array de vértices do grafo
     *
     * @return A posição atual da entidade.
     */
    public int getPosition();

    /**
     * Define a posição da entidade.
     * A posição é o indice onde a entidade está no array de vértices do grafo
     *
     * @param position A nova posição da entidade.
     */
    public void setPosition(int position);
}
