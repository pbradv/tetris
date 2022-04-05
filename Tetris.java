import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;


public class Tetris extends PApplet {
    
    enum GameState {
        RUNNING, PAUSED, HELP, ENDED;
    }
    
    Board board = new Board();
    float boardX = 150;
    float boardY = 0;

    int startTime;
    int currentTime;
    int nextMoveTime;
    int currentLevel;

    // by default, force move every 1000 ms (1s)
    int moveIncrement = 1000;

    ArrayList<Integer> bag = new ArrayList<Integer>();

    int n = 0;

    tris newBlock;
    boolean getNewBlock;

    // move by n blocks (x or y) based on user keys
    int moveX = 0;
    int moveY = 0;

    int rPressedTime;

    GameState state = GameState.RUNNING;

    public void settings() {
        size(500, 600);

        startTime = millis();
        currentTime = startTime;
        nextMoveTime = currentTime;
        currentLevel = board.getLevel();

        refillBag();
        getNewBlock = true;
        board.initializeBlock(new tris(n));
        getNewBlock = false;
    }

    public void draw() {
        background(50);

        if (state == GameState.RUNNING) {
            currentTime = (int)millis() - startTime;
        }

        // Draw state to game background
        updateDebugDisplay();

        if (state == GameState.RUNNING) {
            if (getNewBlock) {
                n = randomBlock();
                newBlock = new tris(n);
                board.initializeBlock(newBlock);
                if (board.getLevel() > currentLevel) {
                    currentLevel = board.getLevel();
                    moveIncrement = moveIncrement - 100;
                    println("New Move Increment: " + moveIncrement);
                }
                nextMoveTime = currentTime + moveIncrement;
                getNewBlock = false;
            }
            else if (nextMoveTime < currentTime) {
                getNewBlock = board.autoMove();
                nextMoveTime = currentTime + moveIncrement;
            }

            /*
             * If the user bumped the X/Y via keyPressed(),
             *  force a non-timer-based move.
             */
            if (moveX != 0 || moveY != 0) {
                board.playerMove(moveX, moveY);
            }
    
            if (board.checkLoss()) {
                state = GameState.ENDED;
            }
        }

        // Board draw
        board.drawBoard(boardX, boardY, g);
        
        // help draw
        if (state == GameState.HELP) {
            displayHelp();
        }

        if (state == GameState.ENDED) {
            fill(100);
            rect(170, 250, 160, 100);
            textSize(20);
            fill(200);
            //println(true);
            text("Game Over", 170, 270);
        }

        moveX = 0;
        moveY = 0;
    }

    /**
     * Display Help Screen
     * Uses a offscreen buffer ("helpG") to avoid messing up graphics context for the board.
     */
    private void displayHelp() {

        String[] helpLines = {
            "←↑→ == Move",
            "zxc == Rotate",
            "p == Pause",
            "h/? == Help"
        };
        int textSize = 20;
        int rectWidth = 200;
        int rectHeight = textSize * (helpLines.length + 1);
        int rectPadding = 5;
        int textY = 25;
        PGraphics helpG = createGraphics(rectWidth + rectPadding, rectHeight + rectPadding);

        helpG.beginDraw();
        // dialog has gray bg, white stroke
        helpG.fill(64, 64, 64);
        helpG.stroke(255, 255, 255);
        helpG.rect(0, 0, rectWidth, rectHeight, 10);        
        helpG.textSize(textSize);
        // white text
        helpG.fill(255,255,255);
        for (String s: helpLines) {
            helpG.text(s, 15, textY);
            textY += textSize;
        }
        helpG.endDraw();
        image(helpG, width/2 - rectWidth/2, height/2 - rectHeight/2);
    }

    private void updateDebugDisplay() {
        // text is white
        fill(255,255,255);

        // left display
        textSize(20);
        text("Sys Time: " + millis(), 0, 20);
        text("User Time: " + (int)currentTime, 0, 40);
        text("Next Move: " + (int)(nextMoveTime - currentTime), 0, 60);
        text("Move Delay: " + (int)moveIncrement, 0, 80);
        text("Bag Size: " + bag.size(), 0, 100);    
        text("Lines Cleared: " + board.totalLinesCleared, 0, 120);
        text("Level: " + currentLevel, 0, 140);
        text("Score: " + board.getScore(), 0, 160); 
        
        // Display new block
        text(n, 350, 180);
        text(board.controlledBlock.state, 350, 200);
        text("Placed: " + getNewBlock, 350, 220);

        // moveX and Y
        text("moveX: " + moveX, 350, 240);
        text("moveY: " + moveY, 350, 260);

        noFill();
    }

    int randomBlock() {
        if (bag.size() == 0) {
            bag = refillBag();
        }
        return bag.remove(0);
    }

    // 
    private ArrayList<Integer> refillBag() {
        var newBag = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            newBag.add((int)(Math.random() * newBag.size()), i);
        }
        return newBag;
    }

    public void keyPressed() {
        println("keyPressed, ", keyCode);
        if (keyCode == LEFT) {
            moveX -= 1;
        }
        if (keyCode == RIGHT) {
            moveX += 1;
        }
        if (keyCode == DOWN) {
            moveY += 1;
        }
        if (key == ' ') {
            board.hardDrop();
            getNewBlock = true;
        }
    
        if (keyCode == SHIFT) {
            getNewBlock = board.holdPiece();
        }
        if (key == 'z' || key == 'Z' || keyCode == UP) {
            board.rotatePiece(3);
        }
        if (key == 'x' || key == 'X') {
            board.rotatePiece(1);
        }
        if (key == 'c' || key == 'C') {
            board.rotatePiece(2);
        }
        if (key == 'r' || key == 'R') {
            rPressedTime = millis();
        }
        if (key == 'p' || key == 'P') {
            if (state == GameState.PAUSED) {
                nextMoveTime = currentTime + moveIncrement;
                state = GameState.RUNNING;
            }
            else {
                state = GameState.PAUSED;
            }
        }
        if (key == '?' || key == 'h' || key == 'H') {
            if (state == GameState.HELP) {
                state = GameState.RUNNING;
            }
            else {
                state = GameState.HELP;
            }
        }
    }


    public static void main(String[] args) {

        String[] appletArgs = new String[] {"Tetris"};
        Tetris tetris = new Tetris();
        PApplet.runSketch(appletArgs, tetris);
    }
}
