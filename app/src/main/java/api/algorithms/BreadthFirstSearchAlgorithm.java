package api.algorithms;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;

import java.util.Iterator;

public class BreadthFirstSearchAlgorithm<T> implements MovementAlgorithm<T> {

    private NetworkADT<T> graph;

    public BreadthFirstSearchAlgorithm(WeightedGraph<T> graph) {
        this.graph = graph;
    }

    @Override
    public Iterator<T> search(T startVertex) {
        return graph.iteratorBFS(startVertex);
    }

    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return 0;
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.BFS;
    }

}
