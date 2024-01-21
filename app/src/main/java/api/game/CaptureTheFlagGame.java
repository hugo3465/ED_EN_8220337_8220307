package api.game;

import java.util.concurrent.TimeUnit;

import api.game.interfaces.ICaptureTheFlag;
import api.map.GameMap;

public class CaptureTheFlagGame implements ICaptureTheFlag {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;
    private Player winner;

    public CaptureTheFlagGame(GameMap map, Player player1, Player player2) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayerTurn = 0;
        this.winner = null;
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

    public void startGame(Player player1, Player player2) {
        // Inicializar o jogo, posicionar bots, etc.
        // initializeGame();
        Player winner = null;
        Player playerTurn = player1;
        Bot currentBot = null;

        int round = 1;
        // Enquanto o jogo não terminar, continuar rodadas
        while (winner == null) {
            System.out.println("-------------Rodada " + round + ": -------------");

            // remover o bot da queue do player
            currentBot = playerTurn.getNextBot();

            System.out.println(playerTurn.getname() + " tinha o bot " + currentBot.getName() + " no vertice "
                    + (currentBot.getPosition() + 1) + '\n');

            // Realizar a lógica da rodada
            // playRound(currentBot);

            // Imprimir visualização do mapa após cada rodada
            System.out.println(playerTurn.getname() + " moveu o bot " + currentBot.getName() + " foi para o vertice "
                    + (currentBot.getPosition() + 1) + '\n');

            try {
                // Aguardar 2 segundos antes da próxima rodada
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Imprimir visualização do mapa após cada rodada
            // System.out.println(getMapPreviw());

            if (checkEndGame(currentBot, playerTurn)) {
                winner = playerTurn;
                break;
            }

            // Avançar para a próxima rodada
            playerTurn = nextTurn();

            round++;

        }

        // Exibir mensagem de fim de jogo
        System.out.println("Fim de jogo!!!");
        System.out.println("--------------WINNER: " + winner.getname() + " ---------------");
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
        // Implemente a lógica para avançar para a próxima rodada, trocar de jogador,
        // etc.
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
