package api.game;

import api.algorithms.AlgorithmType;
import api.algorithms.BreadthFirstSearchAlgorithm;
import api.algorithms.DepthFirstSearchAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.interfaces.GameEntity;

import java.util.Iterator;

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
     * Verifica se o bot pode se mover para a nova posição especificada.
     * Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode
     * movimentar para uma posição
     * em que esteja outro bot.
     *
     * @param newPosition A nova posição desejada.
     * @param bots   Array de todos os bots do jogo.
     * @return true se não houver colisão no movimento, false se houver colisão.
     */
    protected boolean canMoveTo(Position newPosition, Bot[] bots) {
        for (Bot bot : bots) {
            if (bot.position.equals(newPosition) && newPosition != bot.position) {
                // Houve colisão com outro bot
                return false;
            }
        }

        // Movimento válido, sem colisão
        return true;
    }

    /**
     * Move o bot usando o algoritmo atribuído, evitando colisões com outros bots.
     *
     * @param bots Array de todos os bots no jogo.
     */
    public void move(Bot[] bots) {
        if (movementAlgorithm != null) {
            Position currentPosition = getPosition();

            System.out.println(movementAlgorithm.getNextMovement(currentPosition, enemyFlag.getPosition()));

        }
    }

    // private void moveWithShortestPath(Position currentPosition, Bot[] otherBots) {
    //     if (movementAlgorithm instanceof ShortestPathAlgorithm) {
    //         ShortestPathAlgorithm<GameEntity> shortestPathAlgorithm = (ShortestPathAlgorithm<GameEntity>) movementAlgorithm;

    //         Flag targetPosition = enemyFlag; // Já é a posição, não o índice

    //         // Verifica se há um próximo vértice no caminho mais curto
    //         Iterator<GameEntity> shortestPathIterator = shortestPathAlgorithm.iteratorShortestPath(currentPosition,
    //                 targetPosition.getPosition());

    //         if (shortestPathIterator.hasNext()) {
    //             Position nextMove = shortestPathIterator.next().getPosition();

    //             if (canMoveTo(nextMove, otherBots)) {
    //                 setPosition(nextMove);
    //             } else {
    //                 recalculateMove(otherBots, nextMove);
    //             }
    //         } else {
    //             setPosition(currentPosition);
    //         }
    //     } else {
    //         System.out.println("Algoritmo não reconhecido como Shortest Path. Movimento não realizado.");
    //     }
    // }

    // private void moveWithBFS_DFS(Position currentPosition, Bot[] otherBots) {
    //     Iterator<Position> bfsIterator = movementAlgorithm.search(currentPosition);

    //     // Verifica se há um próximo vértice na busca em largura ou profundidade
    //     if (bfsIterator.hasNext()) {
    //         Position nextMove = bfsIterator.next();

    //         if (canMoveTo(nextMove, otherBots)) {
    //             setPosition(nextMove);
    //         } else {
    //             recalculateMove(otherBots, nextMove);
    //         }
    //     } else {
    //         System.out.println("Não há próximo vértice na busca em largura ou profundidade. Movimento não realizado.");
    //     }
    // }

    /**
     * Recalcula o movimento usando o mesmo algoritmo, evitando uma posição
     * específica.
     *
     * @param otherBots     Array de outros bots no jogo.
     * @param avoidPosition Posição a ser evitada durante o recálculo.
     */
    // protected void recalculateMove(Bot[] otherBots, Position avoidPosition) {
    //     if (movementAlgorithm != null) {
    //         Position currentPosition = getPosition();

    //         if (movementAlgorithm instanceof ShortestPathAlgorithm) {
    //             // Se for ShortestPathAlgorithm, use iteratorShortestPath
    //             ShortestPathAlgorithm<Position> shortestPathAlgorithm = (ShortestPathAlgorithm<Position>) movementAlgorithm;
    //             Iterator<Position> shortestPathIterator = shortestPathAlgorithm.iteratorShortestPath(currentPosition,
    //                     enemyFlag.getPosition());

    //             handleNextMove(shortestPathIterator, otherBots, avoidPosition);
    //         } else if (movementAlgorithm instanceof BreadthFirstSearchAlgorithm) {
    //             // Se for BreadthFirstSearchAlgorithm, use search
    //             BreadthFirstSearchAlgorithm<Position> bfsAlgorithm = (BreadthFirstSearchAlgorithm<Position>) movementAlgorithm;
    //             Iterator<Position> bfsIterator = bfsAlgorithm.search(currentPosition);

    //             handleNextMove(bfsIterator, otherBots, avoidPosition);
    //         } else if (movementAlgorithm instanceof DepthFirstSearchAlgorithm) {
    //             // Se for DepthFirstSearchAlgorithm, use search
    //             DepthFirstSearchAlgorithm<Position> dfsAlgorithm = (DepthFirstSearchAlgorithm<Position>) movementAlgorithm;
    //             Iterator<Position> dfsIterator = dfsAlgorithm.search(currentPosition);

    //             handleNextMove(dfsIterator, otherBots, avoidPosition);
    //         } else {
    //             System.out.println("Algoritmo não reconhecido. Movimento não realizado.");
    //         }
    //     }
    // }

    /**
     * Lida com o próximo movimento com base no iterador, verificações de colisão e
     * posição a ser evitada.
     *
     * @param iterator      Iterador para obter o próximo movimento.
     * @param otherBots     Array de outros bots no jogo.
     * @param avoidPosition Posição a ser evitada durante o movimento.
     */
    private void handleNextMove(Iterator<Position> iterator, Bot[] otherBots, Position avoidPosition) {
        if (iterator.hasNext()) {
            Position nextMove = iterator.next();

            if (canMoveTo(nextMove, otherBots) && !nextMove.equals(avoidPosition)) {
                setPosition(nextMove);
            } else {
                setPosition(getPosition());
            }
        } else {
            System.out.println("Não há próximo vértice. Movimento não realizado.");
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
