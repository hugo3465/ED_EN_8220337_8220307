package api.game;

import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedListADT;
import api.DataStructures.Queue.LinkedQueue.QueueADT;
import api.algorithms.interfaces.MovementAlgorithm;

/**
 * Representa um jogador no jogo Capture the Flag.
 */
public class Player {
    private String name;
    private UnorderedListADT<Bot> bots; // TODO: Trocar para queue
    private Flag flag;
    private Flag enemyFlag;

    public Player(String name, Position flagPosition, Position enemyFlagPosition, Bot[] bots) {
        this.name = name;
        this.bots = new UnorderedArrayList<>();
        this.flag = new Flag(flagPosition);
        this.enemyFlag = new Flag(enemyFlagPosition);

        assignBotInitialPositions(bots);
    }

    public String getname() {
        return name;
    }

    public Bot[] getBots() {
        Bot[] botArray = new Bot[bots.size()];
        int index = 0;
        for (Bot bot : bots) {
            botArray[index++] = bot;
        }
        return botArray;
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
     * Obtém a bandeira do jogador enimigo.
     *
     * @return A bandeira do jogador enimigo.
     */
    public Flag getEnemyFlag() {
        return enemyFlag;
    }

    /**
     * Atribui a posição inicial a todos os bots do jogador com base na posição da
     * bandeira.
     * 
     * Tópico 5 - No início da partida todos os bots deverão estar localizados na
     * mesma posição que a bandeira do seu jogador.
     */
    public void assignBotInitialPositions(Bot[] bots) {
        Position flagPosition = flag.getPosition();

        for (Bot bot : bots) {
            bot.setPosition(flagPosition);
        }
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
        Position enemyFlagPosition = enemyFlag.getPosition();
        return bot.getPosition().equals(enemyFlagPosition);
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
