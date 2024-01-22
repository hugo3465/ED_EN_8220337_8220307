package api.game.interfaces;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Flag;

public interface IBot {

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     * 
     * @return O índice para onde foi atribuída a nova posição do bot.
     */
    int move();

    /**
     * Obtém o algoritmo de movimentação do bot.
     *
     * @return O algoritmo de movimentação do bot.
     */
    MovementAlgorithm getMovementAlgorithm();

    /**
     * Define o algoritmo de movimentação do bot.
     *
     * @param algorithm O novo algoritmo de movimentação do bot.
     */
    void setMovementAlgorithm(MovementAlgorithm algorithm);

    /**
     * Obtém o nome do bot.
     *
     * @return O nome do bot.
     */
    public String getName();

    /**
     * Define a bandeira inimiga a ser alcançada pelo bot.
     *
     * @param enemyFlag A nova bandeira inimiga.
     */
    public void setEnemyFlag(Flag enemyFlag);

    /**
     * Obtém o número de vezes que o bot foi movido.
     *
     * @return O número de vezes que o bot foi movido.
     */
    public int getTimesMoved();

    /**
     * Obtém o índice antigo do bot no grafo.
     *
     * @return O índice do bot no grafo.
     */
    int getLastIndex();
}
