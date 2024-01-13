package api.game;

/**
 * Representa a bandeira no jogo, definida por suas coordenadas X e Y.
 */
public class Flag {

    private Position position;

    /**
     * Construtor da classe Flag.
     *
     * @param position A posição inicial da bandeira.
     */
    public Flag(Position position) {
        this.position = position;
    }

    /**
     * Obtém a posição atual da bandeira.
     *
     * @return A posição atual da bandeira.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Define a posição da bandeira.
     *
     * @param position A nova posição da bandeira.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Define as coordenadas X e Y da bandeira.
     *
     * @param x A nova coordenada X da bandeira.
     * @param y A nova coordenada Y da bandeira.
     */
    public void setCoordinates(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Retorna uma representação em formato de string das coordenadas da bandeira.
     *
     * @return Uma string representando as coordenadas da bandeira no formato "(X, Y)".
     */
    @Override
    public String toString() {
        return position.toString();
    }

}
