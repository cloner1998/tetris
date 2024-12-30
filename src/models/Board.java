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
}
