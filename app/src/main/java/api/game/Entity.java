package api.game;

public abstract class Entity implements Comparable<Entity> {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entity other = (Entity) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        return true;
    }

    

}
