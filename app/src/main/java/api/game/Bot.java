package api.game;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;
import api.game.interfaces.IBot;

/**
 * Representa um bot no jogo, caracterizado pelo seu índice no grafo e algoritmo
 * de movimentação.
 */
public class Bot implements GameEntity, IBot {

    /** Nome do bot. */
    private String name;

    /** Índice antigo do bot no grafo. */
    private int lastIndex;

    /** Índice atual do bot no grafo. */
    private int currentIndex;

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
    @Override
    public int move() {
        if (movementAlgorithm != null) {

            int newLocation = movementAlgorithm.getNextMovement(currentIndex,
                    enemyFlag.getCurrentIndex(), this);

            if (newLocation != currentIndex) {
                timesMoved++;
                setCurrentIndex(newLocation);
            }

            return currentIndex;

        }

        return -1;
    }

    /**
     * Obtém o algoritmo de movimentação do bot.
     *
     * @return O algoritmo de movimentação do bot.
     */
    @Override
    public MovementAlgorithm getMovementAlgorithm() {
        return movementAlgorithm;
    }

    /**
     * Define o algoritmo de movimentação do bot.
     *
     * @param algorithm O novo algoritmo de movimentação do bot.
     */
    @Override
    public void setMovementAlgorithm(MovementAlgorithm algorithm) {
        this.movementAlgorithm = algorithm;
    }

    /**
     * Obtém o nome do bot.
     *
     * @return O nome do bot.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Define a bandeira inimiga a ser alcançada pelo bot.
     *
     * @param enemyFlag A nova bandeira inimiga.
     */
    @Override
    public void setEnemyFlag(Flag enemyFlag) {
        this.enemyFlag = enemyFlag;
    }

    /**
     * Obtém o número de vezes que o bot foi movido.
     *
     * @return O número de vezes que o bot foi movido.
     */
    @Override
    public int getTimesMoved() {
        return timesMoved;
    }

    /**
     * Obtém o índice do bot no grafo.
     *
     * @return O índice do bot no grafo.
     */
    @Override
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Define o índice do bot no grafo.
     *
     * @param currentIndex O novo índice do bot.
     */
    @Override
    public void setCurrentIndex(int currentIndex) {
        // se o bot ainda não se mexeu, então o antigo índice é igual ao atual
        if (timesMoved == 0) {
            this.lastIndex = currentIndex;
            this.currentIndex = currentIndex;
        } else {
            this.lastIndex = this.currentIndex;
            this.currentIndex = currentIndex;
        }

    }

    /**
     * Obtém o índice antigo do bot no grafo.
     *
     * @return O índice do bot no grafo.
     */
    @Override
    public int getLastIndex() {
        return lastIndex;
    }

}
