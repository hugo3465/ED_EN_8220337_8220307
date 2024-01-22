package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.dataStructures.Stack.LinkedStack.LinkedStack;
import api.dataStructures.Stack.LinkedStack.StackADT;
import api.game.Bot;
import api.map.GameMap;

public class ShortestPathAlgorithm implements MovementAlgorithm {

    private GameMap map;
    private StackADT<Integer> calculatedPath; // faz sentido ser Stack na maneira como o algoritmo foi feito, mas nnão
                                              // sei se o professor Óscar aprova

    public ShortestPathAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedStack<>();
    }

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private boolean dijkstra(int startIndex, int endIndex) {
        double[][] adjacencyMatrix = map.getAdjacencyMatrix();
        final int NO_PARENT = -1;
        int numVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] conterá a distância mais curta de startIndex para i
        double[] shortestDistances = new double[numVertices];

        // added[i] será verdadeiro se o vértice i estiver
        // incluído no caminho mais curto / na árvore de caminho mais curto
        // ou se a distância mais curta de startIndex para
        // i estiver finalizada
        boolean[] added = new boolean[numVertices];

        // inicializa todas as distâncias com INFINITO e added[] como false
        for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Double.POSITIVE_INFINITY;
            added[vertexIndex] = false;
        }

        // A distância do vértice de origem para
        // ele mesmo é sempre 0
        shortestDistances[startIndex] = 0.0;

        // Array de pais para armazenar a árvore de caminho mais curto
        int[] parents = new int[numVertices];

        // O vértice de início não
        // possui um pai
        parents[startIndex] = NO_PARENT;

        // Encontrar o caminho mais curto para todos
        // os vértices
        for (int i = 1; i < numVertices; i++) {

            // Escolher o vértice de distância mínima
            // do conjunto de vértices ainda não
            // processados. nearestVertex é
            // sempre igual a startNode na
            // primeira iteração.
            int nearestVertex = -1;
            double shortestDistance = Double.POSITIVE_INFINITY;
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance && !hasBot(vertexIndex)) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            if (nearestVertex == -1) {
                // se o nearestVertex não saiar do -1, ent não conseguiu encontrar um caminho
                return false;
            }

            // Marcar o vértice escolhido como processado
            added[nearestVertex] = true;

            // Atualizar o valor da distância dos
            // vértices adjacentes ao
            // vértice escolhido.
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                double edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (!hasBot(vertexIndex) && edgeDistance > 0
                        && (shortestDistance + edgeDistance) < shortestDistances[vertexIndex]) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        // Construir o caminho de endIndex para startIndex ele contém os índices dos
        // vértices, não o conteúdo do vértice.
        // Como usa o addToFront ent o caminho é construído do startIndex para o
        // endIndex
        int currentVertexIndex = endIndex;
        while (currentVertexIndex != NO_PARENT) {
            // path.addToFront(currentVertexIndex);
            this.calculatedPath.push(currentVertexIndex);
            currentVertexIndex = parents[currentVertexIndex];
        }

        // remover do caminho o index onde o bot se encontra
        this.calculatedPath.pop();

        return true;
    }

    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {
        if (calculatedPath.isEmpty()) {
            // Se o caminho calculado estiver vazio, calcula o caminho
            dijkstra(currentIndex, endIndex);
        }

        int nextIndex = currentIndex;
        while (!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.pop();

            // Verifica se o vértice removido contém um bot
            if (hasBot(dequeuedIndex)) {
                System.out.println("bot " + currentBot.getName() + " tentou ir para o índice " + dequeuedIndex
                        + " mas tem lá um bot, então vai ter de se recalcular o caminho");

                // Recalcula o caminho se o vértice retirado contiver um bot
                if (!dijkstra(currentIndex, endIndex)) {
                    // se não conseguiu calcular o caminho vai devolver o vertice atual
                    // break;
                    return currentIndex;
                }
                continue;
            } else {
                // Se não contiver um bot, vai atualizar a posição do bot no vetor e retornar
                // para onde ele foi
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
                map.setVertice(currentIndex, null);
                map.setVertice(nextIndex, bot);
            }
        }
    }

    @Override
    public boolean hasBot(int vertex) {
        // Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode
        // movimentar para uma posição em que esteja outro bot.

        // se o vértice for diferente de null, e o que estiver lá for um bot e não uma
        // flag, ent retorna True
        return (map.getVertices()[vertex] != null && map.getVertices()[vertex] instanceof Bot);
    }
}
