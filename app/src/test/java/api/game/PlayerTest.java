package api.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player player;
    private Bot[] bots;

    @BeforeEach
    void setUp() {
        Flag myFlag = new Flag(1);
        Flag enemyFlag = new Flag(10);

        bots = new Bot[] { new Bot("Bot1", null, null), new Bot("Bot2", null, null) };

        player = new Player("Player1", myFlag, enemyFlag, bots);
    }

    @Test
    void testCheckEndGame() {
        // Configuração
        Bot bot = bots[0];
        bot.setCurrentIndex(player.getEnemyFlag().getCurrentIndex()); // Posiciona o bot na bandeira inimiga

        // Execução e Verificação
        assertTrue(player.checkEndGame(bot));
    }

    @Test
    void testGetEnemyFlag() {
        // Execução
        Flag enemyFlag = player.getEnemyFlag();

        // Verificação
        assertNotNull(enemyFlag);
        assertEquals(9, enemyFlag.getCurrentIndex());
    }

    @Test
    void testGetFlag() {
        // Execução
        Flag myFlag = player.getFlag();

        // Verificação
        assertNotNull(myFlag);
        assertEquals(0, myFlag.getCurrentIndex());
    }

    @Test
    void testGetName() {
        // Execução
        String playerName = player.getName();

        // Verificação
        assertEquals("Player1", playerName);
    }

    @Test
    void testGetNextBot() {

        // Execução
        Bot nextBot = player.getNextBot();

        // Verificação
        assertNotNull(nextBot);
        assertEquals("Bot1", nextBot.getName());

        // Verifica se a fila foi rotacionada corretamente
        Bot rotatedBot = player.getNextBot();
        assertEquals("Bot2", rotatedBot.getName());

        // Rotaciona novamente para verificar o comportamento circular
        Bot circularBot = player.getNextBot();
        assertEquals("Bot1", circularBot.getName());
    }

    @Test
    void testSetName() {
        // Configuração
        player.setName("NewPlayerName");

        // Execução
        String updatedName = player.getName();

        // Verificação
        assertEquals("NewPlayerName", updatedName);
    }

}
