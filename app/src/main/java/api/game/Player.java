package api.game;

import api.DataStructures.ArrayList.ListADT;

public class Player {
    private ListADT<Bot> bots; // maybe uma queue
    private Flag flag;

    public Player(ListADT<Bot> bots, Flag flag) {
        this.bots = bots;
        this.flag = flag;
    }

    // Implemente métodos para configurar bots, atribuir algoritmos, etc.
}
