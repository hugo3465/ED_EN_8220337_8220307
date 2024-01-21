package api.game;

import api.game.interfaces.ICaptureTheFlag;
import api.map.GameMap;
import api.util.Random;

public class CaptureTheFlagGame implements ICaptureTheFlag {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;
    private Player winner;

    public CaptureTheFlagGame(GameMap map, Player player1, Player player2) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;

        this.currentPlayerTurn = Random.generateRandomNumber(0, 1);
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot    O bot cuja posição será verificada em relação à bandeira
     *               inimiga.
     * @param player o player a quem o bot pertence
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    private boolean checkEndGame(Bot bot, Player player) {// TODO não está a fazer muito sentido
        return player.checkEndGame(bot);
    }

    @Override
    public Bot playRound(Player player) {
        Bot currentBot = null;

        currentBot = player.getNextBot();

        currentBot.move();

        if (checkEndGame(currentBot, player)) {
            winner = player;
        }

        // retorna o bot que jogou
        return currentBot;
    }

    public Player nextTurn() {
        currentPlayerTurn = (currentPlayerTurn + 1) % 2; // Alternar entre jogador 1 e jogador 2

        Player playerTurn = null;

        if (currentPlayerTurn == 0) {
            playerTurn = player1;
        } else if (currentPlayerTurn == 1) {
            playerTurn = player2;
        } else {
            throw new RuntimeException("Ocurreu um erro ao processar a vez do jogador");
        }

        return playerTurn;
    }

    /**
     * 
     * @return -1 - não acabou
     *         0 - empate
     *         1 - jogador 1 ganhou
     *         2 - jogador 2 ganhou
     */
    // TODO
    @Override
    public int isGameOver() {
        if (winner == null) {
            return -1;
        } else if (winner == player1) {
            return 1;
        } else if (winner == player2) {
            return 2;
        }

        return 0;
    }

    @Override
    public Player getCurrentPlayer() {
        Player playerTurn = null;

        if (currentPlayerTurn == 0) {
            playerTurn = player1;
        } else if (currentPlayerTurn == 1) {
            playerTurn = player2;
        } else {
            throw new RuntimeException("Ocurreu um erro ao processar a vez do jogador");
        }

        return playerTurn;
    }

}
