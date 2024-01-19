package application;

import java.util.Scanner;

import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.RandomMovementAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.CaptureTheFlagGame;
import api.game.Flag;
import api.game.Player;
import api.game.interfaces.GameEntity;
import api.map.GameMap;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numBots = 0;
        String botName = null;

        GameMap map = new GameMap("unidirecional");

        map.generateRandomMap(15, false, 0.60);
        // map.exportMap("C:\\Users\\User\\Desktop\\" + map.getMapName() + ".txt");
        map.exportMap("C:\\Users\\pedro\\Desktop\\" + map.getMapName() + ".txt");
        int positionPlayer1 = 0;
        int positionPlayer2 = 12;

        Flag flagPlayer1 = new Flag(positionPlayer1);
        Flag flagPlayer2 = new Flag(positionPlayer2);

        do {
            System.out.println("AVISO: O número de bots é entre 1 e 4");
            System.out.println("Quantos bots deseja [1-4] ?");
            numBots = in.nextInt();
        } while (numBots <= 0 || numBots >= 5);

        Bot[] botsPlayer1 = new Bot[numBots];
        Bot[] botsPlayer2 = new Bot[numBots]; // Cria um array de bots

        System.out.println("Player 1:");

        for (int i = 0; i < numBots - 1; i++) {
            System.out.println("Qual o nome que você deseja para o Bot " + (i + 1) + "?");
            botName = in.next(); // Solicita o nome do bot
            MovementAlgorithm<GameEntity> algorithm = chooseAlgorithm(in, botsPlayer1, i, numBots, map);
            botsPlayer1[i] = new Bot(botName, algorithm, flagPlayer2);
        }

        // Solicita o nome do último bot do Player 1
        System.out.println("Qual o nome que você deseja para o último Bot do Player 1?");
        botName = in.next();
        botsPlayer1[numBots - 1] = new Bot(botName, chooseAlgorithm(in, botsPlayer1, numBots - 1, numBots, map),
                flagPlayer2);

        System.out.println("Qual o nome que você deseja para o Player 1");
        String nameplayer1 = in.next();

        Player player1 = new Player(nameplayer1, flagPlayer1.getPosition(), flagPlayer2.getPosition(), botsPlayer1);

        System.out.println("Player 2:");

        for (int i = 0; i < numBots - 1; i++) {
            System.out.println("Qual o nome que você deseja para o Bot " + (i + 1) + "?");
            botName = in.next(); // Solicita o nome do bot
            MovementAlgorithm<GameEntity> algorithm = chooseAlgorithm(in, botsPlayer2, i, numBots, map);
            botsPlayer2[i] = new Bot(botName, algorithm, flagPlayer1);
        }

        // Solicita o nome do último bot do Player 2
        System.out.println("Qual o nome que você deseja para o último Bot do Player 2?");
        botName = in.next();
        botsPlayer2[numBots - 1] = new Bot(botName, chooseAlgorithm(in, botsPlayer2, numBots - 1, numBots, map),
                flagPlayer1);

        System.out.println("Qual o nome que você deseja para o Player 2?");
        String nameplayer2 = in.next();

        Player player2 = new Player(nameplayer2, flagPlayer2.getPosition(), flagPlayer1.getPosition(), botsPlayer2);

        CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

        // Iniciar o jogo
        game.startGame(player1, player2);
    }

    private static MovementAlgorithm<GameEntity> chooseAlgorithm(Scanner in, Bot[] bots, int botIndex, int numBots,
            GameMap map) {
        MovementAlgorithm<GameEntity> algorithm = null;
        boolean algoritmoRepetido;

        // Verifica se todos os algoritmos já foram escolhidos
        boolean allAlgorithmsChosen = true;
        for (int i = 0; i < numBots; i++) {
            if (bots[i] == null || bots[i].getMovementAlgorithm() == null) {
                allAlgorithmsChosen = false;
                break;
            }
        }

        if (allAlgorithmsChosen) {
            System.out.println("Todos os algoritmos já foram escolhidos. Não é possível escolher mais.");
            return null;
        }

        do {
            algoritmoRepetido = false; // Reinicializa algoritmoRepetido no início do loop

            System.out.println("Escolha o algoritmo para o Bot " + (botIndex + 1) + ":");
            System.out.println("1. Shortest Path Algorithm");
            System.out.println("2. Random Movement Algorithm");

            int escolha = in.nextInt();

            switch (escolha) {
                case 1:
                    algorithm = new ShortestPathAlgorithm(map);
                    break;
                case 2:
                    algorithm = new RandomMovementAlgorithm(map);
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    continue; // Volta para o início do loop sem verificar algoritmo repetido
            }

            // Verifica se o algoritmo escolhido já foi escolhido por algum bot anterior
            for (int j = 0; j < botIndex; j++) {
                if (bots[j] != null && bots[j].getMovementAlgorithm() != null
                        && bots[j].getMovementAlgorithm().getClass() == algorithm.getClass()) {
                    algoritmoRepetido = true;
                    System.out.println("Algoritmo repetido. Escolha novamente.");
                    break;
                }
            }

            // Se o bot for o 4º (índice 3) e o número total de bots for 4, permite qualquer
            // algoritmo
            if (botIndex == 3 && numBots == 4) {
                break;
            }

        } while (algorithm == null || algoritmoRepetido);

        return algorithm;
    }
}
