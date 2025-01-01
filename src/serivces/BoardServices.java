package serivces;

import models.Board;
import models.Tetrimino;

public class BoardServices {
    public Board board;



    public boolean isValidMove(Tetrimino currentPiece, int deltaX, int deltaY) {
        int[][] shape = currentPiece.getShape();
        int[][] boardGrid = board.getGrid();  // Get the grid array

        int newX = currentPiece.getX() + deltaX;
        int newY = currentPiece.getY() + deltaY;

        for(int row = 0;row < shape.length;row++){
            for(int col = 0; col < shape[row].length; col++){
                int gridX = newX + col;
                int gridY = newY + row;
                // for walls
                if (gridX < 0 || gridX >= board.getWidth() ||
                        gridY < 0 || gridY >= board.getHeight()) {
                    return false;
                }
                // for collision
                if (gridY >= 0 &&  boardGrid[gridX][gridY] != 0) {
                    return false;
                }
            }

        }

        return true;
    }
    public void movePieceDown () {
        Tetrimino currentPiece = board.getCurrentPiece();
        if(currentPiece == null){
            return;
        }
        if (isValidMove(currentPiece, 0, 1)) {
            currentPiece.setY(board.getCurrentPiece().getY() + 1);

        }else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void movePieceRight() {
        Tetrimino currentPiece = board.getCurrentPiece();
        if(currentPiece == null){
            return;
        }
        if (isValidMove(currentPiece, 1, 0)) {
            currentPiece.setY(board.getCurrentPiece().getX() + 1);

        }else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void movePieceLeft() {
        Tetrimino currentPiece = board.getCurrentPiece();
        if(currentPiece == null){
            return;
        }
        if (isValidMove(currentPiece, -1, 0)) {
            currentPiece.setY(board.getCurrentPiece().getX() - 1);

        }else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void lockPiece() {
        Tetrimino currentPiece = board.getCurrentPiece();
        int[][] boardGrid = board.getGrid();
        int[][] shape = currentPiece.getShape();

        for (int row =0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = currentPiece.getX() + col;
                    int gridY = currentPiece.getY() + row;
                    boardGrid[gridX][gridY] = shape[row][col];
                }
            }
        }
        board.setGrid(boardGrid);
    }

    public int clearLines() {
        //ToDo()
        return 0;
    }

    public void spawnNewPiece() {
        //ToDo()
    }
}
