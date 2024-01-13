package api.game;

import api.DataStructures.Queue.LinkedQueue.LinkedQueue;
import api.DataStructures.Queue.LinkedQueue.QueueADT;
import api.algorithms.interfaces.MovementAlgorithm;

import java.util.Iterator;
import java.util.Random;

/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player {
    private String playerName;
    private Bot[] bots;
    private Flag flag;
    private Flag enemyFlag; // Nova adição para representar a bandeira inimiga

    /**
     * Construtor da classe Player.
     *
     * @param playerName   O nome do jogador.
     * @param numBots      O número de bots que o jogador terá.
     * @param flagPosition A posição inicial da bandeira do jogador.
     */
    public Player(String playerName, int numBots, Position flagPosition, Position enemyFlagPosition) {
        this.playerName = playerName;
        this.bots = new Bot[numBots];

        // Define a posição da bandeira
        this.flag = new Flag(flagPosition);

        // Define a posição da bandeira inimiga
        this.enemyFlag = new Flag(enemyFlagPosition);

        // Atribuição de nomes aos bots
        for (int i = 0; i < numBots; i++) {
            bots[i] = new Bot(flagPosition);
            bots[i].setMovementAlgorithm(null); // Atribua algoritmos posteriormente
            bots[i].setName(playerName + "_Bot" + (i + 1));
        }
    }

    /**
     * Obtém o nome do jogador.
     *
     * @return O nome do jogador.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Obtém os bots pertencentes ao jogador.
     *
     * @return Um array de bots pertencentes ao jogador.
     */
    public Bot[] getBots() {
        return bots;
    }

    /**
     * Obtém a bandeira do jogador.
     *
     * @return A bandeira do jogador.
     */
    public Flag getFlag() {
        return flag;
    }

    public Flag getEnemyFlag() {
        return enemyFlag;
    }

    /**
     * Atribui a posição inicial do bot à posição da bandeira.
     * Tópico 5 - No início da partida todos os bots deverão estar localizados na mesma posição que a
     * bandeira do seu jogador.
     *
     * @param bot  O bot ao qual a posição será atribuída.
     * @param flag A bandeira que servirá como referência para a posição inicial.
     */
    private void assignInitialPosition(Bot bot, Flag flag) {
        Position flagPosition = flag.getPosition();
        bot.setCurrentPosition(flagPosition);
    }

    /**
     * Adiciona um bot à equipe e atribui o algoritmo escolhido pelo jogador.
     * Tópico 4 - Atribuir a cada um deles um algoritmo proveniente das diversas opções que cada
     * grupo de trabalho deve disponibilizar.
     *
     * @param bot      O bot a ser adicionado à equipe.
     * @param algorithm O algoritmo escolhido pelo jogador para o bot.
     */
    public void addBot(Bot bot, MovementAlgorithm algorithm) {
        bot.setName(playerName + "_Bot");
        // Atribui o algoritmo escolhido pelo jogador ao bot
        bot.setMovementAlgorithm(algorithm);
        // Atribui a posição inicial do bot à posição da bandeira
        assignInitialPosition(bot, flag);
    }

    /**
     * Atribui um algoritmo a um bot específico.
     *
     * @param bot       O bot ao qual o algoritmo será atribuído.
     * @param algorithm O algoritmo a ser atribuído ao bot.
     */
    public void assignAlgorithmToBot(Bot bot, MovementAlgorithm algorithm) {
        bot.setMovementAlgorithm(algorithm);
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        Position enemyFlagPosition = enemyFlag.getPosition();
        return bot.getCurrentPosition().equals(enemyFlagPosition);
    }
    
}
