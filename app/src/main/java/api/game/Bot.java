package api.game;

import api.algorithms.interfaces.BotAlgorithm;

/**
 * Representa um bot no jogo, caracterizado por suas coordenadas e algoritmo de movimentação.
 */
public class Bot {

    private int x; // Coordenada X do bot
    private int y; // Coordenada Y do bot
    private BotAlgorithm algorithm; // Algoritmo de movimentação do bot

    /**
     * Construtor da classe Bot.
     *
     * @param x A coordenada X inicial do bot.
     * @param y A coordenada Y inicial do bot.
     */
    public Bot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construtor da classe Bot.
     *
     * @param x A coordenada X inicial do bot.
     * @param y A coordenada Y inicial do bot.
     * @param algorithm O algoritmo de movimentação inicial do bot.
     */
    public Bot(int x, int y, BotAlgorithm algorithm) {
        this.x = x;
        this.y = y;
        this.algorithm = algorithm;
    }

    /**
     * Obtém a coordenada X atual do bot.
     *
     * @return A coordenada X do bot.
     */
    public int getX() {
        return x;
    }

    /**
     * Define a coordenada X do bot.
     *
     * @param x A nova coordenada X do bot.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtém a coordenada Y atual do bot.
     *
     * @return A coordenada Y do bot.
     */
    public int getY() {
        return y;
    }

    /**
     * Define a coordenada Y do bot.
     *
     * @param y A nova coordenada Y do bot.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtém o algoritmo de movimentação atual do bot.
     *
     * @return O algoritmo de movimentação do bot.
     */
    public BotAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Define o algoritmo de movimentação do bot.
     *
     * @param algorithm O novo algoritmo de movimentação do bot.
     */
    public void setAlgorithm(BotAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Retorna uma representação em formato de string do bot, incluindo suas coordenadas e algoritmo.
     *
     * @return Uma string representando o bot no formato "Bot em (X, Y) com algoritmo: [algoritmo]".
     */
    @Override
    public String toString() {
        return "Bot em (" + x + ", " + y + ") com algoritmo: " + algorithm;
    }

    /**
     * Verifica se o bot pode se mover para a nova posição especificada.
     * Tópico 7 - Excetuando na localização das bandeiras, um bot não se pode movimentar para uma posição
     * em que esteja outro bot
     *
     * @param newX A nova coordenada X desejada.
     * @param newY A nova coordenada Y desejada.
     * @param otherBots Array de outros bots no jogo.
     * @return true se o movimento for válido, false se houver colisão.
     */
    public boolean canMoveTo(int newX, int newY, /*List<Bot>*/Bot[] otherBots) {
        for (Bot bot : otherBots) {
            if (bot.getX() == newX && bot.getY() == newY) {
                // Colisão com outro bot
                return false;
            }
        }
        // Movimento válido, sem colisão
        return true;
    }

}
