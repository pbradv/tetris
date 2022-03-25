import processing.core.PApplet;
import processing.core.PGraphics;

public class Board extends PApplet{
    final int numberOfRows = 10;
    final int numberOfColumns = 22;

    cell[][] board;

    tris controlledBlock;

    int heldPieceType = -1;
    boolean canSwap;

    public Board() {
        board = new cell[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                board[row][col] = new cell();
            }
        }
        canSwap = true;
    }

    public void drawBoard(float x, float y, PGraphics g) {
        cell currentCell;
        float initialY = y;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                currentCell = board[row][col];                
                g.fill(currentCell.red, currentCell.green, currentCell.blue);
                g.rect(x, y, 20, 20);
                y += 20;
            }
            y = initialY;
            x += 20;
        }
    }

    public void drawHeldPiece(float x, float y, PGraphics g) {
        
    }

    public void addBlock(tris t) {
        controlledBlock = t;
        Coordinates[] allBlocks = controlledBlock.getAllBlocks();
        board[allBlocks[0].x][allBlocks[0].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[1].x][allBlocks[1].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[2].x][allBlocks[2].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[3].x][allBlocks[3].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
    }

    public boolean autoMove() {
        Coordinates[] temp = controlledBlock.getAllBlocks();
        if (verifyNewPosition(temp, 0, 1)) {
            moveBlock(0, 1);
            return false;
        }
        else {
            board[temp[0].x][temp[0].y].setBlockPlaced(true);
            board[temp[1].x][temp[1].y].setBlockPlaced(true);
            board[temp[2].x][temp[2].y].setBlockPlaced(true);
            board[temp[3].x][temp[3].y].setBlockPlaced(true);
            canSwap = true;
            for (int a = 0; a < temp.length; a++) {
                lineClear(temp[a].y);
            }
            return true;
        }
    }

    // the order of this should be changed
    // blocks should only be able to be placed if the user pressed space or hard drops
    // or when the game moves automatically moves the block down
    public void playerMove(int x, int y) {
        Coordinates[] allBlocks = controlledBlock.getAllBlocks();
        if (verifyNewPosition(allBlocks, x, y)) {
            moveBlock(x, y);
        }
    }

    public void moveBlock(int x, int y) {
        resetColor();
        controlledBlock.moveBlock(x, y);
        addBlock(controlledBlock);
    }

    // try to combine the lower class with this
    public boolean verifyNewPosition(Coordinates[] allBlocks, int x, int y) {
        // check within y bounds
        for (int i = 0; i < allBlocks.length; i++) {
            if (allBlocks[i].y + y >= numberOfColumns || allBlocks[i].y + y < 0) {
                return false;
            }
        }
        // check within x bounds
        for (int i = 0; i < allBlocks.length; i++) {
            if (allBlocks[i].x + x < 0 || allBlocks[i].x + x >= numberOfRows)  {
                return false;
            }
        }
        // check blocks aren't placed
        for (int i = 0; i < allBlocks.length; i++) {
            if (board[allBlocks[i].x + x][allBlocks[i].y + y].getBlockPlaced()) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyNewPosition(Coordinates[] allBlocks) {
        // check within y bounds
        for (int i = 0; i < allBlocks.length; i++) {
            if (allBlocks[i].y >= numberOfColumns || allBlocks[i].y < 0) {
                return false;
            }
        }
        // check within x bounds
        for (int i = 0; i < allBlocks.length; i++) {
            if (allBlocks[i].x < 0 || allBlocks[i].x >= numberOfRows)  {
                return false;
            }
        }
        // check blocks aren't placed
        for (int i = 0; i < allBlocks.length; i++) {
            if (board[allBlocks[i].x][allBlocks[i].y].getBlockPlaced()) {
                return false;
            }
        }
        return true;
    }

    public void resetColor() {
        Coordinates allBlocks[] = controlledBlock.getAllBlocks();
        board[allBlocks[0].x][allBlocks[0].y].setColor(32);
        board[allBlocks[1].x][allBlocks[1].y].setColor(32);
        board[allBlocks[2].x][allBlocks[2].y].setColor(32);
        board[allBlocks[3].x][allBlocks[3].y].setColor(32);
    }

    private void lineClear(int col) {
        boolean b = true;
        for (int row = 0; row < numberOfRows; row++) {
            if (!board[row][col].getBlockPlaced()) {
                b = false;
            }
        }
        if (b) {
            for ( ; col > 0; col--) {
                for (int row = 0; row < numberOfRows; row++) {
                    board[row][col] = board[row][col - 1];
                }
            }
            for (int row = 0; row < numberOfRows; row++) {
                board[row][0] = new cell();
            }
        }
    }

    public boolean checkLoss() {
        for (int row = 0; row < numberOfRows; row++) {
            if (board[row][2].getBlockPlaced()) {
                return true;
            }
        }
        return false;
    }

    public void rotatePiece(int n) {
        Coordinates temp[] = controlledBlock.rotatePiece(n);
        if (verifyNewPosition(temp)) {
            resetColor();
            controlledBlock.allBlocks = temp;
            addBlock(controlledBlock);
        }
    }

    public void hardDrop() {
        Coordinates allBlocks[] = controlledBlock.getAllBlocks();
        int col = Math.max(Math.max(allBlocks[0].y, allBlocks[1].y), Math.max(allBlocks[2].y, allBlocks[3].y));
        System.out.println(allBlocks[0].y + " " + allBlocks[1].y + " " + allBlocks[2].y + " " + allBlocks[3].y + " " + col);
        int max = numberOfColumns - col; 
        int newY = max - 1;     
        outer:
        for ( ; col < max; col++) {
            for (int i = 0; i < allBlocks.length; i++) {
                System.out.println(allBlocks[i].y + " " + col + " = " + (allBlocks[i].y + col));
                if (board[allBlocks[i].x][allBlocks[i].y + col].getBlockPlaced()) {
                    System.out.println(true);
                    newY = col - 1;
                    break outer;
                }
            }
        }
        System.out.println(newY);
        moveBlock(0, newY);
        board[allBlocks[0].x][allBlocks[0].y].setBlockPlaced(true);
        board[allBlocks[1].x][allBlocks[1].y].setBlockPlaced(true);
        board[allBlocks[2].x][allBlocks[2].y].setBlockPlaced(true);
        board[allBlocks[3].x][allBlocks[3].y].setBlockPlaced(true);
        canSwap = true;
        for (int a = 0; a < allBlocks.length; a++) {
            lineClear(allBlocks[a].y);
        }
    }

    public boolean holdPiece() {
        if (canSwap) {
            resetColor();
            canSwap = false;
            if (heldPieceType == -1) {
                heldPieceType = controlledBlock.getBlockType();
                return true;
            }
            else {
                int temp = heldPieceType;
                heldPieceType = controlledBlock.getBlockType();
                addBlock(new tris(temp));
            }
        }
        return false;
    }

    // get methods
    public int getRows() {
        return numberOfRows;
    }

    public int getColumns() {
        return numberOfColumns;
    }

    public cell[][] getBoard() {
        return board;
    }

    public cell getCell(int x, int y) {
        return board[x][y];
    }
}