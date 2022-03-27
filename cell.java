public class cell {
    
    float red;
    float green;
    float blue;

    boolean cellFilled;

    public cell() {
        red = 32;
        green = 32;
        blue = 32;
        cellFilled = false;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public void setColor(float r, float g, float b) {
        red = r;
        green = g;
        blue = b;
    }

    public void setColor(float t) {
        red = t;
        green = t;
        blue = t;
    }

    public boolean getCellFilled() {
        return cellFilled;
    }

    public void setCellFilled(boolean b) {
        cellFilled = b;
    }
}