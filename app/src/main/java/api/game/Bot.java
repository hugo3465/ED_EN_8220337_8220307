package api.game;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;

/**
 * Representa um bot no jogo, caracterizado pelo seu índice no grafo e algoritmo
 * de movimentação.
 */
public class Bot implements GameEntity {

    // Todo Colocar a Entity
    private String name;
    private Position position;
    private MovementAlgorithm<GameEntity> movementAlgorithm;
    private Flag enemyFlag;

    public Bot(String name, MovementAlgorithm<GameEntity> movementAlgorithm, Flag enemyFlag) {
        this.name = name;
        this.movementAlgorithm = movementAlgorithm;
        this.enemyFlag = enemyFlag;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     */
    public void move() {
        if (movementAlgorithm != null) {
            Position currentPosition = getPosition();

            int newLocation = movementAlgorithm.getNextMovement(currentPosition.getIndex(),
                    enemyFlag.getPosition().getIndex());


            this.position.setIndex(newLocation);

            System.out.println("Onde Estou agora: " + getPosition().getIndex());

        }
    }

    public MovementAlgorithm<GameEntity> getMovementAlgorithm() {
        return movementAlgorithm;
    }

    public void setMovementAlgorithm(MovementAlgorithm<GameEntity> algorithm) {
        this.movementAlgorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public void setEnemyFlag(Flag enemyFlag) {
        this.enemyFlag = enemyFlag;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

}
