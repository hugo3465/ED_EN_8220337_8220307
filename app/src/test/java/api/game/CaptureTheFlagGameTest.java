package api.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.map.GameMap;

public class CaptureTheFlagGameTest {

    private CaptureTheFlagGame game;
    private Player player1, player2;
    private GameMap map;

    @BeforeEach
    void setUp() {
        Flag flag1 = new Flag(1);
        Flag enemyFlag1 = new Flag(10);
        Flag flag2 = new Flag(20);
        Flag enemyFlag2 = new Flag(30);

        Bot[] bots1 = new Bot[] { new Bot("Bot1", null, null), new Bot("Bot2", null, null) };
        Bot[] bots2 = new Bot[] { new Bot("Bot3", null, null), new Bot("Bot4", null, null) };

        player1 = new Player("Player1", flag1, enemyFlag1, bots1);
        player2 = new Player("Player2", flag2, enemyFlag2, bots2);

        map = new GameMap();
        map.generateRandomMap(40, false, 0.5);
        game = new CaptureTheFlagGame(map, player1, player2);
    }

    @Test
    void testGetCurrentPlayer() {
        // Execução
        Player currentPlayer = game.getCurrentPlayer();

        // Verificação
        assertEquals(player1, currentPlayer);
    }

    @Test
    void testGetGameMap() {
        // Execução
        GameMap gameMap = game.getGameMap();

        // Verificação
        assertEquals(map, gameMap);
    }

    @Test
    void testIsGameOver_NoWinner() {
        // Execução
        int result = game.isGameOver();

        // Verificação
        assertEquals(-1, result);
    }

    @Test
    void testIsGameOver_Player1Wins() {
        // Configuração
        Bot bot = player1.getNextBot();
        bot.setIndex(player1.getEnemyFlag().getIndex());

        // Execução
        int result = game.isGameOver();

        // Verificação
        assertEquals(1, result);
    }

    @Test
    void testIsGameOver_Player2Wins() {
        // Configuração
        Bot bot = player2.getNextBot();
        bot.setIndex(player2.getEnemyFlag().getIndex());

        // Execução
        int result = game.isGameOver();

        // Verificação
        assertEquals(2, result);
    }

    @Test
    void testNextTurn() {
        // Execução
        Player nextPlayer = game.nextTurn();

        // Verificação
        assertEquals(player2, nextPlayer);
    }

    @Test
    void testPlayRound() {
        // Execução
        Bot playedBot = game.playRound(player1);

        // Verificação
        assertNotNull(playedBot);
        assertEquals(player1.getNextBot(), playedBot);
    }
}
