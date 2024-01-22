package api.game;

import api.dataStructures.Queue.LinkedQueue.LinkedQueue;
import api.dataStructures.Queue.LinkedQueue.QueueADT;


/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player {

    /** Nome do jogador. */
    private String name;

    /** Fila de bots associados ao jogador. */
    private QueueADT<Bot> bots;

    /** Bandeira do jogador. */
    private Flag flag;

    /** Bandeira do jogador inimigo. */
    private Flag enemyFlag;

    /**
     * Construtor da classe Player.
     *
     * @param name      O nome do jogador.
     * @param myflag    A bandeira do jogador.
     * @param enemyFlag A bandeira do jogador inimigo.
     * @param bots      Array de bots associados ao jogador.
     */
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
     *
     * @param bots O array de bots a serem posicionados.
     * @return O array de bots com as posições iniciais atribuídas.
     */
    private Bot[] assignBotInitialPositions(Bot[] bots) {
        int flagPosition = flag.getIndex();

        for (Bot bot : bots) {
            bot.setIndex(flagPosition);
        }

        return bots;
    }

    /**
     * Coloca na fila os bots associados ao jogador.
     *
     * @param bots O array de bots associados ao jogador.
     */
    private void enqueueBots(Bot[] bots) {
        for (Bot bot : bots) {
            this.bots.enqueue(bot);
        }
    }

    /**
     * Obtém o nome do jogador.
     *
     * @return O nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém a bandeira do jogador.
     *
     * @return A bandeira do jogador.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Obtém a bandeira do jogador inimigo.
     *
     * @return A bandeira do jogador inimigo.
     */
    public Flag getEnemyFlag() {
        return enemyFlag;
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário.
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        int enemyFlagPosition = enemyFlag.getIndex();

        return bot.getIndex() == enemyFlagPosition;
    }

    /**
     * Obtém o próximo bot da fila.
     *
     * @return O próximo bot da fila.
     */
    protected Bot getNextBot() {
        Bot currenBot = this.bots.dequeue();

        this.bots.enqueue(currenBot);

        return currenBot;
    }

    /**
     * Define o nome do jogador.
     *
     * @param name O novo nome do jogador.
     */
    public void setName(String name) {
        this.name = name;
    }

}
