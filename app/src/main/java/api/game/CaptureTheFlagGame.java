package api.game;

import api.game.interfaces.ICaptureTheFlag;
import api.game.interfaces.IPlayer;
import api.map.GameMap;
import api.util.Random;

/**
 * Representa uma instância de jogo do Capture the Flag.
 */
public class CaptureTheFlagGame implements ICaptureTheFlag {

    /** O mapa do jogo. */
    private GameMap map;

    /** Jogador 1 e Jogador 2 */
    private IPlayer player1, player2;

    /** Índice do jogador cuja vez é a atual. */
    private int currentPlayerTurn;

    /** Jogador vencedor. */
    private IPlayer winner;

    /**
     * Construtor da classe CaptureTheFlagGame.
     *
     * @param map     O mapa do jogo.
     * @param player1 O primeiro jogador.
     * @param player2 O segundo jogador.
     */
    public CaptureTheFlagGame(GameMap map, IPlayer player1, IPlayer player2) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;

        // colocar as flags no mapa
        this.map.setVertice(player1.getEnemyFlag().getCurrentIndex(), player1.getEnemyFlag());
        this.map.setVertice(player2.getEnemyFlag().getCurrentIndex(), player2.getEnemyFlag());

        this.currentPlayerTurn = Random.generateRandomNumber(0, 1);
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário.
     *
     * @param bot    O bot cuja posição será verificada em relação à bandeira
     *               inimiga.
     * @param player O jogador a quem o bot pertence.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    private boolean checkEndGame(Bot bot, IPlayer player) {
        return player.checkEndGame(bot);
    }

    /**
     * Executa uma rodada do jogo para o jogador especificado.
     *
     * @param player O jogador que está jogando a rodada.
     * @return O bot que jogou na rodada.
     */
    @Override
    public Bot playRound(IPlayer player) {
        Bot currentBot = null;
        int currentBotMoves;

        currentBot = player.getNextBot();

        currentBotMoves = currentBot.getTimesMoved();

        currentBot.move();

        // se entrar aqui foi porque o bot não se moveu
        if (currentBotMoves == currentBot.getTimesMoved()) {
            player.incrementStuckCount();
        } else {
            player.decrementStuckCount();
        }

        if (checkEndGame(currentBot, player)) {
            winner = player;
        }

        // retorna o bot que jogou
        return currentBot;
    }

    /**
     * Vai definir o próximo jogador a jogar.
     *
     * @return o jogador que vai jogar.
     */
    @Override
    public IPlayer nextTurn() {
        currentPlayerTurn = (currentPlayerTurn + 1) % 2; // Alternar entre jogador 1 e jogador 2

        IPlayer playerTurn = null;

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
     * Verifica se o jogo chegou ao fim e quem é o vencedor.
     *
     * @return -1 se o jogo não acabou, 0 em caso de empate, 1 se o jogador 1
     *         ganhou, 2 se o jogador 2 ganhou.
     */
    @Override
    public int isGameOver() {
        if (player1.isStuckCountReached() && player2.isStuckCountReached()) {
            // se o stuck count de cada player ultrapassar o limite máximo permitido, então
            // é empate
            return 0;
        } else if (winner == player1) {
            return 1;
        } else if (winner == player2) {
            return 2;
        }

        return -1;
    }

    /**
     * Obtém a vez atual do jogador.
     *
     * @return o jogador atual.
     */
    @Override
    public IPlayer getCurrentPlayer() {
        IPlayer playerTurn = null;

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
     * Obtém o mapa do jogo.
     *
     * @return O mapa do jogo.
     */
    @Override
    public GameMap getGameMap() {
        return map;
    }

}
