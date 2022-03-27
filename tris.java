public class tris {
    // color
    float red;
    float green;
    float blue;

    // bounds
    Coordinates allBlocks[];

    int state; // 4 states: 0(up), 1(left), 2(right), 3(down)
    int blockType; // 7 block types

    // constructors
    
    // create a new block 

    public tris(int b) {
        blockType = b;
        state = 0;
        getBlockColor();
        allBlocks = new Coordinates[4];
        allBlocks[0] = new Coordinates(4, 2);
        allBlocks = rotatePiece(state);
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

    public void setColor(float color) {
        red = color;
        green = color;
        blue = color;
    }

    public int getState() {
        return state;
    }

    public void setState(int s) {
        state = s;
    }

    public Coordinates[] getAllBlocks() {
        return allBlocks;
    }

    public void setAllBlocks(Coordinates[] temp, int x, int y) {
        allBlocks[0] = new Coordinates(temp[0].x + x, temp[0].y + y);
        allBlocks[1] = new Coordinates(temp[1].x + x, temp[1].y + y);
        allBlocks[2] = new Coordinates(temp[2].x + x, temp[2].y + y);
        allBlocks[3] = new Coordinates(temp[3].x + x, temp[3].y + y);
    }

    public int getBlockType() {
        return blockType;
    }

    public void moveBlock(int moveX, int moveY) {
        allBlocks[0] = new Coordinates(allBlocks[0].x + moveX, allBlocks[0].y + moveY);
        allBlocks[1] = new Coordinates(allBlocks[1].x + moveX, allBlocks[1].y + moveY);
        allBlocks[2] = new Coordinates(allBlocks[2].x + moveX, allBlocks[2].y + moveY);
        allBlocks[3] = new Coordinates(allBlocks[3].x + moveX, allBlocks[3].y + moveY);
    }

    public void getBlockColor() {
        // T block
        if (blockType == 0) {
            setColor(255, 0, 255);
        }
        // J
        else if (blockType == 1) {
            // blue
            setColor(0, 0, 255);
        }
        // L
        else if (blockType == 2) {
            // orange
            setColor(255, 125, 0);
        }
        // S
        else if (blockType == 3) {
            // green
            setColor(0, 255, 0);
        }
        // Z
        else if (blockType == 4) {
            // red
            setColor(255, 0, 0);
        }
        // O
        else if (blockType == 5) {
            // yellow
            setColor(255, 255, 0);
        }
        // I
        else if (blockType == 6) {
            // cyan
            setColor(0, 255, 255);
        }
    }

    public int calcNewState(int n) {
        return (state + n) % 4;
    }   
    
    public Coordinates[] rotatePiece(int n) {
        int newState = calcNewState(n);
        Coordinates[] temp = new Coordinates[4];
        // T block
        if (blockType == 0) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
            }
        }
        // J block
        else if (blockType == 1) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
        }
        // L
        else if (blockType == 2) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y - 1);
            }
        }
        // S
        else if (blockType == 3) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
        }
        // Z
        else if (blockType == 4) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
        }
        // O
        else if (blockType == 5) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
        }
        // I
        else if (blockType == 6) {
            if (newState == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 2, allBlocks[0].y);
            }
            else if (newState == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 2);
            }
            else if (newState == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 2, allBlocks[0].y);
            }
            else if (newState == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 2);
            }
        }
        return temp;
    }
}