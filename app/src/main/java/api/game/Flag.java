package api.game;

import api.game.interfaces.GameEntity;

/**
 * Representa a bandeira no jogo, definida pelo seu índice no grafo.
 */
public class Flag implements GameEntity {

    private Position position;

    /**
     * Construtor da classe Flag.
     *
     * @param position O índice inicial da bandeira no grafo.
     */
    public Flag(Position position) {
        this.position = position;
    }

    /**
     * Retorna uma representação em formato de string do índice da bandeira no grafo.
     *
     * @return Uma string representando o índice da bandeira.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

}
