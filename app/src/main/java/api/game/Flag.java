package api.game;

import api.game.interfaces.GameEntity;

/**
 * Representa a bandeira no jogo, definida pelo seu índice no grafo.
 */
public class Flag implements GameEntity {

    private int index;

    /**
     * Construtor da classe Flag.
     * Passamos o número de vertices logo temos de converter para o indice
     * @param vertex O vértice inicial da bandeira no grafo.
     */
    public Flag(int vertex) {
        this.index = vertex - 1;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
