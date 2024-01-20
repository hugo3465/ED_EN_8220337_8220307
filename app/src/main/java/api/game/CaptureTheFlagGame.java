package api.game;

import java.util.concurrent.TimeUnit;

import api.game.interfaces.ICaptureTheFlag;
import api.map.GameMap;

public class CaptureTheFlagGame implements ICaptureTheFlag {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;

    public CaptureTheFlagGame(GameMap map, Player player1, Player player2) {
        newGame(map, player1, player2);
    }

    public void newGame(GameMap map, Player player1, Player player2) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayerTurn = 0;
    }

    @Override
    public String getMapPreviw() {
        // Implemente a lógica para obter uma visualização do mapa (pode ser um método
        // na classe GameMap)
        return map.getMap(); // Substitua por lógica real
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira
     * inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @param player o player a quem o bot pertence
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot, Player player) {//TODO não está a fazer muito sentido
        return player.checkEndGame(bot);
    }

    // Implemente métodos para iniciar o jogo, avançar rodadas, etc.

    public void startGame(Player player1, Player player2) {
        // Inicializar o jogo, posicionar bots, etc.
        // initializeGame();
        Player winner = null;
        Player playerTurn = player1;
        Bot currentBot = null;

        // Imprimir visualização do mapa inicial
        //System.out.println(getMapPreviw());

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
            currentBot.move();

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

    private void playRound(Bot bot) {
        // Implemente a lógica da rodada aqui, como movimento de bots, verificação de
        // vitória, etc.
        Player currentPlayer = (currentPlayerTurn == 0) ? player1 : player2;
        Player otherPlayer = (currentPlayerTurn == 0) ? player2 : player1;

        // Lógica de movimento de bots, etc.
        // Exemplo:
        bot.move();
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
            System.out.println("ERRO TESTE");
        }

        return playerTurn;
    }

}
