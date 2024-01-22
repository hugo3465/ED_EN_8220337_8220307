package api.game.interfaces;

import api.game.Bot;
import api.game.Flag;

public interface IPlayer {
    /**
     * Obtém o nome do jogador.
     *
     * @return O nome do jogador.
     */
    String getName();

    /**
     * Obtém a bandeira do jogador.
     *
     * @return A bandeira do jogador.
     */
    Flag getFlag();

    /**
     * Obtém a bandeira do jogador inimigo.
     *
     * @return A bandeira do jogador inimigo.
     */
    Flag getEnemyFlag();

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário.
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    boolean checkEndGame(Bot bot);

    /**
     * Obtém o próximo bot da fila.
     *
     * @return O próximo bot da fila.
     */
    Bot getNextBot();

    /**
     * Define o nome do jogador.
     *
     * @param name O novo nome do jogador.
     */
    void setName(String name);

    /**
     * Obtém o número atual de vezes que um bot ficou "preso".
     *
     * @return O número atual de vezes que um bot ficou "preso".
     */

    int getStuckCount();

    /**
     * Incrementa o contador de vezes que um bot ficou "preso".
     */
    void incrementStuckCount();

    /**
     * Decrementa o contador de vezes que um bot ficou "preso", se o contador for
     * maior que zero.
     */
    void decrementStuckCount();

    /**
     * Verifica se o contador de bots "presos" ultrapassou o limite permitido.
     *
     * @return true se o contador ultrapassou o limite, false caso contrário.
     */
    boolean isStuckCountReached();

}
