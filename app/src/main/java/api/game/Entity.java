package api.game;

public abstract class Entity {
    private Position position;

    /**
     * Construtor da classe Enity.
     *
     * @param position A posição inicial da entidade.
     */
    public Entity(Position position) {
        this.position = position;
    }

    /**
     * Obtém a posição atual da entidade.
     *
     * @return A posição atual da entidade.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Define a posição da entidade.
     *
     * @param position A nova posição da entidade.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Define as coordenadas X e Y da entidade.
     *
     * @param x A nova coordenada X da entidade.
     * @param y A nova coordenada Y da entidade.
     */
    public void setCoordinates(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Retorna uma representação em formato de string das coordenadas da entidade.
     *
     * @return Uma string representando as coordenadas da entidade no formato "(X,
     *         Y)".
     */
    @Override
    public String toString() {
        return position.toString();
    }

}
