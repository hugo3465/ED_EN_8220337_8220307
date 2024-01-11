package api.game;

public class Flag {
    private int x;
    private int y;

    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Métodos para obter e configurar as coordenadas da bandeira
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
