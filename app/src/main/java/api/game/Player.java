package api.game;

import api.dataStructures.Queue.LinkedQueue.LinkedQueue;
import api.dataStructures.Queue.LinkedQueue.QueueADT;
import api.game.interfaces.IPlayer;

/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player implements IPlayer {

    /**
     * Representa o intervalo máximo do {@code stuckCount}, que é incrementado
     * sempre que um bot não se conseguir mexer, e decrementado se conseguir
     */
    private int STUCK_RANGE;

    /** Nome do jogador. */
    private String name;

    /** Fila de bots associados ao jogador. */
    private QueueADT<Bot> bots;

    /** Bandeira do jogador. */
    private Flag flag;

    /** Bandeira do jogador inimigo. */
    private Flag enemyFlag;

    /**
     * contador de bots presos, sempre que um bot não se conseguir mexer ele é
     * incrementado, se conseguir ele desce, mas nunca desce a baixo de 0
     */
    private int stuckCount;

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

        defineStuckRange(bots.length);

        assignBotInitialPositions(bots);

        enqueueBots(bots);
    }

    private void defineStuckRange(int numBots) {
        int stuckRange;

        switch (numBots) {
            case 1:
                stuckRange = 2;
                break;
            case 2:
                stuckRange = 4;
                break;
            case 3:
                stuckRange = 7;
                break;
            case 4:
                stuckRange = 10;
                break;
            default:
                stuckRange = 10;
                break;
        }

        this.STUCK_RANGE = stuckRange;
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
        int flagPosition = flag.getCurrentIndex();

        for (Bot bot : bots) {
            bot.setCurrentIndex(flagPosition);
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
    @Override
    public String getName() {
        return name;
    }

    /**
     * Obtém a bandeira do jogador.
     *
     * @return A bandeira do jogador.
     */
    @Override
    public Flag getFlag() {
        return flag;
    }

    /**
     * Obtém a bandeira do jogador inimigo.
     *
     * @return A bandeira do jogador inimigo.
     */
    @Override
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
    @Override
    public boolean checkEndGame(Bot bot) {
        int enemyFlagPosition = enemyFlag.getCurrentIndex();

        return bot.getCurrentIndex() == enemyFlagPosition;
    }

    /**
     * Obtém o próximo bot da fila.
     *
     * @return O próximo bot da fila.
     */
    @Override
    public Bot getNextBot() {
        Bot currenBot = this.bots.dequeue();

        this.bots.enqueue(currenBot);

        return currenBot;
    }

    /**
     * Define o nome do jogador.
     *
     * @param name O novo nome do jogador.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o número atual de vezes que um bot ficou "preso".
     *
     * @return O número atual de vezes que um bot ficou "preso".
     */
    @Override
    public int getStuckCount() {
        return stuckCount;
    }

    /**
     * Incrementa o contador de vezes que um bot ficou "preso".
     */
    @Override
    public void incrementStuckCount() {
        stuckCount++;
    }

    /**
     * Decrementa o contador de vezes que um bot ficou "preso", se o contador for
     * maior que zero.
     */
    @Override
    public void decrementStuckCount() {
        if (stuckCount > 0) {
            stuckCount--;
        }
    }

    /**
     * Verifica se o contador de bots "presos" ultrapassou o limite permitido.
     *
     * @return true se o contador ultrapassou o limite, false caso contrário.
     */
    @Override
    public boolean isStuckCountReached() {
        return stuckCount >= STUCK_RANGE;
    }

}
