package api.game;

import api.algorithms.BreadthFirstSearchAlgorithm;
import api.algorithms.DepthFirstSearchAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Bot.
 */
public class BotTest {
//    private static Bot bot;
//    private static Bot[] otherBots;
//
//    @BeforeAll
//    public static void setUp() {
//        Position initialPosition = new Position(0);
//        bot = new Bot(initialPosition);
//
//        otherBots = new Bot[] {
//                new Bot(new Position(1)),
//                new Bot(new Position(2)),
//                new Bot(new Position(3))
//        };
//    }
//
//    @Test
//    public void testMoveWithBFS() {
//        Position initialPosition = new Position(0);
//
//        Bot bot = new Bot(initialPosition);
//        bot.setMovementAlgorithm(new BreadthFirstSearchAlgorithm());
//
//        Bot[] otherBots = { new Bot(new Position(1)), new Bot(new Position(2)) };
//
//        bot.move(otherBots);
//
//        assertNotNull(bot.getPosition());
//    }
//
//    @Test
//    public void testMoveWithDFS() {
//        Position initialPosition = new Position(0);
//
//        Bot bot = new Bot(initialPosition);
//        bot.setMovementAlgorithm(new DepthFirstSearchAlgorithm());
//
//        Bot[] otherBots = { new Bot(new Position(1)), new Bot(new Position(2)) };
//
//        bot.move(otherBots);
//
//        assertNotNull(bot.getPosition());
//    }
//
//    @Test
//    public void testCanMoveToValidPosition() {
//        Position initialPosition = new Position(0);
//        Bot bot = new Bot(initialPosition);
//        Bot otherBot = new Bot(new Position(1));
//
//        assertTrue(bot.canMoveTo(new Position(2), new Bot[] { otherBot }));
//    }
//
//    @Test
//    public void testCanMoveToInvalidPosition() {
//        Position initialPosition = new Position(0);
//        Bot bot = new Bot(initialPosition);
//        Bot otherBot = new Bot(new Position(1));
//
//        assertFalse(bot.canMoveTo(new Position(1), new Bot[] { otherBot }));
//    }
//    @Test
//    public void testRecalculateMove() {
//        Position initialPosition = new Position(0);
//        Bot bot = new Bot(initialPosition);
//        bot.setMovementAlgorithm(new ShortestPathAlgorithm<>());
//        bot.setEnemyFlagPosition(new Position(5));
//
//        Bot[] otherBots = { new Bot(new Position(1)), new Bot(new Position(2)) };
//
//        bot.recalculateMove(otherBots, new Position(1));
//
//        assertNotNull(bot.getPosition());
//    }
}
