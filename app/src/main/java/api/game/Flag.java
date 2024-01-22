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
     * @param index O índice inicial da bandeira no grafo.
     */
    public Flag(int index) {
        this.index = index - 1;
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
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
