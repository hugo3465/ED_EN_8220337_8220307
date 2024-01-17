package api.algorithms;

import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.game.interfaces.GameEntity;
import api.map.GameMap;

public class ShortestPathAlgorithm implements MovementAlgorithm<GameEntity> {

    private GameMap graph;

    public ShortestPathAlgorithm(GameMap graph) {
        this.graph = graph;
    }

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private UnorderedArrayList<Integer> dijkstra(double[][] adjacencyMatrix, int startVertex, int endVertex) {
        final int NO_PARENT = -1;

        int numVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] conterá a distância mais curta de startVertex para i
        double[] shortestDistances = new double[numVertices];

        // added[i] será verdadeiro se o vértice i estiver
        // incluído no caminho mais curto / na árvore de caminho mais curto
        // ou se a distância mais curta de startVertex para
        // i estiver finalizada
        boolean[] added = new boolean[numVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
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

        // Construir o caminho de endVertex para startVertex, ele contém os índices dos
        // vértices, não o conteúdo do vértice.
        UnorderedArrayList<Integer> path = new UnorderedArrayList<>();
        int currentVertexIndex = endVertex;
        while (currentVertexIndex != NO_PARENT) {
            path.addToFront(currentVertexIndex);
            currentVertexIndex = parents[currentVertexIndex];
        }

        return path;
    }

    @Override
    public int getNextMovement(int currentIndex, int endIndex) {
        UnorderedArrayList<Integer> indexList = new UnorderedArrayList<>();
        indexList = dijkstra(graph.getAdjacencyMatrix(), currentIndex, endIndex);

        for (Integer i : indexList) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        return indexList.removeFirst();
    }

    @Override
    public boolean hasBot(int vertex) {
        // se o vértice for diferente de null, e o que estiver lá for um bot e não uma
        // flag, ent retorna True
        return (graph.getVertices()[vertex] != null && graph.getVertices()[vertex] instanceof Bot);
    }
}
