package api.game.interfaces;

import api.game.Bot;
import api.game.Player;
import api.map.GameMap;

public interface ICaptureTheFlag {

    Bot playRound(Player player);

    Player nextTurn();

    int isGameOver();

    Player getCurrentPlayer();

    GameMap getGameMap();
}
