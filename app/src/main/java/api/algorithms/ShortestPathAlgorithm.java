package api.algorithms;

import api.DataStructures.Graph.NetworkADT;
import api.DataStructures.Graph.WeightedGraph;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Position;
import api.game.interfaces.GameEntity;
import api.map.GameMap;

import java.util.Iterator;

public class ShortestPathAlgorithm<T> implements MovementAlgorithm<T> {

    private GameMap graph;

    public ShortestPathAlgorithm(GameMap graph) {
        this.graph = graph;
    }

    public int getNextMovement(T currentPosition, T target) {

        // // Obtém um iterador para o caminho mais curto do vértice atual ao vértice de destino
        // Iterator<T> shortestPathIterator = graph.iteratorShortestPath(currentPosition, target);

        // // Se houver um próximo vértice no caminho mais curto, mova-se para ele
        // if (shortestPathIterator.hasNext()) {
        //     T nextVertex = shortestPathIterator.next();

        //     // TODO: Converta nextVertex para Position, se necessário
        //     // Por exemplo, se T for Position, você pode usar Position nextPosition = (Position) nextVertex;

        //     // Aqui você pode calcular a direção ou movimento necessário
        //     // com base na posição atual e próxima, e retornar a direção desejada.
        //     // Por exemplo, se T for Position e você deseja saber a direção em termos de linhas e colunas:
        //     // int rowDifference = nextPosition.getRow() - currentPosition.getRow();
        //     // int colDifference = nextPosition.getCol() - currentPosition.getCol();
        //     // A partir dessas diferenças, você pode determinar a direção do movimento.

        //     // Substitua a linha abaixo com a lógica real para obter a próxima direção/movimento
        //     return 0;
        // } else {
        //     // Não há próximo vértice no caminho mais curto, então nenhum movimento
        //     return 0;
        // }

        Iterator<T> path = graph.shortestPathWeight(currentPosition, target);
    }

    @Override
    public Iterator<T> search(T startVertex) {
        return null;
    }
}
