package api.algorithms;

import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;
import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedListADT;
import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Position;

import java.util.Iterator;

public class EvenNumberPathAlgorithm<T> implements MovementAlgorithm<T> {
    @Override
    public Position calculateNextMove(Position currentPosition) {
        return null;
    }
//    private NetworkADT<T> graph;
//    private UnorderedListADT<T> evenNumberPath;
//
//    public EvenNumberPathAlgorithm(NetworkADT<T> graph, UnorderedListADT<T> evenNumberPath) {
//        this.graph = graph;
//        this.evenNumberPath = evenNumberPath;
//    }
//
//    public UnorderedListADT<T> findEvenNumberPath(T startVertex) {
//        evenNumberPath.removeAll();
//        boolean[] visited = new boolean[graph.size()];
//        int startIndex = graph.getIndex(startVertex);
//        dfs(startIndex, visited);
//        return evenNumberPath;
//    }
//
//    private void dfs(int vertex, boolean[] visited) {
//        visited[vertex] = true;
//        evenNumberPath.addToRear(graph.getVertex(vertex));
//
//        Iterator<T> neighbors = graph.iteratorDFS(vertex);
//        while (neighbors.hasNext()) {
//            T neighbor = neighbors.next();
//            int neighborIndex = graph.getIndex(neighbor);
//
//            if (!visited[neighborIndex] && isEven(neighborIndex)) {
//                dfs(neighborIndex, visited);
//            }
//        }
//    }
//
//    private boolean isEven(int number) {
//        return number % 2 == 0;
//    }
}
