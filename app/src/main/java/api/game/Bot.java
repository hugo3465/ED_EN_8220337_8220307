package api.game;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;

/**
 * Representa um bot no jogo, caracterizado pelo seu índice no grafo e algoritmo
 * de movimentação.
 */
public class Bot implements GameEntity {

    /** Nome do bot. */
    private String name;

    /** Índice do bot no grafo. */
    private int index;

    /** Algoritmo de movimentação associado ao bot. */
    private MovementAlgorithm movementAlgorithm;

    /** Bandeira inimiga a ser alcançada pelo bot. */
    private Flag enemyFlag;

    /** Número de vezes que o bot foi movido. */
    private int timesMoved;

    /**
     * Construtor da classe Bot.
     *
     * @param name              O nome do bot.
     * @param movementAlgorithm O algoritmo de movimentação do bot.
     * @param enemyFlag         A bandeira inimiga a ser alcançada pelo bot.
     */
    public Bot(String name, MovementAlgorithm movementAlgorithm, Flag enemyFlag) {
        this.name = name;
        this.movementAlgorithm = movementAlgorithm;
        this.enemyFlag = enemyFlag;
        this.timesMoved = 0;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     * 
     * @return O índice para onde foi atribuída a nova posição do bot.
     */
    public int move() {
        if (movementAlgorithm != null) {

            int newLocation = movementAlgorithm.getNextMovement(index,
                    enemyFlag.getIndex(), this);

            if (newLocation != index) {
                timesMoved++;
                this.index = newLocation;
            }

            return index;

        }

        return -1;
    }

    /**
     * Obtém o algoritmo de movimentação do bot.
     *
     * @return O algoritmo de movimentação do bot.
     */
    public MovementAlgorithm getMovementAlgorithm() {
        return movementAlgorithm;
    }

    /**
     * Define o algoritmo de movimentação do bot.
     *
     * @param algorithm O novo algoritmo de movimentação do bot.
     */
    public void setMovementAlgorithm(MovementAlgorithm algorithm) {
        this.movementAlgorithm = algorithm;
    }

    /**
     * Obtém o nome do bot.
     *
     * @return O nome do bot.
     */
    public String getName() {
        return name;
    }

    /**
     * Define a bandeira inimiga a ser alcançada pelo bot.
     *
     * @param enemyFlag A nova bandeira inimiga.
     */
    public void setEnemyFlag(Flag enemyFlag) {
        this.enemyFlag = enemyFlag;
    }

    /**
     * Obtém o número de vezes que o bot foi movido.
     *
     * @return O número de vezes que o bot foi movido.
     */
    public int getTimesMoved() {
        return timesMoved;
    }

    /**
     * Obtém o índice do bot no grafo.
     *
     * @return O índice do bot no grafo.
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Define o índice do bot no grafo.
     *
     * @param index O novo índice do bot.
     */
    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
