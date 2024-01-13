package api.game;

import api.DataStructures.Queue.LinkedQueue.LinkedQueue;
import api.DataStructures.Queue.LinkedQueue.QueueADT;
import api.algorithms.interfaces.BotAlgorithm;

/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player {
    private QueueADT<Bot> botQueue; // Fila de bots pertencentes ao jogador
    private Flag flag; // Bandeira do jogador
    private Flag enemyFlag; // Localização da bandeira inimiga


    public Player(Flag flag, Flag enemyFlag) {
        this.botQueue = new LinkedQueue<>();
        this.flag = flag;
        this.enemyFlag = enemyFlag;
    }

    // Implementação doos  métodos para configurar bots, atribuir algoritmos, etc.


    /**
     * Atribui a posição inicial do bot à posição da bandeira.
     * Tópico 5 - No início da partida todos os bots deverão estar localizados na mesma posição que a
     * bandeira do seu jogador.
     *
     * @param bot O bot ao qual a posição será atribuída.
     * @param flag A bandeira que servirá como referência para a posição inicial.
     */
    private void assignInitialPosition(Bot bot, Flag flag) {
        // Atribui a posição inicial do bot à posição da bandeira
        bot.setX(flag.getX());
        bot.setY(flag.getY());
    }

    /**
     * Adiciona um bot à fila da equipe e atribui o algoritmo escolhido pelo jogador.
     * Tópico 4 - Atribuir a cada um deles um algoritmo proveniente das diversas opções que cada
     * grupo de trabalho deve disponibilizar.
     * @param bot O bot a ser adicionado à equipe.
     * @param algorithm O algoritmo escolhido pelo jogador para o bot.
     */
    public void addBot(Bot bot, BotAlgorithm algorithm) {
        // Atribui o algoritmo escolhido pelo jogador ao bot
        bot.setAlgorithm(algorithm);
        // Adiciona o bot à fila da equipe
        botQueue.enqueue(bot);
//        assignRandomPosition(bot);  // Adiciona a chamada para atribuir posição aleatória
        // Atribui a posição inicial do bot à posição da bandeira
        assignInitialPosition(bot, flag);
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        return (bot.getX() == enemyFlag.getX() && bot.getY() == enemyFlag.getY());
    }
    
}
