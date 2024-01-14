package api.algorithms;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Flag;
import api.game.Position;

import java.util.Iterator;

public class ShortestPathAlgorithm<T> implements MovementAlgorithm<T> {

    private NetworkADT<T> graph;
    private Flag enemyFlag;

    public ShortestPathAlgorithm(WeightedGraph<T> graph, Flag enemyFlag) {
        this.graph = graph;
        this.enemyFlag = enemyFlag;
    }
    @Override
    public Position calculateNextMove(Position currentPosition) {
        // Obtenha a posição atual do bot no grafo
        int startIndex = graph.getIndex(currentPosition);

        // Encontre o índice do vértice de destino (a bandeira inimiga)
        int targetIndex = graph.getIndex(convertPositionToVertex(enemyFlag.getPosition()));

        // Calcule o caminho mais curto entre a posição atual e a bandeira inimiga
        Iterator<T> shortestPathIterator = graph.iteratorShortestPath(startIndex, targetIndex);

        // Se houver um caminho mais curto, retorne a próxima posição no caminho
        if (shortestPathIterator.hasNext()) {
            T nextVertex = shortestPathIterator.next();
            // Aqui você precisa converter o próximo vértice para a posição desejada
            Position nextPosition = convertVertexToPosition(nextVertex);
            return nextPosition;
        }

        // Se não houver um caminho mais curto, retorne a posição atual (sem movimento)
        return currentPosition;
    }

    private Position convertVertexToPosition(T vertex) {
        // Se os vértices do grafo são instâncias de Position, então basta fazer o cast
        if (vertex instanceof Position) {
            return (Position) vertex;
        } else {
            // Lógica de conversão se os vértices não forem instâncias de Position
            // Substitua por sua própria lógica
            return new Position(0, 0);
        }
    }

    private T convertPositionToVertex(Position position) {
        // Se a posição já é do tipo T (o tipo genérico do grafo), basta retorná-la
        if (graph.getVertices()[0] instanceof Position) {
            return (T) position;
        } else {
            // Lógica de conversão se a posição não for do tipo T
            // Substitua por sua própria lógica
            return null;
        }
    }

}
