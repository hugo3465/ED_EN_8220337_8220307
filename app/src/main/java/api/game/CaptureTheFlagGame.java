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

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o bot alcançou a bandeira inimiga.
     * Tópico 8 - O jogo termina quando um dos bots chega ao campo do adversário
     *
     * @param bot O bot cuja posição será verificada em relação à bandeira inimiga.
     * @return true se o bot alcançou a bandeira inimiga, false caso contrário.
     */
    public boolean checkEndGame(Bot bot) {
        return (player1.checkEndGame(bot) || player2.checkEndGame(bot));
    }

    // Implemente métodos para iniciar o jogo, avançar rodadas, etc.
}
