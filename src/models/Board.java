package models;

public class Board {

    private int width;
    private int height;

    private int[][] grid;
    private Tetrimino currentPiece;
    private boolean isGameOver;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
        this.isGameOver = false;
        this.currentPiece = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid() {
        return grid;
    }

    public Tetrimino getCurrentPiece() {
        return currentPiece;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCurrentPiece(Tetrimino currentPiece) {
        this.currentPiece = currentPiece;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
