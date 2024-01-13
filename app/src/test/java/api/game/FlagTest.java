package api.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários para a classe Flag.
 */
public class FlagTest {

    private static Position initialPosition;

    @BeforeAll
    public static void setUp() {
        initialPosition = new Position(2, 4);
    }

    @Test
    public void test001GetPosition() {
        Flag flag = new Flag(initialPosition);

        assertEquals(initialPosition, flag.getPosition());
    }

    @Test
    public void test002SetPosition() {
        Flag flag = new Flag(initialPosition);

        Position newPosition = new Position(6, 8);
        flag.setPosition(newPosition);

        assertEquals(newPosition, flag.getPosition());
    }

    @Test
    public void test003SetCoordinates() {
        Flag flag = new Flag(initialPosition);

        int newX = 10;
        int newY = 20;
        flag.setCoordinates(newX, newY);

        assertEquals(newX, flag.getPosition().getX());
        assertEquals(newY, flag.getPosition().getY());
    }

    @Test
    public void test004ToString() {
        Flag flag = new Flag(initialPosition);

        assertEquals("(" + initialPosition.getX() + ", " + initialPosition.getY() + ")", flag.toString());
    }
}
