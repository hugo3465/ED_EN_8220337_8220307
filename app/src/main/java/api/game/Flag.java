package api.game;

/**
 * Representa a bandeira no jogo, definida pelo seu índice no grafo.
 */
public class Flag extends Entity {

    /**
     * Construtor da classe Flag.
     *
     * @param position O índice inicial da bandeira no grafo.
     */
    public Flag(Position position) {
        super(position);
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
    public int compareTo(Entity otherEntity) {
        if (otherEntity instanceof Flag) {
            Flag otherFlag = (Flag) otherEntity;
            return Integer.compare(this.getPosition().getIndex(), otherFlag.getPosition().getIndex());
        }
        throw new IllegalArgumentException("Cannot compare Flag with a different entity type.");
    }

}
