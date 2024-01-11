package api.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {

    private static Flag flag;
    private static Player player;

    /**
     * Inicializa uma bandeira e um jogador antes de todos os testes.
     */
    @BeforeAll
    public static void init() {
        // Cria uma bandeira com coordenadas (0, 0)
         flag = new Flag(0, 0);
        // Cria um jogador com a bandeira criada
         player = new Player(flag);
    }

    /**
     * Testa o método addBot da classe Player.
     */
    @Test
    public void testAddBot() {

        // Cria um bot com coordenadas iniciais (0, 0) e um algoritmo fictício
        Bot bot = new Bot(0, 0, new DummyAlgorithm());

        // Adiciona o bot ao jogador
        player.addBot(bot, new DummyAlgorithm());

        // Verifica se o bot foi adicionado corretamente à fila do jogador
        assertFalse(player.getBotQueue().isEmpty());
        assertEquals(bot, player.getBotQueue().front());

        // Verifica se a posição inicial do bot foi atribuída corretamente
        assertEquals(flag.getX(), bot.getX());
        assertEquals(flag.getY(), bot.getY());
    }
}
