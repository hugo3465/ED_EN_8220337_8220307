package api.algorithms;

import api.DataStructures.Stack.LinkedStack.LinkedStack;
import api.DataStructures.Stack.LinkedStack.StackADT;
import api.algorithms.interfaces.MovementAlgorithm;
import api.game.Bot;
import api.map.GameMap;

public class LongestPathAlgorithm implements MovementAlgorithm {

    // A classe implementa um algoritmo para encontrar o caminho mais longo num mapa de jogo.

    private GameMap map; // O mapa de jogo onde o algoritmo opera.
    private StackADT<Integer> calculatedPath; // Pilha para armazenar o caminho calculado.

    // Construtor recebe o mapa de jogo.
    public LongestPathAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedStack<>();
    }

    // Método privado para realizar uma busca em profundidade (DFS) para encontrar o caminho mais longo.
    private boolean longestPathDFS(int startVertex, int endVertex) {
        boolean[] visited = new boolean[map.getVertices().length];
        return longestPathDFSUtil(startVertex, endVertex, visited);
    }

    // Função auxiliar recursiva para a busca em profundidade (DFS).
    private boolean longestPathDFSUtil(int currentVertex, int endVertex, boolean[] visited) {
        visited[currentVertex] = true;

        // Se atingiu o vértice de destino, retorna verdadeiro para parar a recursão.
        if (currentVertex == endVertex) {
            return true;
        }

        boolean pathFound = false;

        // Itera sobre os vizinhos do vértice atual.
        for (int neighbor = 0; neighbor < map.getVertices().length; neighbor++) {
            // Verifica se o vizinho não foi visitado, não contém um bot e há uma aresta entre eles.
            if (!visited[neighbor] && !hasBot(neighbor) && map.getAdjacencyMatrix()[currentVertex][neighbor] > 0) {
                // Se encontrar um caminho, adiciona o vértice atual à pilha do caminho calculado.
                if (longestPathDFSUtil(neighbor, endVertex, visited)) {
                    calculatedPath.push(neighbor);
                    pathFound = true;
                }
            }
        }

        return pathFound;
    }

    // Implementação do método da interface para obter o próximo movimento do bot.
    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {
        // Se a pilha do caminho calculado estiver vazia, calcula o caminho mais longo.
        if (calculatedPath.isEmpty()) {
            longestPathDFS(currentIndex, endIndex);
        }

        // Imprime o caminho calculado para fins de teste.
        while (!calculatedPath.isEmpty()) {
            System.out.print(calculatedPath.pop() + " ");
        }
        System.out.println("\n");

        // TODO: Implemente a lógica para retornar o próximo índice com base no caminho calculado.

        return currentIndex;
    }

    // Implementação do método da interface para atualizar a localização do bot no mapa.
    @Override
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot) {
        // Se o índice atual for diferente do próximo índice, atualiza a posição do bot no mapa.
        if (currentIndex != nextIndex) {
            // Se for o primeiro movimento do bot, não coloca o vértice anterior a null para não apagar a bandeira.
            if (bot.getTimesMoved() == 0) {
                map.setVertice(nextIndex, bot);
            } else {
                map.setVertice(currentIndex, null);
                map.setVertice(nextIndex, bot);
            }
        }
    }

    // Implementação do método da interface para verificar se há um bot num determinado vértice.
    @Override
    public boolean hasBot(int vertex) {
        return (map.getVertices()[vertex] != null && map.getVertices()[vertex] instanceof Bot);
    }
}
