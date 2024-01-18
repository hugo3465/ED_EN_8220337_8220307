package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;
import api.map.GameMap;

public class RandomMovementAlgorithm implements MovementAlgorithm<GameEntity> {

    private GameMap map;

    public RandomMovementAlgorithm(GameMap map) {
        this.map = map;
    }

    // public Iterator<Position> search(Position startVertex) {
    //     UnorderedArrayList<Position> availableMoves = getAvailableMoves(startVertex);

    //     // Embaralha a lista de movimentos disponíveis para obter um movimento aleatório
    //     shuffleList(availableMoves);

    //     return availableMoves.iterator();
    // }

    // private UnorderedArrayList<Position> getAvailableMoves(Position currentPosition) {
    //     UnorderedArrayList<Position> availableMoves = new UnorderedArrayList<>();

    //     // Adicione lógica para obter posições vizinhas válidas no mapa
    //     // Certifique-se de verificar limites do mapa, obstáculos, etc.

    //     // Exemplo simplificado: Adiciona posições vizinhas (acima, abaixo, esquerda, direita)
    //     availableMoves.addToRear(new Position(currentPosition.getIndex() - 1));
    //     availableMoves.addToRear(new Position(currentPosition.getIndex() + 1));

    //     return availableMoves;
    // }

    // private void shuffleList(UnorderedArrayList<Position> list) {
    //     int n = list.size();

    //     for (int i = n - 1; i > 0; i--) {
    //         int j = (int) (Math.random() * (i + 1));

    //         // Troca os elementos
    //         Position temp = list.removeLast();
    //         list.addToRear(list.removeIndex(j));
    //         list.addToRear(temp);
    //     }
    // }

    @Override
    public int getNextMovement(int currentIndex, int endIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextMovement'");
    }

    @Override
    public boolean hasBot(int vertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasBot'");
    }
}
