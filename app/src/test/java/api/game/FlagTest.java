package api.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para a classe Flag.
 */
public class FlagTest {

    @Test
    public void testGetIndex() {

        int vertex = 5;

        Flag flag = new Flag(vertex);

        assertEquals(vertex - 1, flag.getCurrentIndex());
    }

    @Test
    public void testSetIndex() {

        int initialVertex = 3;
        int newIndex = 7;

        Flag flag = new Flag(initialVertex);
        flag.setCurrentIndex(newIndex);

        assertEquals(newIndex, flag.getCurrentIndex());
    }
}
