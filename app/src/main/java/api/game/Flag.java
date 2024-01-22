package api.game;

import api.game.interfaces.GameEntity;

/**
 * Representa a bandeira no jogo, definida pelo seu índice no grafo.
 */
public class Flag implements GameEntity {

    /**
     * Índice da bandeira no grafo.
     */
    private int index;

    /**
     * Construtor da classe Flag.
     * Passamos o número de vertices logo temos de converter para o indice
     * 
     * @param vertex O vértice inicial da bandeira no grafo.
     */
    public Flag(int vertex) {
        this.index = vertex - 1;
    }

    /**
     * Obtém o índice da bandeira no grafo.
     *
     * @return O índice da bandeira.
     */
    @Override
    public int getCurrentIndex() {
        return index;
    }

    /**
     * Define o índice da bandeira no grafo.
     *
     * @param index O novo índice da bandeira.
     */
    @Override
    public void setCurrentIndex(int index) {
        this.index = index;
    }

}
