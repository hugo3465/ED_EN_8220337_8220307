package api.algorithms;

import api.algorithms.interfaces.MovementAlgorithm;
import api.dataStructures.Stack.LinkedStack.LinkedStack;
import api.dataStructures.Stack.LinkedStack.StackADT;
import api.game.Bot;
import api.game.Flag;
import api.map.GameMap;

/**
 * Algoritmo do caminho mais curto
 */
public class ShortestPathAlgorithm implements MovementAlgorithm {
    /** Mapa de jogo onde o algoritmo opera. */
    private GameMap map;

    /**
     * Stack para armazenar o caminho calculado. Guarda os índices para onde o bot
     * tem de se deslocar
     * Como o algoritmo retorna o camunho mais curto de y a x e não de x a y, uma
     * stack é a melhor maneira de armazenar o caminho.
     */
    private StackADT<Integer> calculatedPath;

    /**
     * Construtor que recebe o mapa do jogo
     * 
     * @param map mapa do jogo
     */
    public ShortestPathAlgorithm(GameMap map) {
        this.map = map;
        calculatedPath = new LinkedStack<>();
    }

    /**
     * Aplica o algoritmo de Dijkstra para encontrar o caminho mais curto a partir
     * do vértice de início
     * especificado até o vértice de destino fornecido no grafo representado pela
     * matriz de adjacência do {@code GameMap}.
     *
     * @param startIndex o índice do vértice de início.
     * @param endIndex   o índice do vértice de destino.
     * @return {@code true} se um caminho válido for encontrado, {@code false} caso
     *         contrário.
     */
    private boolean dijkstra(int startIndex, int endIndex) {
        double[][] adjacencyMatrix = map.getAdjacencyMatrix();
        final int NO_PARENT = -1;
        int numVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] conterá a distância mais curta de startIndex para i
        double[] shortestDistances = new double[numVertices];

        // added[i] será verdadeiro se o vértice i estiver
        // incluído no caminho mais curto / na árvore de caminho mais curto
        // ou se a distância mais curta de startIndex para
        // i estiver finalizada
        boolean[] added = new boolean[numVertices];

        // inicializa todas as distâncias com INFINITO e added[] como false
        for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Double.POSITIVE_INFINITY;
            added[vertexIndex] = false;
        }

        // A distância do vértice de origem para
        // ele mesmo é sempre 0
        shortestDistances[startIndex] = 0.0;

        // Array de pais para armazenar o de caminho mais curto
        int[] parents = new int[numVertices];

        // O vértice de início não
        // possui um pai
        parents[startIndex] = NO_PARENT;

        // Encontrar o caminho mais curto para todos
        // os vértices
        for (int i = 1; i < numVertices; i++) {

            // Escolher o vértice de distância mínima
            // do conjunto de vértices ainda não
            // processados. nearestVertex é
            // sempre igual a startNode na
            // primeira iteração.
            int nearestVertex = -1;
            double shortestDistance = Double.POSITIVE_INFINITY;
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance && !hasBot(vertexIndex)) {
                    // se o vértice ainda não foi adicionado, o caminho até ele for menor que o
                    // menor caminho armazenado e não estiver um bot lá, vai guardar esse vértice e
                    // a distância até ele
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // se o nearestVertex não saiar do -1, ent não conseguiu encontrar um caminho
            if (nearestVertex == -1) {
                return false;
            }

            // Marcar o vértice escolhido como processado
            added[nearestVertex] = true;

            // Atualizar o valor da distância dos
            // vértices adjacentes ao
            // vértice escolhido.
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                double edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                // se não tiver bot e edgeDistance > 0
                if (!hasBot(vertexIndex) && edgeDistance > 0
                        && (shortestDistance + edgeDistance) < shortestDistances[vertexIndex]) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
            // se 0 fosse um peso válido nos grafos, então edgeDistance < 0 dentro do if
            // tinha
            // de ser edgeDistance < Double.POSITIVE_INFINITY
        }

        // Construir o caminho de endIndex para startIndex ele contém os índices dos
        // vértices, não o conteúdo do vértice.
        // Como usa o addToFront ent o caminho é construído do startIndex para o
        // endIndex
        int currentVertexIndex = endIndex;
        while (currentVertexIndex != NO_PARENT) {
            this.calculatedPath.push(currentVertexIndex);
            currentVertexIndex = parents[currentVertexIndex];
        }

        // remover do caminho o index onde o bot se encontra
        this.calculatedPath.pop();

        return true;
    }

    /**
     * vai calcular um caminho segundo o algoritmo associado, caso já não tenha
     * calculado, e retorna o próximo índice que o bot tem de ir
     * 
     * @param currentIndex vertice atual do bot
     * @param endIndex     vertice para onde o bot tem de ir
     * @param currentBot   bot que se vai mover
     * @return próximo índice para onde o bot tem de ir, caso não consiga ir para
     *         lado nenhum retorna o índice onde está
     */
    @Override
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot) {
        if (calculatedPath.isEmpty()) {
            // Se o caminho calculado estiver vazio, calcula o caminho
            dijkstra(currentIndex, endIndex);
        }

        int nextIndex = currentIndex;
        while (!calculatedPath.isEmpty()) {
            int dequeuedIndex = calculatedPath.pop();

            // Verifica se o vértice removido contém um bot
            if (hasBot(dequeuedIndex)) {
                System.out.println("bot " + currentBot.getName() + " tentou ir para o índice " + dequeuedIndex
                        + " mas tem lá um bot, então vai ter de se recalcular o caminho");

                // Recalcula o caminho se o vértice retirado contiver um bot
                if (!dijkstra(currentIndex, endIndex)) {
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

        // Se não houver mais movimentos disponíveis, retorna o índice atual
        return currentIndex;
    }

    /**
     * Atualiza a posição do bot no mapa. Atualizar no mapa significa atualizar no
     * vetoor de vértices da super class
     * 
     * @param currentIndex vertice atual do bot
     * @param nextIndex    vertice para onde o bot vai
     * @param bot          bot que se vai mexer
     */
    @Override
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot) {

        if (currentIndex != nextIndex) {
            if (map.getVertices()[currentIndex] instanceof Flag) {
                // se ele estiver em cima da bandeira, não coloca o antigo vértice a null, para
                // não apagar a bandeira
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
     * @param vertex vertice onde se vai verificar a existencia de um bot
     * @return true se houver um bot nessa posição, false caso contrário
     */
    @Override
    public boolean hasBot(int vertex) {
        // Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode
        // movimentar para uma posição em que esteja outro bot.

        // se o vértice for diferente de null, e o que estiver lá for um bot e não uma
        // flag, ent retorna True
        return (map.getVertices()[vertex] != null && map.getVertices()[vertex] instanceof Bot);
    }
}
