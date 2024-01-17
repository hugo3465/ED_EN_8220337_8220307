package api.game;

/**
 * Representa uma posição no jogo, definida pelo seu índice no grafo.
 */
public class Position {

    private int index;

    /**
     * Construtor da classe Position.
     *
     * @param index O índice da posição no grafo.
     */
    public Position(int index) {
        this.index = index;
    }

    /**
     * Obtém o índice da posição no grafo.
     *
     * @return O índice da posição no grafo.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Define o índice da posição no grafo.
     *
     * @param index O novo índice da posição no grafo.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retorna uma representação em formato de string do índice da posição.
     *
     * @return Uma string representando o índice da posição.
     */
    @Override
    public String toString() {
        return "Nó " + (index + 1);
    }
}
