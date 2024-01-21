package api.game;

import api.DataStructures.Queue.LinkedQueue.LinkedQueue;
import api.DataStructures.Queue.LinkedQueue.QueueADT;

/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player {
    private String name;
    private QueueADT<Bot> bots;
    private Flag flag;
    private Flag enemyFlag;

    public Player(String name, Flag myflag, Flag enemyFlag, Bot[] bots) {
        this.name = name;
        this.bots = new LinkedQueue<>();
        this.flag = myflag;
        this.enemyFlag = enemyFlag;

        assignBotInitialPositions(bots);

        enqueueBots(bots);
    }

    /**
     * Atribui a posição inicial a todos os bots do jogador com base na posição da
     * bandeira.
     * 
     * Tópico 5 - No início da partida todos os bots deverão estar localizados na
     * mesma posição que a bandeira do seu jogador.
     */
    private Bot[] assignBotInitialPositions(Bot[] bots) {
        int flagPosition = flag.getPosition();

        for (Bot bot : bots) {
            bot.setPosition(flagPosition);
        }

        return bots;
    }

    private void enqueueBots(Bot[] bots) {
        for (Bot bot : bots) {
            this.bots.enqueue(bot);
        }
    }

    public String getname() {
        return name;
    }

    // public Bot[] getBots() {
    // Bot[] botArray = new Bot[bots.size()];
    // int index = 0;
    // for (Bot bot : bots) {
    // botArray[index++] = bot;
    // }
    // return botArray;

    // Iterator<Bot> bots = this.bots.
    // }

    /**
     * Obtém a bandeira do jogador.
     *
     * @return A bandeira do jogador.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Obtém a bandeira do jogador enimigo.
     *
     * @return A bandeira do jogador enimigo.
     */
    public Flag getEnemyFlag() {
        return enemyFlag;
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        int enemyFlagPosition = enemyFlag.getPosition();

        return bot.getPosition() == enemyFlagPosition;
    }

    public Bot getNextBot() {
        Bot currenBot = this.bots.dequeue();

        this.bots.enqueue(currenBot);

        return currenBot;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
