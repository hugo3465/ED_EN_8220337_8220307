package api.game;

import api.algorithms.BreadthFirstSearchAlgorithm;
import api.algorithms.DepthFirstSearchAlgorithm;
import api.algorithms.ShortestPathAlgorithm;
import api.algorithms.interfaces.MovementAlgorithm;

import java.util.Iterator;

/**
 * Representa um bot no jogo, caracterizado pelo seu índice no grafo e algoritmo de movimentação.
 */
public class Bot extends Entity{

    //Todo Colocar a Entity
    private String name;
    private MovementAlgorithm<Position> movementAlgorithm;
    private Flag enemyFlagPosition;
    

    public Bot(Position initialPosition) {
        super(initialPosition);
    }

    public Bot(Position initialPosition, String name) {
        super(initialPosition);
        this.name = name;
    }

    public MovementAlgorithm<Position> getMovementAlgorithm() {
        return movementAlgorithm;
    }

    public void setMovementAlgorithm(MovementAlgorithm<Position> algorithm) {
        this.movementAlgorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnemyFlagPosition(Flag enemyFlagPosition) {
        this.enemyFlagPosition = enemyFlagPosition;
    }

    /**
     * Verifica se o bot pode se mover para a nova posição especificada.
     * Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode movimentar para uma posição
     * em que esteja outro bot.
     *
     * @param newPosition A nova posição desejada.
     * @param otherBots   Array de outros bots no jogo.
     * @return true se o movimento for válido, false se houver colisão.
     */
    protected boolean canMoveTo(Position newPosition, Bot[] otherBots) {
        for (Bot currentBot : otherBots) {
            if (currentBot.getPosition().equals(newPosition)) {
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
            Position currentPosition = getPosition();

            // Verifica o tipo de algoritmo e chama o método apropriado
            if (movementAlgorithm instanceof BreadthFirstSearchAlgorithm) {
                moveWithBFS_DFS(currentPosition, otherBots);
            } else if (movementAlgorithm instanceof ShortestPathAlgorithm) {
                moveWithShortestPath(currentPosition, otherBots);
            } else if (movementAlgorithm instanceof DepthFirstSearchAlgorithm){
                moveWithBFS_DFS(currentPosition, otherBots);
            }else {
                // Lógica para outros tipos de algoritmos, se necessário
                System.out.println("Algoritmo não reconhecido. Movimento não realizado.");
            }
        }
    }

    private void moveWithShortestPath(Position currentPosition, Bot[] otherBots) {
        if (movementAlgorithm instanceof ShortestPathAlgorithm) {
            ShortestPathAlgorithm<Position> shortestPathAlgorithm = (ShortestPathAlgorithm<Position>) movementAlgorithm;

            Flag targetPosition = enemyFlagPosition; // Já é a posição, não o índice

            // Verifica se há um próximo vértice no caminho mais curto
            Iterator<Position> shortestPathIterator = shortestPathAlgorithm.iteratorShortestPath(currentPosition, targetPosition.getPosition());

            if (shortestPathIterator.hasNext()) {
                Position nextMove = shortestPathIterator.next();

                if (canMoveTo(nextMove, otherBots)) {
                    setPosition(nextMove);
                } else {
                    recalculateMove(otherBots, nextMove);
                }
            } else {
                System.out.println("Não há próximo vértice no caminho mais curto. Movimento não realizado.");
            }
        } else {
            System.out.println("Algoritmo não reconhecido como Shortest Path. Movimento não realizado.");
        }
    }

    private void moveWithBFS_DFS(Position currentPosition, Bot[] otherBots) {
        Iterator<Position> bfsIterator = movementAlgorithm.search(currentPosition);

        // Verifica se há um próximo vértice na busca em largura ou profundidade
        if (bfsIterator.hasNext()) {
            Position nextMove = bfsIterator.next();

            if (canMoveTo(nextMove, otherBots)) {
                setPosition(nextMove);
            } else {
                recalculateMove(otherBots, nextMove);
            }
        } else {
            System.out.println("Não há próximo vértice na busca em largura ou profundidade. Movimento não realizado.");
        }
    }

    /**
     * Recalcula o movimento usando o mesmo algoritmo, evitando uma posição específica.
     *
     * @param otherBots       Array de outros bots no jogo.
     * @param avoidPosition   Posição a ser evitada durante o recálculo.
     */
    protected void recalculateMove(Bot[] otherBots, Position avoidPosition) {
        if (movementAlgorithm != null) {
            Position currentPosition = getPosition();

            if (movementAlgorithm instanceof ShortestPathAlgorithm) {
                // Se for ShortestPathAlgorithm, use iteratorShortestPath
                ShortestPathAlgorithm<Position> shortestPathAlgorithm = (ShortestPathAlgorithm<Position>) movementAlgorithm;
                Iterator<Position> shortestPathIterator = shortestPathAlgorithm.iteratorShortestPath(currentPosition, enemyFlagPosition.getPosition());

                handleNextMove(shortestPathIterator, otherBots, avoidPosition);
            } else if (movementAlgorithm instanceof BreadthFirstSearchAlgorithm) {
                // Se for BreadthFirstSearchAlgorithm, use search
                BreadthFirstSearchAlgorithm<Position> bfsAlgorithm = (BreadthFirstSearchAlgorithm<Position>) movementAlgorithm;
                Iterator<Position> bfsIterator = bfsAlgorithm.search(currentPosition);

                handleNextMove(bfsIterator, otherBots, avoidPosition);
            } else if (movementAlgorithm instanceof DepthFirstSearchAlgorithm) {
                // Se for DepthFirstSearchAlgorithm, use search
                DepthFirstSearchAlgorithm<Position> dfsAlgorithm = (DepthFirstSearchAlgorithm<Position>) movementAlgorithm;
                Iterator<Position> dfsIterator = dfsAlgorithm.search(currentPosition);

                handleNextMove(dfsIterator, otherBots, avoidPosition);
            } else {
                System.out.println("Algoritmo não reconhecido. Movimento não realizado.");
            }
        }
    }

    /**
     * Lida com o próximo movimento com base no iterador, verificações de colisão e posição a ser evitada.
     *
     * @param iterator       Iterador para obter o próximo movimento.
     * @param otherBots      Array de outros bots no jogo.
     * @param avoidPosition  Posição a ser evitada durante o movimento.
     */
    private void handleNextMove(Iterator<Position> iterator, Bot[] otherBots, Position avoidPosition) {
        if (iterator.hasNext()) {
            Position nextMove = iterator.next();

            if (canMoveTo(nextMove, otherBots) && !nextMove.equals(avoidPosition)) {
                setPosition(nextMove);
            } else {
                System.out.println("Movimento inválido. Ignorando movimento.");
            }
        } else {
            System.out.println("Não há próximo vértice. Movimento não realizado.");
        }
    }

    @Override
    public int compareTo(Entity o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
