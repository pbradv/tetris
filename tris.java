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
        allBlocks = new Coordinates[4];
        blockType = b;
        state = 0;
        getBlockColor();
        allBlocks[0] = new Coordinates(4, 1);
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

    public Coordinates[] getAllBlocks() {
        return allBlocks;
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
            red = 255;
            green = 0;
            blue = 255;
        }
        // J
        else if (blockType == 1) {
            // blue
            red = 0;
            green = 0;
            blue = 255;
        }
        // L
        else if (blockType == 2) {
            // orange
            red = 255;
            green = 125;
            blue = 0;
        }
        // S
        else if (blockType == 3) {
            // green
            red = 0;
            green = 255;
            blue = 0;
        }
        // Z
        else if (blockType == 4) {
            // red
            red = 255;
            green = 0;
            blue = 0;
        }
        // O
        else if (blockType == 5) {
            // yellow
            red = 255;
            green = 255;
            blue = 0;        
        }
        // I
        else if (blockType == 6) {
            // cyan
            red = 0;
            green = 255;
            blue = 255;
        }
    }

    public int calcNewState(int n) {
        return (state + n) % 4;
    }   
    
    public Coordinates[] rotatePiece(int n) {
        state = calcNewState(n);
        Coordinates[] temp = new Coordinates[4];
        // T block
        if (blockType == 0) {
            // allBlocks[0] stay the same
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
            }
        }
        // J block
        else if (blockType == 1) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y - 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
        }
        // L
        else if (blockType == 2) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y - 1);
            }
        }
        // S
        else if (blockType == 3) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
        }
        // Z
        else if (blockType == 4) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y + 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y + 1);
            }
        }
        // O
        else if (blockType == 5) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y - 1);
            }
        }
        // I
        else if (blockType == 6) {
            if (state == 0) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x + 2, allBlocks[0].y);
            }
            else if (state == 1) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 2);
            }
            else if (state == 2) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x + 1, allBlocks[0].y);
                temp[2] = new Coordinates(allBlocks[0].x - 1, allBlocks[0].y);
                temp[3] = new Coordinates(allBlocks[0].x - 2, allBlocks[0].y);
            }
            else if (state == 3) {
                temp[0] = new Coordinates(allBlocks[0].x, allBlocks[0].y);
                temp[1] = new Coordinates(allBlocks[0].x, allBlocks[0].y + 1);
                temp[2] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 1);
                temp[3] = new Coordinates(allBlocks[0].x, allBlocks[0].y - 2);
            }
        }
        return temp;
    }
}