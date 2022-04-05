import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.Collections;

public class Board extends PApplet{
    final int numberOfRows = 10;
    final int numberOfColumns = 23;

    cell[][] board;
    cell[][] heldPiece;

    tris controlledBlock;

    Coordinates[] guideBlock;
    float guideBlockColor;

    int heldPieceType = -1;
    boolean canSwap;
    boolean blockPlaced;

    int score = 0;
    int level = 1;
    int totalLinesCleared = 0;

    public Board() {
        board = new cell[numberOfRows][numberOfColumns];
        heldPiece = new cell[4][4];

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                board[row][col] = new cell();
            }
        }
        
        for (int row = 0; row < heldPiece.length; row++) {
            for (int col = 0; col < heldPiece[row].length; col++) {
                heldPiece[row][col] = new cell();
            }
        }

        canSwap = true;
        blockPlaced = false;

        guideBlock = new Coordinates[4];
        guideBlockColor = 60;
        
    }

    public void drawBoard(float x, float y, PGraphics g) {
        cell currentCell;
        float initialY = y;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                currentCell = board[row][col];   
                if (col > 2 || currentCell.red != 32) {
                    g.fill(currentCell.red, currentCell.green, currentCell.blue);
                    g.rect(x, y, 20, 20);
                }             
                y += 20;
            }
            y = initialY;
            x += 20;
        }
    }

    public void initializeBlock(tris t) {
        controlledBlock = t;
        addBlock(controlledBlock);
    }

    public void addBlock(tris t) {
        addGuideBlock();
        Coordinates[] allBlocks = t.getAllBlocks();
        board[allBlocks[0].x][allBlocks[0].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[1].x][allBlocks[1].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[2].x][allBlocks[2].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
        board[allBlocks[3].x][allBlocks[3].y].setColor(t.getRed(), t.getGreen(), t.getBlue());
    }

    public boolean autoMove() {
        Coordinates[] temp = controlledBlock.getAllBlocks();
        if (verifyNewPosition(temp, 0, 1)) {
            moveBlock(controlledBlock, 0, 1);
            return false;
        }
        else {
            placeBlock();
            return true;
        }
    }

    public void playerMove(int x, int y) {
        println(String.format("playerMove(%d, %d)", x, y));
        Coordinates[] allBlocks = controlledBlock.getAllBlocks();
        if (verifyNewPosition(allBlocks, x, 0)) {
            moveBlock(controlledBlock, x, 0);
        }
        if (verifyNewPosition(allBlocks, 0, y)) {
            moveBlock(controlledBlock, 0, y);
            score += y;
        }
    }

    public void moveBlock(tris t, int x, int y) {
        resetColor(t);
        t.moveBlock(x, y);
        addBlock(t);
    }

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
            if (board[allBlocks[i].x + x][allBlocks[i].y + y].getCellFilled()) {
                return false;
            }
        }
        return true;
    }

    public void resetColor(tris t) {
        Coordinates allBlocks[] = t.getAllBlocks();
        board[allBlocks[0].x][allBlocks[0].y].setColor(32);
        board[allBlocks[1].x][allBlocks[1].y].setColor(32);
        board[allBlocks[2].x][allBlocks[2].y].setColor(32);
        board[allBlocks[3].x][allBlocks[3].y].setColor(32);
    }

    private void placeBlock() {
        blockPlaced = true;
        Coordinates allBlocks[] = controlledBlock.getAllBlocks();
        canSwap = true;
        for (int i = 0; i < allBlocks.length; i++) {
            board[allBlocks[i].x][allBlocks[i].y].setCellFilled(true);
        }
        lineClear();
    }

    private void lineClear() {
        Coordinates[] allBlocks = controlledBlock.getAllBlocks();
        ArrayList<Integer> columns = new ArrayList<Integer>();
        int nextY;
        int col;

        for (int index = 0; index < allBlocks.length; index++) {
            nextY = allBlocks[index].y;
            if (!columns.contains(nextY)) {
                columns.add(nextY);
            }
        }

        Collections.sort(columns);

        System.out.println(columns);

        for (int index = 0; index < columns.size(); index++) {
            col = columns.get(index);
            for (int row = 0; row < numberOfRows; row++) {
                if (!board[row][col].getCellFilled()) {
                    columns.remove(index);
                    index--;
                    break;
                }
            }
        }

        if (columns.size() == 1) {
            score += 100;
        }
        else if (columns.size() == 2) {
            score += 300;
        }
        else if (columns.size() == 3) {
            score += 500;
        }
        else if (columns.size() == 4) {
            score += 800;
        }

        totalLinesCleared += columns.size();
        if (level * 10 < totalLinesCleared) {
            level++;
        }
        
        for (int index = 0; index < columns.size(); index++) {
            for (col = columns.get(index); col > 0; col--) {
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
            if (board[row][2].getCellFilled()) {
                return true;
            }
        }
        return false;
    }

    // possibly add more newPosition coords, expand to 2
    public void rotatePiece(int n) {

        Coordinates temp[] = controlledBlock.rotatePiece(n);
        int[][] newPosition = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {-1, 1}, {1, 1}, {0, -1}, {1, -1}, {-1, -1}};

        for (int index = 0; index < newPosition.length; index++) {
            if (verifyNewPosition(temp, newPosition[index][0], newPosition[index][1])) {
                resetColor(controlledBlock);
                controlledBlock.setAllBlocks(temp, newPosition[index][0], newPosition[index][1]);
                controlledBlock.setState(controlledBlock.calcNewState(n));
                addBlock(controlledBlock);
                break;
            }
        }
    }

    // bugs:
    // one time i have gotten the block to float 1 cell above where it should have landed
    // idk why it did this and i cannot reproduce it
    public void hardDrop() {
        Coordinates allBlocks[] = controlledBlock.getAllBlocks();
        
        int i = 0;
        while (verifyNewPosition(allBlocks, 0, i)) {
            i++;
        }
        moveBlock(controlledBlock, 0, i - 1);

        score += 2 * (i - 1);

        placeBlock();
    }

    public void addGuideBlock() {
        System.out.println("blockplaced " + blockPlaced);
        if (!blockPlaced && guideBlock[0] != null) {
            for (int index = 0; index < guideBlock.length; index++) {
                board[guideBlock[index].x][guideBlock[index].y].setColor(32);
            }
        }

        Coordinates[] allBlocks = controlledBlock.getAllBlocks();
        for (int index = 0; index < guideBlock.length; index++) {
            guideBlock[index] = new Coordinates(allBlocks[index].x, allBlocks[index].y);
        }
        
        int i = 0;
        while (verifyNewPosition(guideBlock, 0, i)) {
            i++;
        }

        for (int index = 0; index < guideBlock.length; index++) {
            guideBlock[index].y = guideBlock[index].y + i - 1;
            board[guideBlock[index].x][guideBlock[index].y].setColor(guideBlockColor);
        }

        blockPlaced = false;

    }

    public boolean holdPiece() {
        if (canSwap) {
            resetColor(controlledBlock);
            canSwap = false;
            if (heldPieceType == -1) {
                heldPieceType = controlledBlock.getBlockType();
                return true;
            }
            else {
                int temp = heldPieceType;
                heldPieceType = controlledBlock.getBlockType();
                initializeBlock(new tris(temp));
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

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int totalLinesCleared() {
        return totalLinesCleared;
    }
}