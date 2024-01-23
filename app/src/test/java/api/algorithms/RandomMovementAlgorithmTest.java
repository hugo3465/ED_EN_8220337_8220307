package api.algorithms;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;
import exceptions.InvalidMapException;

public class RandomMovementAlgorithmTest {
    private GameMap map;
    private Bot bot1;
    private MovementAlgorithm algorithmForTheTests;
    private Flag enemyFlag;

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
        enemyFlag = new Flag(10);

        // cria o álgoritmo que vai ser usado para os testes
        algorithmForTheTests = new RandomMovementAlgorithm(map);

        // Cria bots para teste
        bot1 = new Bot("Bot1", algorithmForTheTests, enemyFlag);

    }

    // @Test
    // void testGenerateRandomNumber() {
    //     int bot1VerticeIndex = 0;
    //     int bot1EndIndex = enemyFlag.getCurrentIndex();
    //     int[] bot1PossibleIndexes = {1, 4, 6};

    //     // Define a posição inicial do bot
    //     bot1.setCurrentIndex(bot1VerticeIndex);

    //     // Testa se o próximo movimento do Bot1 é o esperado
    //     int nextMovementBot1 = algorithmForTheTests.getNextMovement(bot1VerticeIndex, bot1EndIndex, bot1);
    //     System.out.println(nextMovementBot1);
    //     assertTrue(Arrays.asList(bot1PossibleIndexes).contains(nextMovementBot1),
    //         "O próximo movimento do Bot1 não está dentro dos índices possíveis.");

    // }

    @Test
    void testGetNextMovement() {
        int bot1InitialVerticeIndex = 0;
        int bot1EndIndex = enemyFlag.getCurrentIndex();

        // Define a posição inicial do bot
        bot1.setCurrentIndex(bot1InitialVerticeIndex);

        // Testa se não retornou o vertice onde está
        int nextMovementBot1 = algorithmForTheTests.getNextMovement(bot1InitialVerticeIndex, bot1EndIndex, bot1);
        assertNotEquals(bot1InitialVerticeIndex, nextMovementBot1);
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

        assertTrue(algorithmForTheTests.hasBot(bot1NewPositionIndex),
                "testUpdateBotLocation  nao conseguiu localizar o bot");
    }
}
