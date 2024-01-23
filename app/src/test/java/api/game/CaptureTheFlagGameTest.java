package api.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.game.interfaces.IPlayer;
import api.map.GameMap;
import exceptions.InvalidMapException;

public class CaptureTheFlagGameTest {

    private CaptureTheFlagGame game;
    private Player player1, player2;
    private GameMap map;

    @BeforeEach
    void setUp() {
        Flag flag1 = new Flag(1);
        Flag enemyFlag1 = new Flag(10);
        Flag flag2 = new Flag(2);
        Flag enemyFlag2 = new Flag(9);

        Bot[] bots1 = new Bot[] { new Bot("Bot1", null, null), new Bot("Bot2", null, null) };
        Bot[] bots2 = new Bot[] { new Bot("Bot3", null, null), new Bot("Bot4", null, null) };

        player1 = new Player("Player1", flag1, enemyFlag1, bots1);
        player2 = new Player("Player2", flag2, enemyFlag2, bots2);

        map = new GameMap();
        try {
            map.importMap("src/test/java/api/game/testMap.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }

        game = new CaptureTheFlagGame(map, player1, player2);
    }

    @Test
    void testGetGameMap() {
        GameMap gameMap = game.getGameMap();

        assertEquals(map, gameMap);
    }

    @Test
    void testIsGameOver_NoWinner() {
        int result = game.isGameOver();

        assertEquals(-1, result);
    }
}
