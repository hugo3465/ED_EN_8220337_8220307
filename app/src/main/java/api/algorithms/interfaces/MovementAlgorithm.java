package api.algorithms.interfaces;

import api.game.Bot;

public interface MovementAlgorithm {

    public int getNextMovement(int currentIndex, int endIndex, Bot currentBot);

    public void updateBotLocation(int currentIndex, int nextIndex, Bot bot);

    public boolean hasBot(int vertex);

}
