package api.game;

import api.algorithms.interfaces.MovementAlgorithm;

/**
 * Representa um bot no jogo, caracterizado por suas coordenadas e algoritmo de movimentação.
 */
public class Bot {

    private MovementAlgorithm movementAlgorithm;
    private Position currentPosition;
    private String name;

    public Bot(Position initialPosition) {
        this.currentPosition = initialPosition;
    }

    public void setMovementAlgorithm(MovementAlgorithm algorithm) {
        this.movementAlgorithm = algorithm;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Define a posição atual do bot.
     *
     * @param newPosition A nova posição do bot.
     */
    public void setCurrentPosition(Position newPosition) {
        this.currentPosition = newPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    /**
     * Verifica se o bot pode se mover para a nova posição especificada.
     * Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode movimentar para uma posição
     * em que esteja outro bot
     *
     * @param newX A nova coordenada X desejada.
     * @param newY A nova coordenada Y desejada.
     * @param bots Array de outros bots no jogo.
     * @return true se o movimento for válido, false se houver colisão.
     */
    private boolean canMoveTo(int newX, int newY, Bot[] bots) {
        for (Bot currentBot : bots) {
            if (currentBot.getCurrentPosition().getX() == newX && currentBot.getCurrentPosition().getY() == newY) {
                // Colisão com outro bot
                return false;
            }
        }
        // Movimento válido, sem colisão
        return true;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     *
     * @param otherBots Array de outros bots no jogo.
     */
    public void move(Bot[] otherBots) {
        if (movementAlgorithm != null) {
            // Calcula o próximo movimento usando o algoritmo atribuído
            Position nextMove = movementAlgorithm.calculateNextMove(currentPosition);

            // Verifica se o movimento é válido
            if (canMoveTo(nextMove.getX(), nextMove.getY(), otherBots)) {
                // Atualiza a posição atual
                currentPosition = nextMove;
            } else {
                System.out.println("Movimento inicial inválido. Tentando recalcular...");

                // Recalcula o movimento evitando a posição inicial inválida
                recalculateMove(otherBots, nextMove);
            }
        }
    }

    /**
     * Recalcula o movimento usando o mesmo algoritmo, evitando uma posição específica.
     *
     * @param otherBots       Array de outros bots no jogo.
     * @param invalidPosition Posição inválida a ser evitada.
     */
    private void recalculateMove(Bot[] otherBots, Position invalidPosition) {
        if (movementAlgorithm != null) {
            // Calcula uma nova posição usando o mesmo algoritmo
            Position recalculatedMove = movementAlgorithm.calculateNextMove(currentPosition);

            // Verifica se o novo movimento é válido, evitando a posição inválida
            if (canMoveTo(recalculatedMove.getX(), recalculatedMove.getY(), otherBots) &&
                    !recalculatedMove.equals(invalidPosition)) {
                // Atualiza a posição atual com o novo movimento
                currentPosition = recalculatedMove;
                System.out.println("Movimento recalculado com sucesso. Nova posição: " + recalculatedMove);
            } else {
                System.out.println("Movimento inválido mesmo após recálculo. Ignorando movimento.");
            }
        }
    }

    // Métodos auxiliares para obter as coordenadas X e Y da posição atual
    private int getX() {
        return currentPosition.getX();
    }

    private int getY() {
        return currentPosition.getY();
    }

}
