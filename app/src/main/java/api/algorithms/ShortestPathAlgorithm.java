package api.algorithms;

import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.DataStructures.Exceptions.EmptyCollectionException;
import api.DataStructures.Stack.LinkedStack.LinkedStack;
import api.DataStructures.Stack.LinkedStack.StackADT;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.interfaces.GameEntity;
import api.map.GameMap;

public class ShortestPathAlgorithm implements MovementAlgorithm<GameEntity> {

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
    private void dijkstra(int startVertex, int endVertex) { // TODO secalhar remover a
                                                            // adjacencyMatrix
        double[][] adjacencyMatrix = map.getAdjacencyMatrix();
        final int NO_PARENT = -1;
        int numVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] conterá a distância mais curta de startVertex para i
        double[] shortestDistances = new double[numVertices];

        // added[i] será verdadeiro se o vértice i estiver
        // incluído no caminho mais curto / na árvore de caminho mais curto
        // ou se a distância mais curta de startVertex para
        // i estiver finalizada
        boolean[] added = new boolean[numVertices];

        // inicializa todas as distâncias com INFINITO e added[] como false
        for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Double.POSITIVE_INFINITY;
            added[vertexIndex] = false;
        }

        // A distância do vértice de origem para
        // ele mesmo é sempre 0
        shortestDistances[startVertex] = 0.0;

        // Array de pais para armazenar a árvore de caminho mais curto
        int[] parents = new int[numVertices];

        // O vértice de início não
        // possui um pai
        parents[startVertex] = NO_PARENT;

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

        // Construir o caminho de endVertex para startVertex ele contém os índices dos
        // vértices, não o conteúdo do vértice.
        // Como usa o addToFront ent o caminho é construído do startVertex para o
        // endVertex
        // UnorderedArrayList<Integer> path = new UnorderedArrayList<>(); // TODO ACHO
        // QUE ISTO DEVIA SER UMA
        // UNORDEREDLINKEDLIST ou uma LinkedStack
        int currentVertexIndex = endVertex;
        while (currentVertexIndex != NO_PARENT) {
            // path.addToFront(currentVertexIndex);
            this.calculatedPath.push(currentVertexIndex);
            currentVertexIndex = parents[currentVertexIndex];
        }

        // return path;
    }

    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {
        if (calculatedPath.isEmpty()) {
            // Se o caminho calculado estiver vazio, recalcule o caminho
            dijkstra(currentIndex, endIndex);
        }

        // TODO while para testes para saber se fez bem o caminho
        // while (!this.calculatedPath.isEmpty()) {
        //     System.out.print(calculatedPath.pop() + " ");
        // }
        // System.out.println("\n");

        int nextIndex = -1;
        if(!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.pop();

            // Verifica se o vértice removido contém um bot
            if (hasBot(dequeuedIndex)) {
                // Recalcula o caminho se o vértice removido contiver um bot
                dijkstra(dequeuedIndex, endIndex);
            } else {
                // Se não contiver um bot, vai atualizar a posição do bot no vetor e retornar
                // para onde ele foi
                nextIndex = dequeuedIndex;
                map.setVertice(currentIndex, null);
                map.setVertice(nextIndex, currentBot);
                return nextIndex;
            }
        }

        // Se não houver mais movimentos disponíveis, retorna o índice atual
        return currentIndex;
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
