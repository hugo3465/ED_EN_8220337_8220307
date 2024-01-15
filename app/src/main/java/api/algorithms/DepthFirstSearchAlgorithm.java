package api.algorithms;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;

import java.util.Iterator;

public class DepthFirstSearchAlgorithm<T> implements MovementAlgorithm<T> {
    private NetworkADT<T> graph;

    public DepthFirstSearchAlgorithm(WeightedGraph<T> graph) {
        this.graph = graph;
    }

    @Override
    public Iterator<T> search(T startVertex) {
        return graph.iteratorDFS(startVertex);
    }

    // Não vai ser usado nesta classe
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return 0;
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }
}
