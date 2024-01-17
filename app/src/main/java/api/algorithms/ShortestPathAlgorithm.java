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

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        double[] shortestDistances = new double[numVertices];

        // added[i] will true if vertex i is
        // included / in the shortest path tree
        // or the shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[numVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Double.POSITIVE_INFINITY;
            added[vertexIndex] = false;
        }

        // Distance of the source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0.0;

        // Parent array to store the shortest
        // path tree
        int[] parents = new int[numVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find the shortest path for all
        // vertices
        for (int i = 1; i < numVertices; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // the first iteration.
            int nearestVertex = -1;
            double shortestDistance = Double.POSITIVE_INFINITY;
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance && !hasBot(vertexIndex)) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as processed
            added[nearestVertex] = true;

            // Update the distance value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                double edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (!hasBot(vertexIndex) && edgeDistance > 0 && (shortestDistance + edgeDistance) < shortestDistances[vertexIndex]) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        // Build the path from endVertex to startVertex, it caontains the indices of the vertices, not the vertice content
        UnorderedArrayList<Integer> path = new UnorderedArrayList<>();
        int currentVertexIndex = endVertex;
        while (currentVertexIndex != NO_PARENT) {
            //path.addToFront(graph.getVertice(currentVertex));
            path.addToFront(currentVertexIndex);
            // acho que se for addToRear não preciso fazer o reverse
            currentVertexIndex = parents[currentVertexIndex];
        }

        return path;
    }

    @Override
    public int getNextMovement(int currentIndex, int endIndex) {
        UnorderedArrayList<Integer> indexList = new UnorderedArrayList<>();
        indexList = dijkstra(graph.getAdjacencyMatrix(), currentIndex, endIndex);

        for(Integer i : indexList) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        return indexList.removeFirst();
    }

    @Override
    public boolean hasBot(int vertex) {
        return (graph.getVertices()[vertex] != null && graph.getVertices()[vertex] instanceof Bot);
    }
}
