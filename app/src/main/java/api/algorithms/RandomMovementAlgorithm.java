package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.dataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.dataStructures.ArrayList.UnorderedArrayList.UnorderedListADT;
import api.dataStructures.Queue.LinkedQueue.LinkedQueue;
import api.dataStructures.Queue.LinkedQueue.QueueADT;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;

public class RandomMovementAlgorithm implements MovementAlgorithm {

    private GameMap map;
    private QueueADT<Integer> calculatedPath; // guarda os índices para onde o bot tem de se deslocar

    public RandomMovementAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedQueue<>();
    }

    private boolean calculatePath(int startVertex, int endVertex) {

        int currentIndex = startVertex;

        // Adiciona o vértice inicial na fila e nos vértices visitados
        calculatedPath.enqueue(currentIndex);
        // visitedIndexes.add(startVertex);

        while (currentIndex != endVertex) {
            int[] neighbors = getNeighbors(currentIndex);
            int randomNeighbor = getRandomNeighbor(neighbors);

            // System.out.println(neighbors.length);

            if (randomNeighbor != -1) {
                currentIndex = randomNeighbor;
                calculatedPath.enqueue(currentIndex);
                // visitedIndexes.add(currentIndex);
            } else {
                // Se não houver vizinhos disponíveis, o cálculo vai parar e vai limpar o
                // caminho que calculou
                // break;
                calculatedPath = new LinkedQueue<>();
                return false;
            }
        }

        calculatedPath.dequeue(); // remover do caminho o index onde o bot se encontra

        return true;

    }

    private int[] getNeighbors(int index) {
        double[][] adjMatrix = map.getAdjacencyMatrix();
        int numVertices = adjMatrix.length;
        UnorderedListADT<Integer> neighborsList = new UnorderedArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i] > 0 && !hasBot(i)) {
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

    /**
     * vai calcular um caminho segundo o algoritmo associado, caso já não tenha
     * calculado, e retorna o próximo índice que o bot tem de ir
     * 
     * @param currentIndex
     * @param endIndex
     * @param currentBot
     * @return próximo índice para onde o bot tem de ir, caso não consiga ir para
     *         lado nenhum retorna o índice onde está
     */
    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {

        if (calculatedPath.isEmpty()) {
            // Se o caminho calculado estiver vazio, calcula o caminho
            calculatePath(currentIndex, endIndex);
        }

        int nextIndex = currentIndex;
        while (!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.dequeue();

            // Verifica se o vértice retirado contém um bot
            if (hasBot(dequeuedIndex)) {
                System.out.println("bot " + currentBot.getName() + " tentou ir para o índice " + dequeuedIndex
                        + " mas tem lá um bot, então vai ter de se recalcular o caminho");

                // Recalcula o caminho se o vértice retirado contiver um bot
                if (!calculatePath(currentIndex, endIndex)) {
                    // se não conseguiu calcular o caminho vai devolver o vertice atual
                    // break;
                    return currentIndex;
                }

                // caso consiga calcular o caminho depois de encontrar o bot, vai vltar a iterar
                // para mover para a próxima casa
                continue;

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

    /**
     * Atualiza a posição do bot no mapa. Atualizar no mapa significa atualizar no
     * vetoor de vértices da super class
     * 
     * @param currentIndex
     * @param nextIndex
     * @param bot
     */
    @Override
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot) {

        if (currentIndex != nextIndex) {
            if (bot.getTimesMoved() == 0 || map.getVertices()[currentIndex] instanceof Flag) {
                // se for a primeira vez que se mexe, não coloca o antigo vértice a null, para
                // não apagar a bandeira
                map.setVertice(nextIndex, bot);
            } else {
                map.setVertice(currentIndex, null); // define o vértice antigo a null
                map.setVertice(nextIndex, bot); // vai para o novo vértice
            }
        }
    }

    /**
     * Verifica se no índice passado tem um bot
     * 
     * @param vertex
     * @return true se houver um bot nessa posição, false caso contrário
     */
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
