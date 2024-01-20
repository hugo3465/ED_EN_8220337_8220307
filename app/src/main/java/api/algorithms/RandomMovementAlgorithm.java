package api.algorithms;

import javax.swing.text.html.HTMLDocument.Iterator;

import api.DataStructures.ArrayList.OrderedArrayList.OrderedArrayList;
import api.DataStructures.ArrayList.OrderedArrayList.OrderedListADT;
import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedListADT;
import api.DataStructures.Queue.LinkedQueue.LinkedQueue;
import api.DataStructures.Queue.LinkedQueue.QueueADT;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.map.GameMap;

public class RandomMovementAlgorithm implements MovementAlgorithm {

    private GameMap map;
    private QueueADT<Integer> calculatedPath; // guardda os índices para onde o bot tem de se deslocar

    public RandomMovementAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedQueue<>();
    }

    private void calculatePath(int startVertex, int endVertex) {
        // OrderedListADT<Integer> visitedIndexes = new OrderedArrayList<>(); // serve
        // para não repetir vértices
        int currentIndex = startVertex;

        // Adiciona o vértice inicial na fila e nos vértices visitados
        calculatedPath.enqueue(currentIndex);
        // visitedIndexes.add(startVertex);

        while (currentIndex != endVertex) {
            int[] neighbors = getNeighbors(currentIndex);
            int randomNeighbor = getRandomNeighbor(neighbors);

            if (randomNeighbor != -1) { // && !visitedIndexes.contains(randomNeighbor)
                currentIndex = randomNeighbor;
                calculatedPath.enqueue(currentIndex);
                // visitedIndexes.add(currentIndex);
            } else {
                // Se não houver vizinhos disponíveis, o cálculo vai parar
                break;
            }
        }

        calculatedPath.dequeue(); // remover do caminho o index onde o bot se encontra

    }

    private int[] getNeighbors(int index) {
        double[][] adjMatrix = map.getAdjacencyMatrix();
        int numVertices = adjMatrix.length;
        UnorderedListADT<Integer> neighborsList = new UnorderedArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i] > 0 && !hasBot(i)) { // TODO envés de > 0 acho que devo meter != de infinito
                neighborsList.addToFront(i);
            }
        }

        int i = 0;
        int[] neighbors = new int[neighborsList.size()];
        // passar de arrayList para array comum
        for (Integer currentIndex : neighborsList) {
            neighbors[i] = currentIndex;
            i++;
        }

        return neighbors;
    }

    private int getRandomNeighbor(int[] neighbors) {
        if (neighbors.length == 0) {
            return -1; // Sem vizinhos disponíveis
        }

        int randomIndex = generateRandomNumber(0, neighbors.length - 1);
        return neighbors[randomIndex];
    }

    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {

        if (calculatedPath.isEmpty()) {
            // Se o caminho calculado estiver vazio, recalcule o caminho
            calculatePath(currentIndex, endIndex);
        }

        // TODO while para testes para saber se fez bem o caminho
        // while (!this.calculatedPath.isEmpty()) {
        //     System.out.print(calculatedPath.dequeue() + " ");
        // }
        // System.out.println("\n");

        int nextIndex = -1;
        while (!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.dequeue();

            // Verifica se o vértice desenfileirado contém um bot
            if (hasBot(dequeuedIndex)) {
                // Recalcula o caminho se o vértice desenfileirado contiver um bot
                calculatePath(dequeuedIndex, endIndex);
            } else {
                // Se não contiver um bot, define a lógica padrão
                nextIndex = dequeuedIndex;
                updateBotLocation(currentIndex, nextIndex, currentBot);
                return nextIndex;
            }
        }

        // Se não houver mais movimentos disponíveis, retorna o índice atual
        return currentIndex;
    }

    @Override
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot) {

        if (currentIndex != nextIndex) {
            if (bot.getTimesMoved() == 0) {
                // se for a primeira vez que se mexe, não coloca o antigo vértice a null, para
                // não apagar a bandeira
                map.setVertice(nextIndex, bot);
            } else {
                map.setVertice(currentIndex, null); // define o vértice antigo a null
                map.setVertice(nextIndex, bot); // vai para o novo vértice
            }
        }
    }

    @Override
    public boolean hasBot(int vertex) {
        // se o vértice for diferente de null, e o que estiver lá for um bot e não uma
        // flag, ent retorna True
        return (map.getVertices()[vertex] != null && map.getVertices()[vertex] instanceof Bot);
    }

    /**
     * Gera um número aleatório no intervalo especificado.
     *
     * @param min Valor mínimo do intervalo.
     * @param max Valor máximo do intervalo.
     * @return Um número aleatório no intervalo [min, max].
     */
    public static int generateRandomNumber(long min, long max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
