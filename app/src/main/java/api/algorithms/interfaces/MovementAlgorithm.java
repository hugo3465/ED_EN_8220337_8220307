package api.algorithms.interfaces;

import api.game.Bot;

public interface MovementAlgorithm {

    /**
     * vai calcular um caminho segundo o algoritmo associado, caso já não tenha
     * calculado, e retorna o próximo índice que o bot tem de ir
     * 
     * @param currentIndex
     * @param endIndex
     * @param currentBot
     * @return próximo índice para onde o bot tem de ir, caso não consiga ir para
     *         lado nenhum retorna o índice onde está
     */
    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot);

    /**
     * Atualiza a posição do bot no mapa. Atualizar no mapa significa atualizar no
     * vetoor de vértices da super class
     * 
     * @param currentIndex
     * @param nextIndex
     * @param bot
     */
    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot);

    /**
     * Verifica se no índice passado tem um bot
     * 
     * @param vertex
     * @return true se houver um bot nessa posição, false caso contrário
     */
    public boolean hasBot(int vertex);

}
