package api.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes unitários para a classe Flag.
 */
public class FlagTest {

    private static Position position1;
    private static Position position2;

    @BeforeAll
    public static void setUp() {
        position1 = new Position(5);
        position2 = new Position(8);
    }

    @Test
    public void testToString() {
        Flag flag = new Flag(position1);
        assertEquals(position1.toString(), flag.toString());
    }
    @Test
    public void test(){
        Flag flag1 = new Flag(position1);
        Flag flag2 = new Flag(position1);

        assertEquals(flag1.getPosition().getIndex(), flag2.getPosition().getIndex());
    }

    @Test
    public void testCompareToEqualFlags() {
        Position position = new Position(5);
        Flag flag1 = new Flag(position);
        Flag flag2 = new Flag(position);

        assertEquals(0, flag1.equals(flag2));
    }

    @Test
    public void testCompareToDifferentFlags() {
        Position position1 = new Position(10);
        Position position2 = new Position(15);
        Flag flag1 = new Flag(position1);
        Flag flag2 = new Flag(position2);

        assertEquals(Integer.compare(position1.getIndex(), position2.getIndex()), flag1.equals(flag2)); // ACHO QUE TEM DE SER COMPARETO E NÃO EQUALS
    }


}
