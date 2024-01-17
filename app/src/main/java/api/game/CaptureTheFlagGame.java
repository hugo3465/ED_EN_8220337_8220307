package api.game;

import api.game.interfaces.ICaptureTheFlag;
import api.map.GameMap;

public class CaptureTheFlagGame implements ICaptureTheFlag {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;

    public CaptureTheFlagGame(GameMap map, Player player1, Player player2) {
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
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        return (player1.checkEndGame(bot) || player2.checkEndGame(bot));
    }

    // Implemente métodos para iniciar o jogo, avançar rodadas, etc.

    public void startGame(Player player1, Player player2) {
        // Inicializar o jogo, posicionar bots, etc.
        initializeGame();

        // Imprimir visualização do mapa inicial
        System.out.println(getMapPreviw());

        // Enquanto o jogo não terminar, continuar rodadas
        while (!isGameOver()) {
            // Realizar a lógica da rodada
            playRound();

            // Imprimir visualização do mapa após cada rodada
            System.out.println(getMapPreviw());

            // Avançar para a próxima rodada
            nextTurn();
        }

        // Exibir mensagem de fim de jogo
        System.out.println("Fim de jogo!");
    }

    private void initializeGame() {
        Position flagPositionPlayer1 = new Position(0); // Substitua com a lógica real para posicionar a bandeira do
                                                        // jogador 1
        Position flagPositionPlayer2 = new Position(3); // Substitua com a lógica real para posicionar a bandeira do
                                                         // jogador 2
        // Posicione as bandeiras dos jogadores

        // // Atribua algoritmos diferentes aos bots de cada jogador
        // QueueADT<Object> algorithmsPlayer1 = new LinkedQueue<>();
        // algorithmsPlayer1.enqueue(new BreadthFirstSearchAlgorithm<>(map));
        // algorithmsPlayer1.enqueue(new DepthFirstSearchAlgorithm<>(map));

        // QueueADT<Object> algorithmsPlayer2 = new LinkedQueue<>();
        // algorithmsPlayer2.enqueue(new ShortestPathAlgorithm<>(map));
        // algorithmsPlayer2.enqueue(new BreadthFirstSearchAlgorithm<>(map));

        // player1.assignAlgorithmToBots(algorithmsPlayer1);
        // player2.assignAlgorithmToBots(algorithmsPlayer2);

        // Posicione os bots na posição inicial
    }

    private boolean isGameOver(Player player) {
        Bot[] playerBots = player.getBots();
        for (Bot bot : playerBots) {
            if (player.checkEndGame(bot)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        // Verificar se o jogo terminou com base nas condições de ambos os jogadores
        return isGameOver(player1) || isGameOver(player2);
    }

    private void playRound() {
        // Implemente a lógica da rodada aqui, como movimento de bots, verificação de
        // vitória, etc.
        Player currentPlayer = (currentPlayerTurn == 0) ? player1 : player2;
        Player otherPlayer = (currentPlayerTurn == 0) ? player2 : player1;

        // Lógica de movimento de bots, etc.
        // Exemplo:
        moveBots(currentPlayer.getBots(), otherPlayer.getBots());
    }

    private void moveBots(Bot[] currentPlayerBots, Bot[] otherPlayerBots) {
        // Implemente a lógica de movimento dos bots
        for (Bot bot : currentPlayerBots) {
            // Lógica de movimento para cada bot
            bot.move(otherPlayerBots);
        }
    }

    public void nextTurn() {
        // Implemente a lógica para avançar para a próxima rodada, trocar de jogador,
        // etc.
        currentPlayerTurn = (currentPlayerTurn + 1) % 2; // Alternar entre jogador 1 e jogador 2
    }

}
