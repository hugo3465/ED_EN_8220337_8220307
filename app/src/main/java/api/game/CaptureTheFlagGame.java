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
        // Implemente a lógica para obter uma visualização do mapa (pode ser um método na classe GameMap)
        return map.getMap();  // Substitua por lógica real
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

    public void startGame() {
        // Posicionar a bandeira de cada jogador no mapa
        map.placeFlag(player1.getFlag());
        map.placeFlag(player2.getFlag());

        // Posicionar os jogadores e seus bots no mapa
        // positionPlayer(player1);
        // positionPlayer(player2);
    }

    // private void positionPlayer(Player player) {
    //     // Posicionar o jogador no mapa
    //     map.placePlayer(player);

    //     // Posicionar os bots do jogador no mapa usando assignInitialPosition
    //     for (Bot bot : player.getBots()) {
    //         player.assignInitialPosition(bot, player.getFlag());
    //         map.placeBot(bot);
    //     }
    // }


    public void nextTurn() {
        // Implemente a lógica para avançar para a próxima rodada, trocar de jogador, etc.
        currentPlayerTurn = (currentPlayerTurn + 1) % 2;  // Alternar entre jogador 1 e jogador 2
    }

}
