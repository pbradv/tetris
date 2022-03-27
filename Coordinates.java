public class Coordinates {
    
    int x;
    int y;

    public Coordinates() {
        x = 0;
        y = 0;
    }

    public Coordinates(int a, int b) {
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoords(int a, int b) {
        x = a;
        y = b;
    }

    public void incrementCoords(int xIncrement, int yIncrement) {
        x += xIncrement;
        y += yIncrement;
    }
}