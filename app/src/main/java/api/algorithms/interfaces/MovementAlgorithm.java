package api.algorithms.interfaces;
import api.game.Position;

public interface MovementAlgorithm<T> {
    /**
     * Calcula o próximo movimento com base na posição atual.
     *
     * @param currentPosition A posição atual do bot.
     * @return A próxima posição calculada pelo algoritmo.
     */
    Position calculateNextMove(Position currentPosition);
}
