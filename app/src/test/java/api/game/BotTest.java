package api.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.map.GameMap;
import exceptions.InvalidMapException;

public class BotTest {

    private Bot bot;
    private Flag mockEnemyFlag;
    private GameMap mockGameMap;

    @BeforeEach
    void setUp() {
        mockGameMap = new GameMap();
        try {
            mockGameMap.importMap("src/test/java/api/game/testMap.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }
        mockEnemyFlag = new Flag(1);
        bot = new Bot("BotName", new ShortestPathAlgorithm(mockGameMap), mockEnemyFlag);
    }

    @Test
    void testGetIndex() {
        // Configuração
        int expectedIndex = 10;
        bot.setCurrentIndex(expectedIndex);

        // Execução
        int actualIndex = bot.getCurrentIndex();

        // Verificação
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void testGetMovementAlgorithm() {
        // Configuração
        RandomMovementAlgorithm expectedAlgorithm = new RandomMovementAlgorithm(mockGameMap);
        bot.setMovementAlgorithm(expectedAlgorithm);

        // Execução
        RandomMovementAlgorithm actualAlgorithm = (RandomMovementAlgorithm) bot.getMovementAlgorithm();

        // Verificação
        assertNotNull(actualAlgorithm);
        assertEquals(expectedAlgorithm, actualAlgorithm);
    }

    @Test
    void testMove() {
        // Configuração
        int initialIndex = 0;
        bot.setCurrentIndex(initialIndex);

        // Execução
        int newIndex = bot.move();

        // Verificação
        assertEquals(initialIndex, newIndex);
    }

    @Test
    void testSetIndex() {
        // Configuração
        int newIndex = 99;

        // Execução
        bot.setCurrentIndex(newIndex);
        int actualIndex = bot.getCurrentIndex();

        // Verificação
        assertEquals(newIndex, actualIndex);
    }

    @Test
    void testSetMovementAlgorithm() {
        // Configuração
        ShortestPathAlgorithm newAlgorithm = new ShortestPathAlgorithm(mockGameMap);

        // Execução
        bot.setMovementAlgorithm(newAlgorithm);
        ShortestPathAlgorithm actualAlgorithm = (ShortestPathAlgorithm) bot.getMovementAlgorithm();

        // Verificação
        assertNotNull(actualAlgorithm);
        assertEquals(newAlgorithm, actualAlgorithm);
    }
}
