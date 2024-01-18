package application;

import java.util.Scanner;

import api.algorithms.ShortestPathAlgorithm;
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
        int numBots;

        GameMap map = new GameMap("unidirecional");

        map.generateRandomMap(8, false, 0.95);
        map.exportMap("C:\\Users\\pedro\\Desktop\\" + map.getMapName() + ".txt");
        int positionPlayer1 = 1;
        int positionPlayer2 = 7;

        Flag flagPlayer1 = new Flag(positionPlayer1);
        Flag flagPlayer2 = new Flag(positionPlayer2);

        do {
            System.out.println("Quantos bots deseja[1-4]?");
            numBots = in.nextInt();
        } while (numBots <= 0 || numBots >= 5);

        Bot[] botsPlayer1 = new Bot[numBots];
        Bot[] botsPlayer2 = new Bot[numBots]; // Cria um array de bots

        System.out.println("Player 1:");

        for (int i = 0; i < numBots - 1; i++) {
            botsPlayer1[i] = new Bot("John Mendes", null, flagPlayer2);

            System.out.println("Escolha o algoritmo para o Bot " + (i + 1) + ":");
            System.out.println("1. Shortest Path Algorithm");
            System.out.println("2. Random Movement Algorithm");

            int escolha = in.nextInt();
            MovementAlgorithm<GameEntity> algorithm = null;

            switch (escolha) {
                case 1:
                    algorithm = new ShortestPathAlgorithm(map);
                    break;
                case 2:
                    //algorithm = new RandomMovementAlgorithm(map);
                    break;
                default:
                    System.out.println("Escolha inválida. Usando o algoritmo padrão.");
                    algorithm = new ShortestPathAlgorithm(map);
            }

            boolean algoritmoRepetido = false;
            for (int j = 0; j < i; j++) {
                if (botsPlayer1[j].getMovementAlgorithm().getClass() == algorithm.getClass()) {
                    algoritmoRepetido = true;
                    break;
                }
            }

            if (algoritmoRepetido) {
                System.out.println("Algoritmo repetido. Escolha novamente.");
                i--;
            } else {
                botsPlayer1[i].setMovementAlgorithm(algorithm);
            }
        }

        System.out.println("Escolha o algoritmo para o último Bot do jogador 1:");
        System.out.println("1. Shortest Path Algorithm");
        System.out.println("2. Random Movement Algorithm");

        int escolhaUltimoBotPlayer1 = in.nextInt();
        MovementAlgorithm<GameEntity> algorithmUltimoBotPlayer1 = null;

        switch (escolhaUltimoBotPlayer1) {
            case 1:
                algorithmUltimoBotPlayer1 = new ShortestPathAlgorithm(map);
                break;
            case 2:
                //algorithmUltimoBotPlayer1 = new RandomMovementAlgorithm(map);
                break;
            default:
                System.out.println("Escolha inválida. Usando o algoritmo padrão.");
                algorithmUltimoBotPlayer1 = new ShortestPathAlgorithm(map);
        }

        botsPlayer1[numBots - 1] = new Bot("John Mendes", algorithmUltimoBotPlayer1, flagPlayer2);

        System.out.println("Qual o nome que voce deseja");
        String nameplayer1 = in.next();

        Player player1 = new Player(nameplayer1, flagPlayer1.getPosition(), flagPlayer2.getPosition(), botsPlayer1);

        for (int i = 0; i < numBots - 1; i++) {
            botsPlayer2[i] = new Bot("Bot Player 2", null, flagPlayer1);

            System.out.println("Escolha o algoritmo para o Bot " + (i + 1) + " do Player 2:");
            System.out.println("1. Shortest Path Algorithm");
            System.out.println("2. Random Movement Algorithm");

            int escolha = in.nextInt();
            MovementAlgorithm<GameEntity> algorithm = null;

            switch (escolha) {
                case 1:
                    algorithm = new ShortestPathAlgorithm(map);
                    break;
                case 2:
                    //algorithm = new RandomMovementAlgorithm(map);
                    break;
                default:
                    System.out.println("Escolha inválida. Usando o algoritmo padrão.");
                    algorithm = new ShortestPathAlgorithm(map);
            }

            boolean algoritmoRepetidoPlayer2 = false;
            for (int j = 0; j < i; j++) {
                if (botsPlayer2[j].getMovementAlgorithm().getClass() == algorithm.getClass()) {
                    algoritmoRepetidoPlayer2 = true;
                    break;
                }
            }

            if (algoritmoRepetidoPlayer2) {
                System.out.println("Algoritmo repetido. Escolha novamente.");
                i--;
            } else {
                botsPlayer2[i].setMovementAlgorithm(algorithm);
            }
        }

        System.out.println("Escolha o algoritmo para o último Bot do jogador 2:");
        System.out.println("1. Shortest Path Algorithm");
        System.out.println("2. Random Movement Algorithm");

        int escolhaUltimoBotPlayer2 = in.nextInt();
        MovementAlgorithm<GameEntity> algorithmUltimoBotPlayer2 = null;

        switch (escolhaUltimoBotPlayer2) {
            case 1:
                algorithmUltimoBotPlayer2 = new ShortestPathAlgorithm(map);
                break;
            case 2:
                //algorithmUltimoBotPlayer2 = new RandomMovementAlgorithm(map);
                break;
            default:
                System.out.println("Escolha inválida. Usando o algoritmo padrão.");
                algorithmUltimoBotPlayer2 = new ShortestPathAlgorithm(map);
        }

        botsPlayer2[numBots - 1] = new Bot("Bot Player 2", algorithmUltimoBotPlayer2, flagPlayer1);

        System.out.println("Qual o nome que você deseja para o Player 2?");
        String nameplayer2 = in.next();

        Player player2 = new Player(nameplayer2, flagPlayer1.getPosition(), flagPlayer2.getPosition(), botsPlayer2);

        CaptureTheFlagGame game = new CaptureTheFlagGame(map, player1, player2);

        // Iniciar o jogo
        game.startGame(player1, player2);
    }
}
