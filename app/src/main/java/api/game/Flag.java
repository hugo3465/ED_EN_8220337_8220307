package api.game;

/**
 * Representa a bandeira no jogo, definida por suas coordenadas X e Y.
 */
public class Flag extends Entity {

    /**
     * Construtor da classe Flag.
     *
     * @param position A posição inicial da bandeira.
     */
    public Flag(Position position) {
        super(position);
    }

    /**
     * Retorna uma representação em formato de string das coordenadas da bandeira.
     *
     * @return Uma string representando as coordenadas da bandeira no formato "(X, Y)".
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
