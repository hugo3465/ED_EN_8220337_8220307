package api.algorithms;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;

import java.util.Iterator;

public class ShortestPathAlgorithm<T> implements MovementAlgorithm<T> {

    private NetworkADT<T> graph;

    public ShortestPathAlgorithm(WeightedGraph<T> graph) {
        this.graph = graph;
    }

    @Override
    public Iterator<T> search(T startVertex) {
        return null;
    }

    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return graph.shortestPathWeight(startVertex, targetVertex);
    }
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return graph.iteratorShortestPath(startVertex, targetVertex);
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.SHORTEST_PATH;
    }
}
