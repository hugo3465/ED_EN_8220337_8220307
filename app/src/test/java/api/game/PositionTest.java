package api.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {

    private static Position position;
    @BeforeAll
    public static void setUp() {
        position = new Position(42);
    }

    @Test
    public void testGetIndex() {
        assertEquals(42, position.getIndex());
    }

    @Test
    public void testSetIndex() {
        position.setIndex(20);
        assertEquals(20, position.getIndex());
    }

    @Test
    public void testToString() {
        assertEquals("42", position.toString());
    }

    // Adicione mais testes conforme necessário

    @Test
    public void testEquality() {
        Position position2 = new Position(42);

        assertEquals(position.getIndex(), position2.getIndex());
    }

    @Test
    public void testInequality() {

        Position position2 = new Position(40);

        assertNotEquals(position, position2);
    }
}
