package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Position;
import api.map.GameMap;
import api.DataStructures.ArrayList.UnorderedArrayList.UnorderedArrayList;

import java.util.Iterator;

public class RandomMovementAlgorithm implements MovementAlgorithm<Position> {

    private GameMap map;

    public RandomMovementAlgorithm(GameMap map) {
        this.map = map;
    }

    @Override
    public Iterator<Position> search(Position startVertex) {
        UnorderedArrayList<Position> availableMoves = getAvailableMoves(startVertex);

        // Embaralha a lista de movimentos disponíveis para obter um movimento aleatório
        shuffleList(availableMoves);

        return availableMoves.iterator();
    }

    private UnorderedArrayList<Position> getAvailableMoves(Position currentPosition) {
        UnorderedArrayList<Position> availableMoves = new UnorderedArrayList<>();

        // Adicione lógica para obter posições vizinhas válidas no mapa
        // Certifique-se de verificar limites do mapa, obstáculos, etc.

        // Exemplo simplificado: Adiciona posições vizinhas (acima, abaixo, esquerda, direita)
        availableMoves.addToRear(new Position(currentPosition.getIndex() - 1));
        availableMoves.addToRear(new Position(currentPosition.getIndex() + 1));

        return availableMoves;
    }

    private void shuffleList(UnorderedArrayList<Position> list) {
        int n = list.size();

        for (int i = n - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));

            // Troca os elementos
            Position temp = list.removeLast();
            list.addToRear(list.removeIndex(j));
            list.addToRear(temp);
        }
    }

    @Override
    public double shortestPathWeight(Position startVertex, Position targetVertex) {
        // Este algoritmo não calcula o caminho mais curto, apenas retorna um movimento aleatório
        return 0;
    }

    @Override
    public Iterator<Position> iteratorShortestPath(Position startVertex, Position targetVertex) {
        // Este algoritmo não calcula o caminho mais curto, apenas retorna um movimento aleatório
        return search(startVertex);
    }

    @Override
    public AlgorithmType getAlgorithmType() {
        return AlgorithmType.RANDOM;
    }
}
