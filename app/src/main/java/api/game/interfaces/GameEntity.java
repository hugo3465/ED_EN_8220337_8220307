package api.game.interfaces;

import api.game.Position;

public interface GameEntity {
    /**
     * Obtém a posição atual da entidade.
     *
     * @return A posição atual da entidade.
     */
    public Position getPosition();

    /**
     * Define a posição da entidade.
     *
     * @param position A nova posição da entidade.
     */
    public void setPosition(Position position);
}
