import processing.core.PApplet;

import java.util.ArrayList;

public class Tetris extends PApplet {
    
    Board board = new Board();    
    float boardX = 150;
    float boardY = 0;

    int startTime;
    int currentTime;
    int autoMove = 1000;
    int playerMove = 75;

    ArrayList<Integer> bag = new ArrayList<Integer>();
    ArrayList<Integer> nextBag = new ArrayList<Integer>();
    int n = 0;

    tris newBlock;
    boolean getNewBlock;

    int moveX = 0;
    int moveY = 0;

    boolean rightPressed;
    boolean leftPressed;
    boolean downPressed;
    boolean spacePressed;
    boolean shiftPressed;
    boolean zPressed;
    boolean xPressed;
    boolean cPressed;
    boolean rPressed;

    int rPressedTime;

    boolean gameOver = false;

    public void settings() {
        size(500, 600);
        startTime = millis();
        refillBag();
        getNewBlock = true;
        board.initializeBlock(new tris(n));
        getNewBlock = false;
    }

    public void draw() {
        background(50);

        // left display
        fill(0);
        textSize(20);
        currentTime = millis() - startTime;
        text("Time:", 0, 20);
        text((int)currentTime, 0, 40); 
        text((int)autoMove / 1000, 0, 60); 
        text("bag size: " + bag.size(), 0, 80);   
        text("Level: " + board.getLevel(), 0, 100);
        text("Score: " + board.getScore(), 0, 120); 


        // right display
        // Display key output
        text("RIGHT: " + rightPressed, 350, 20);
        text("LEFT: " + leftPressed, 350, 40);
        text("DOWN: " + downPressed, 350, 60);
        text("SPACE: " + spacePressed, 350, 80);
        text("SHIFT: " + shiftPressed, 350, 100);
        text("Z: " + zPressed, 350, 120);
        text("X: " + xPressed, 350, 140);
        text("C: " + cPressed, 350, 160);

        // Display new block
        text(n, 350, 180);
        text(board.controlledBlock.state, 350, 200);
        text("Placed: " + getNewBlock, 350, 220);

        // moveX and Y
        text("moveX: " + moveX, 350, 240);
        text("moveY: " + moveY, 350, 260);

        if (!gameOver) {
            if (getNewBlock) {
                n = randomBlock();
                newBlock = new tris(n);
                board.initializeBlock(newBlock);
                getNewBlock = false;
            }
    
            update();
            if (autoMove < currentTime) {
                autoMove += 1000;
                getNewBlock = board.autoMove();
            }
            else if (playerMove < currentTime) {
                playerMove += 75;
                board.playerMove(moveX, moveY);
            }
    
            if (board.checkLoss()) {
                gameOver = true;
            }
        }

        // Board draw
        board.drawBoard(boardX, boardY, g);
        
        if (gameOver) {
            fill(100);
            rect(170, 250, 160, 100);
            textSize(20);
            fill(200);
            println(true);
            text("Game Over", 170, 270);
        }

        moveX = 0;
        moveY = 0;

    }

    void update() {
        if (rightPressed) {
            // Move Right
            moveX += 1;
        }
        if (leftPressed) {
            // Move Left
            moveX += -1;
        }
        if (downPressed) {
            moveY = 1;
        }
    }

    int randomBlock() {
        if (bag.size() == 0) {
            bag = nextBag;
            refillBag();
        }
        return bag.remove(0);
    }

    private void refillBag() {
        for (int i = 0; i < 7; i++) {
            nextBag.add((int)(Math.random() * nextBag.size()), i);
        }
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            leftPressed = true;
        }
        if (keyCode == RIGHT) {
            rightPressed = true;
        }
        if (keyCode == DOWN) {
            downPressed = true;
        }
        if (key == ' ') {
            if (!spacePressed) {
                board.hardDrop();
                getNewBlock = true;
            }
            spacePressed = true;
        }
    
        if (keyCode == SHIFT) {
            getNewBlock = board.holdPiece();
            shiftPressed = true;
        }
        if (key == 'z' || key == 'Z') {
            if (!zPressed) {
                board.rotatePiece(3);
            }
            zPressed = true;
        }
        if (key == 'x' || key == 'X') {
            if (!xPressed) {
                board.rotatePiece(1);
            }
            xPressed = true;
        }
        if (key == 'c' || key == 'C') {
            if (!cPressed) {
                board.rotatePiece(2);
            }
            cPressed = true;
        }
        if (key == 'r' || key == 'R') {
            if (!rPressed) {
                rPressedTime = millis();
            }
            rPressed = true;
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            leftPressed = false;
        }
        if (keyCode == RIGHT) {
            rightPressed = false;
        }
        if (keyCode == DOWN) {
            downPressed = false;
        }
        if (key == ' ') {
            spacePressed = false;
        }
        if (keyCode == SHIFT) {
            shiftPressed = false;
        }
        if (key == 'z' || key == 'Z') {
            zPressed = false;
        }
        if (key == 'x' || key == 'X') {
            xPressed = false;
        }
        if (key == 'c' || key == 'C') {
            cPressed = false;
        }
        if (key == 'r' || key == 'R') {
            rPressed = false;
            rPressedTime = 0;
        }
    }

    public static void main(String[] args) {

        String[] appletArgs = new String[] {"Tetris"};
        Tetris tetris = new Tetris();
        PApplet.runSketch(appletArgs, tetris);
    }
}