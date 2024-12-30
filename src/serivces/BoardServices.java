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
                if (gridX < 0 || gridX >= board.getWidth() ||    // Left and right walls
                        gridY < 0 || gridY >= board.getHeight()) {   // Top and bottom
                    return false;
                }
                if (gridY >= 0 &&  boardGrid[gridX][gridY] != 0) {
                    return false;
                }
            }

        }

        return true;
    }
    public void movePieceDown () {
    }

    public void movePieceRight() {
    }

    public void movePieceLeft() {

    }
}
