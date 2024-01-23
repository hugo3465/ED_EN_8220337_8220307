package api.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.map.GameMap;
import exceptions.InvalidMapException;

public class ShortestPathAlgorithmTest {

    private GameMap map;
    private Bot bot1;
    private Bot bot2;
    private ShortestPathAlgorithm algorithmForTheTests;

    @BeforeEach
    void setUp() {

        map = new GameMap();
        try {
            map.importMap("src/test/java/api/algorithms/testMap.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }

        // cira as flags
        Flag enemyFlagBot1 = new Flag(1);
        Flag enemyFlagBot2 = new Flag(10);

        // Cria bots para teste
        bot1 = new Bot("Bot1", new ShortestPathAlgorithm(map), enemyFlagBot2);
        bot2 = new Bot("Bot2",new ShortestPathAlgorithm(map), enemyFlagBot1);

        // cria o álgoritmo que vai ser usado para os testes
        algorithmForTheTests = new ShortestPathAlgorithm(map);
    }

    @Test
    void testGetNextMovement() {
        int bot1VerticeIndex = 0;
        int bot2VerticeIndex = 9;
        int bot1EndIndex = 4;
        int bot2EndIndex = 0;
        int bot1ExpectedVerticeIndex = 0;
        int bot2ExpectedVerticeIndex = 9;

        // Define a posição inicial dos bots no mapa
        map.setVertice(bot1VerticeIndex, bot1);
        map.setVertice(bot2VerticeIndex, bot2);

        // Testa se o próximo movimento do Bot1 é o esperado
        int nextMovementBot1 = algorithmForTheTests.getNextMovement(bot1VerticeIndex, bot1EndIndex, bot1);
        assertEquals(bot1ExpectedVerticeIndex, nextMovementBot1);

        // Testa se o próximo movimento do Bot2 é o esperado
        int nextMovementBot2 = algorithmForTheTests.getNextMovement(bot2VerticeIndex, bot2EndIndex, bot2);
        assertEquals(bot2ExpectedVerticeIndex, nextMovementBot2);
    }

    @Test
    void testHasBot() {
        int bot1PositionIndex = 0;
        map.setVertice(bot1PositionIndex, bot1);

        assertTrue(algorithmForTheTests.hasBot(bot1PositionIndex), "testHasBot  nao conseguiu localizar o bot");
    }

    @Test
    void testUpdateBotLocation() {
        int bot1PositionIndex = 0;
        int bot1NewPositionIndex = 1;

        map.setVertice(bot1PositionIndex, bot1);

        algorithmForTheTests.updateBotLocation(bot1PositionIndex, bot1NewPositionIndex, bot1);

        assertTrue(algorithmForTheTests.hasBot(bot1NewPositionIndex), "testUpdateBotLocation  nao conseguiu localizar o bot");

    }
}
