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
        return String.valueOf(index);
    }
    // //Matriz so da ligações lugar num array

    // private int x;
    // private int y;

    // /**
    // * Construtor da classe Position.
    // *
    // * @param x A coordenada X da posição.
    // * @param y A coordenada Y da posição.
    // */
    // public Position(int x, int y) {
    // this.x = x;
    // this.y = y;
    // }

    // /**
    // * Obtém a coordenada X da posição.
    // *
    // * @return A coordenada X da posição.
    // */
    // public int getX() {
    // return x;
    // }

    // /**
    // * Define a coordenada X da posição.
    // *
    // * @param x A nova coordenada X da posição.
    // */
    // public void setX(int x) {
    // this.x = x;
    // }

    // /**
    // * Obtém a coordenada Y da posição.
    // *
    // * @return A coordenada Y da posição.
    // */
    // public int getY() {
    // return y;
    // }

    // /**
    // * Define a coordenada Y da posição.
    // *
    // * @param y A nova coordenada Y da posição.
    // */
    // public void setY(int y) {
    // this.y = y;
    // }

    // /**
    // * Retorna uma representação em formato de string das coordenadas da posição.
    // *
    // * @return Uma string representando as coordenadas da posição no formato "(X,
    // Y)".
    // */
    // @Override
    // public String toString() {
    // return "(" + x + ", " + y + ")";
    // }
}
