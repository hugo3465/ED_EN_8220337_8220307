package api.game;

/**
 * Representa a bandeira no jogo, definida por suas coordenadas X e Y.
 */
public class Flag {
    private int x; // Coordenada X da bandeira
    private int y; // Coordenada Y da bandeira

    /**
     * Construtor da classe Flag.
     *
     * @param x A coordenada X inicial da bandeira.
     * @param y A coordenada Y inicial da bandeira.
     */
    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Métodos para obter e configurar as coordenadas da bandeira

    /**
     * Obtém a coordenada X da bandeira.
     *
     * @return A coordenada X da bandeira.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a coordenada Y da bandeira.
     *
     * @return A coordenada Y da bandeira.
     */
    public int getY() {
        return y;
    }

    /**
     * Define a coordenada X da bandeira.
     *
     * @param x A nova coordenada X da bandeira.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Define a coordenada Y da bandeira.
     *
     * @param y A nova coordenada Y da bandeira.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Define as coordenadas X e Y da bandeira.
     *
     * @param x A nova coordenada X da bandeira.
     * @param y A nova coordenada Y da bandeira.
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna uma representação em formato de string das coordenadas da bandeira.
     *
     * @return Uma string representando as coordenadas da bandeira no formato "(X, Y)".
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
