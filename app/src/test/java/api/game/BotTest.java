package api.game;

import api.algorithms.interfaces.BotAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Bot.
 */
public class BotTest {

    /**
     * Testa a criação de um bot com coordenadas e algoritmo inicial.
     */
    @Test
    public void testBotCreation() {
        // Cria um algoritmo de exemplo (pode substituir por um algoritmo real)
        BotAlgorithm exampleAlgorithm = new ExampleAlgorithm();

        // Cria um bot com coordenadas (1, 2) e o algoritmo de exemplo
        Bot bot = new Bot(1, 2, exampleAlgorithm);

        // Verifica se o bot foi criado corretamente
        assertNotNull(bot); // verifica se é nulo
        assertEquals(1, bot.getX());
        assertEquals(2, bot.getY());
        assertEquals(exampleAlgorithm, bot.getAlgorithm());
    }

    /**
     * Testa a atualização das coordenadas de um bot.
     */
    @Test
    public void testUpdateCoordinates() {
        // Cria um bot com coordenadas (3, 4)
        Bot bot = new Bot(3, 4);

        // Atualiza as coordenadas para (5, 6)
        bot.setX(5);
        bot.setY(6);

        // Verifica se as coordenadas foram atualizadas corretamente
        assertEquals(5, bot.getX());
        assertEquals(6, bot.getY());
    }

    /**
     * Testa a atualização do algoritmo de movimentação de um bot.
     */
    @Test
    public void testUpdateAlgorithm() {
        // Cria um bot com coordenadas (7, 8) e um algoritmo de exemplo
        Bot bot = new Bot(7, 8, new ExampleAlgorithm());

        // Cria um novo algoritmo de exemplo para a atualização
        BotAlgorithm newAlgorithm = new ExampleAlgorithm();

        // Atualiza o algoritmo do bot
        bot.setAlgorithm(newAlgorithm);

        // Verifica se o algoritmo foi atualizado corretamente
        assertEquals(newAlgorithm, bot.getAlgorithm());
    }

    /**
     * Testa a representação em string de um bot.
     */
    @Test
    public void testToString() {
        // Cria um bot com coordenadas (9, 10) e um algoritmo de exemplo
        Bot bot = new Bot(9, 10, new ExampleAlgorithm());

        // Verifica se a representação em string está correta
        assertEquals("Bot em (9, 10) com algoritmo: ExampleAlgorithm", bot.toString());
    }

    /**
     * Testa um movimento válido, onde a nova posição não colide com outros bots.
     */
    @Test
    public void testCanMoveToValidMove() {
        // Criação de bots
        Bot bot1 = new Bot(0, 0);
        Bot bot2 = new Bot(1, 1);

        // Array de outros bots
        Bot[] otherBots = {bot2};

        // Movimento válido (nova posição não colide com outros bots)
        assertTrue(bot1.canMoveTo(2, 2, otherBots));
    }

    /**
     * Testa um movimento inválido, onde a nova posição colide com outro bot.
     */
    @Test
    public void testCanMoveToInvalidMove() {
        // Criação de bots
        Bot bot1 = new Bot(0, 0);
        Bot bot2 = new Bot(1, 1);

        // Array de outros bots
        Bot[] otherBots = {bot2};

        // Movimento inválido (nova posição colide com outro bot)
        assertFalse(bot1.canMoveTo(1, 1, otherBots));
    }

    /**
     * Testa um movimento válido quando o array de outros bots está vazio.
     */
    @Test
    public void testCanMoveToEmptyBotsArray() {
        // Criação de bots
        Bot bot1 = new Bot(0, 0);

        // Array vazio de outros bots
        Bot[] otherBots = {};

        // Movimento válido (nenhum bot para colidir)
        assertTrue(bot1.canMoveTo(1, 1, otherBots));
    }

}
