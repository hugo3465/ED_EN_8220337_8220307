package api.game;

import api.game.interfaces.ICaptureTheFlag;
import api.map.GameMap;

public class CaptureTheFlagGame implements ICaptureTheFlag {
    private GameMap map;
    private Player player1, player2;
    private int currentPlayerTurn;

    
    public CaptureTheFlagGame(GameMap map, Player player1, Player player2) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayerTurn = 0;
    }


    @Override
    public String getMapPreviw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMapPreviw'");
    }

    // Implemente métodos para iniciar o jogo, avançar rodadas, etc.
}
