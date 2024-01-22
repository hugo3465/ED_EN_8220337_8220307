package api.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para a classe Flag.
 */
public class FlagTest {

    @Test
    public void testGetIndex() {
        // Arrange
        int vertex = 5;

        // Act
        Flag flag = new Flag(vertex);

        // Assert
        assertEquals(vertex - 1, flag.getIndex());
    }

    @Test
    public void testSetIndex() {
        // Arrange
        int initialVertex = 3;
        int newIndex = 7;

        // Act
        Flag flag = new Flag(initialVertex);
        flag.setIndex(newIndex);

        // Assert
        assertEquals(newIndex, flag.getIndex());
    }

    @Test
    void testGetIndex2() {
        
    }

    @Test
    void testSetIndex2() {
        
    }
}
