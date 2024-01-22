package api.game;

import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;

/**
 * Representa um bot no jogo, caracterizado pelo seu índice no grafo e algoritmo
 * de movimentação.
 */
public class Bot implements GameEntity {

    private String name;
    private int index;
    private MovementAlgorithm movementAlgorithm;
    private Flag enemyFlag;
    private int timesMoved;

    public Bot(String name, MovementAlgorithm movementAlgorithm, Flag enemyFlag) {
        this.name = name;
        this.movementAlgorithm = movementAlgorithm;
        this.enemyFlag = enemyFlag;
        this.timesMoved = 0;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     * 
     * @return devolve o indíce para onde foi atribuída a nova posição do bot
     */
    public int move() {
        if (movementAlgorithm != null) {

            int newLocation = movementAlgorithm.getNextMovement(index,
                    enemyFlag.getIndex(), this);


            if (newLocation != index) {
                timesMoved++;
                this.index = newLocation;
            }

            return index;

        }

        return -1;
    }

    public MovementAlgorithm getMovementAlgorithm() {
        return movementAlgorithm;
    }

    public void setMovementAlgorithm(MovementAlgorithm algorithm) {
        this.movementAlgorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public void setEnemyFlag(Flag enemyFlag) {
        this.enemyFlag = enemyFlag;
    }


    public int getTimesMoved() {
        return timesMoved;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

}
