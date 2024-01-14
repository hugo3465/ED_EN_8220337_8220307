package api.algorithms;


import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Position;

public class EvenNumberPathAlgorithm<T> implements MovementAlgorithm<T> {
    @Override
    public Position calculateNextMove(Position currentPosition) {
        Position nextMove;
        // Verifique se as coordenadas atuais são pares
        if (isEven(currentPosition.getX()) && isEven(currentPosition.getY())) {
            nextMove = findNearestEvenPosition(currentPosition);
            return nextMove;
        } else {
            // Se as coordenadas não são pares, mova-se para a posição mais próxima com coordenadas pares
            nextMove = findNearestEvenPosition(currentPosition);
            return nextMove;
        }
    }

    // Método auxiliar para verificar se um número é par
    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    // Método auxiliar para encontrar a posição mais próxima com coordenadas pares
    private Position findNearestEvenPosition(Position currentPosition) {
        // Implemente lógica para encontrar a posição mais próxima com coordenadas pares
        // Este é apenas um exemplo, você pode substituir por sua própria lógica
        int newX = (currentPosition.getX() % 2 == 0) ? currentPosition.getX() : currentPosition.getX() + 1;
        int newY = (currentPosition.getY() % 2 == 0) ? currentPosition.getY() : currentPosition.getY() + 1;

        return new Position(newX, newY);
    }
}
