package api.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários para a classe Flag.
 */
public class FlagTest {

    /**
     * Testa o método getX() e getY() da classe Flag.
     */
    @Test
    public void testGetCoordinates() {
        // Cria uma bandeira com coordenadas iniciais (3, 7)
        Flag flag = new Flag(3, 7);

        // Verifica se as coordenadas retornadas são as esperadas
        assertEquals(3, flag.getX());
        assertEquals(7, flag.getY());
    }

    /**
     * Testa o método setCoordinates() da classe Flag.
     */
    @Test
    public void testSetCoordinates() {
        // Cria uma bandeira
        Flag flag = new Flag(0, 0);

        // Define novas coordenadas (5, 10)
        flag.setCoordinates(5, 10);

        // Verifica se as coordenadas foram definidas corretamente
        assertEquals(5, flag.getX());
        assertEquals(10, flag.getY());
    }

    /**
     * Testa o método toString() da classe Flag.
     */
    @Test
    public void testToString() {
        // Cria uma bandeira com coordenadas (1, 2)
        Flag flag = new Flag(1, 2);

        // Verifica se a representação em string está correta
        assertEquals("(1, 2)", flag.toString());
    }
}
