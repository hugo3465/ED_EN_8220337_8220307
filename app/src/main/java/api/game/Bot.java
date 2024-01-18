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
    private int position;
    private MovementAlgorithm<GameEntity> movementAlgorithm;
    private Flag enemyFlag;

    public Bot(String name, MovementAlgorithm<GameEntity> movementAlgorithm, Flag enemyFlag) {
        this.name = name;
        this.movementAlgorithm = movementAlgorithm;
        this.enemyFlag = enemyFlag;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     * @return devolve o indíce para onde foi atribuída a nova posição do bot
     */
    public int move() {
        if (movementAlgorithm != null) {

            int newLocation = movementAlgorithm.getNextMovement(position,
                    enemyFlag.getPosition());


            this.position = newLocation;

            System.out.println("Onde Estou agora: " + position);

            return position;

        }

        return -1;
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
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

}
