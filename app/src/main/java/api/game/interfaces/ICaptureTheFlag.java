package api.game.interfaces;

import api.game.Bot;
import api.game.Player;
import api.map.GameMap;

public interface ICaptureTheFlag {

    /**
     * Executa uma rodada do jogo para o jogador especificado.
     *
     * @param player O jogador que está jogando a rodada.
     * @return O bot que jogou na rodada.
     */
    Bot playRound(Player player);

    /**
     * Vai definir o próximo jogador a jogar.
     *
     * @return o jogador que vai jogar.
     */
    Player nextTurn();

    /**
     * Verifica se o jogo chegou ao fim e quem é o vencedor.
     *
     * @return -1 se o jogo não acabou, 0 em caso de empate, 1 se o jogador 1
     *         ganhou, 2 se o jogador 2 ganhou.
     */
    int isGameOver();

    /**
     * Obtém a vez atual do jogador.
     *
     * @return o jogador atual.
     */
    Player getCurrentPlayer();

    /**
     * Obtém o mapa do jogo.
     *
     * @return O mapa do jogo.
     */
    GameMap getGameMap();
}
