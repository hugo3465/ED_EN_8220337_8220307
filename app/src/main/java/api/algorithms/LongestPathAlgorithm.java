package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.dataStructures.Stack.LinkedStack.LinkedStack;
import api.dataStructures.Stack.LinkedStack.StackADT;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;

/**
 * Algoritmo do caminho mais longo
 */
public class LongestPathAlgorithm implements MovementAlgorithm {

    /** Mapa de jogo onde o algoritmo opera. */
    private GameMap map;

    /**
     * Stack para armazenar o caminho calculado. Guarda os índices para onde o bot tem de se deslocar
     * Como o algoritmo retorna o camunho mais curto de y a x e não de x a y, uma
     * stack é a melhor maneira de armazenar o caminho
     */
    private StackADT<Integer> calculatedPath;

    /**
     * Construtor que recebe o mapa do jogo
     * 
     * @param map mapa do jogo
     */
    public LongestPathAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedStack<>();
    }

    /**
     * Método privado para realizar uma busca em profundidade (DFS) para encontrar o
     * caminho mais longo.
     * 
     * @param startVertex vértice onde começa
     * @param endVertex   vértice onnde quer chegar
     * @return true se conseguiu encontrar um caminho, false se não conseguiu
     */
    private boolean longestPathDFS(int startVertex, int endVertex) {
        boolean[] visited = new boolean[map.getVertices().length];
        return longestPathDFSUtil(startVertex, endVertex, visited);
    }

    /**
     * Função auxiliar da {@code longestPathDFS} recursiva para a busca em
     * profundidade (DFS).
     * 
     * @param currentVertex vertice atual
     * @param endVertex vertice para onde quer ir
     * @param visited verties já visitados
     * @return true se conseguiu encontrar um caminho, false se não conseguiu
     */
    private boolean longestPathDFSUtil(int currentVertex, int endVertex, boolean[] visited) {
        visited[currentVertex] = true;

        // Se atingiu o vértice de destino, retorna verdadeiro para parar a recursão.
        if (currentVertex == endVertex) {
            return true;
        }

        boolean pathFound = false;

        // Itera sobre os vizinhos do vértice atual.
        for (int neighbor = 0; neighbor < map.getVertices().length; neighbor++) {
            // Verifica se o vizinho não foi visitado, não contém um bot e há uma aresta
            // entre eles.
            if (!visited[neighbor] && !hasBot(neighbor) && map.getAdjacencyMatrix()[currentVertex][neighbor] > 0) {
                // Se encontrar um caminho, adiciona o vértice atual à fila do caminho
                // calculado.
                if (longestPathDFSUtil(neighbor, endVertex, visited)) {
                    calculatedPath.push(neighbor);
                    pathFound = true;
                }
            }
        }

        return pathFound;
    }

    /**
     * vai calcular um caminho segundo o algoritmo associado, caso já não tenha
     * calculado, e retorna o próximo índice que o bot tem de ir
     * 
     * @param currentIndex vertice atual do bot
     * @param endIndex vertice para onde se quer ir
     * @param currentBot bot que se vai mover
     * @return próximo índice para onde o bot tem de ir, caso não consiga ir para
     *         lado nenhum retorna o índice onde está
     */
    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {
        // Se a stack do caminho calculado estiver vazia, calcula o caminho mais longo.
        if (calculatedPath.isEmpty()) {
            longestPathDFS(currentIndex, endIndex);
        }

        int nextIndex = currentIndex;
        while (!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.pop();

            // Verifica se o vértice removido contém um bot
            if (hasBot(dequeuedIndex)) {
                System.out.println("bot " + currentBot.getName() + " tentou ir para o índice " + dequeuedIndex
                        + " mas tem lá um bot, então vai ter de se recalcular o caminho");

                // Recalcula o caminho se o vértice retirado contiver um bot
                if (!longestPathDFS(currentIndex, endIndex)) {
                    // se não conseguiu calcular o caminho vai devolver o vertice atual
                    // break;
                    return currentIndex;
                }
                continue;
            } else {
                // Se não contiver um bot, vai atualizar a posição do bot no vetor e retornar
                // para onde ele foi
                nextIndex = dequeuedIndex;
                updateBotLocation(currentIndex, nextIndex, currentBot);
                return nextIndex;
            }
        }

        return currentIndex;
    }

    /**
     * Atualiza a posição do bot no mapa. Atualizar no mapa significa atualizar no
     * vetoor de vértices da super class
     * 
     * @param currentIndex vertice atual do bot
     * @param nextIndex vertice para onde o bot vai
     * @param bot bot que se vai mover
     */
    @Override
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot) {
        // Se o índice atual for diferente do próximo índice, atualiza a posição do bot
        // no mapa.
        if (currentIndex != nextIndex) {

            if (map.getVertices()[currentIndex] instanceof Flag) {
                // se ele estiver em cima da bandeira, não coloca o vértice anterior a null para
                // não apagar a bandeira.
                map.setVertice(nextIndex, bot);
            } else {
                map.setVertice(currentIndex, null);
                map.setVertice(nextIndex, bot);
            }
        }
    }

    /**
     * Verifica se no índice passado tem um bot
     * 
     * @param vertex vertice onde se vai verificaar a existência de um bot
     * @return true se houver um bot nessa posição, false caso contrário
     */
    @Override
    public boolean hasBot(int vertex) {
        return (map.getVertices()[vertex] != null && map.getVertices()[vertex] instanceof Bot);
    }
}