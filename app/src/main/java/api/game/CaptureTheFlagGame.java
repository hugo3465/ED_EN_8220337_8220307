package api.game;

import api.algorithms.interfaces.MovementAlgorithm;
import api.map.GameMap;

/**
 * Representa o jogo Capture the Flag.
 */
public class CaptureTheFlagGame {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;

    public CaptureTheFlagGame() {
        // Inicialize os jogadores, bots e outras configurações do jogo aqui
        initializeGame();
    }

    private void initializeGame() {
        // Exemplo de inicialização com dois jogadores e uma bandeira para cada
        Position player1FlagPosition = new Position(0, 0);
        Position player2FlagPosition = new Position(10, 10);

        Position player1EnemyFlagPosition = new Position(10, 10);
        Position player2EnemyFlagPosition = new Position(0, 0);

        player1 = new Player("Player1", 3, player1FlagPosition, player1EnemyFlagPosition);
        player2 = new Player("Player2", 3, player2FlagPosition, player2EnemyFlagPosition);

        // Atribua algoritmos de movimentação aos bots
        MovementAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithm();

        for (Bot bot : player1.getBots()) {
            player1.assignAlgorithmToBot(bot, shortestPathAlgorithm);
        }

        for (Bot bot : player2.getBots()) {
            player2.assignAlgorithmToBot(bot, shortestPathAlgorithm);
        }
    }

        // Implemente métodos para iniciar o jogo, avançar rodadas, etc.
}
