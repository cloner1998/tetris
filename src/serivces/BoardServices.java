package serivces;

import inputs.Shapes;
import models.Board;
import models.Tetrimino;

import java.util.Arrays;
import java.util.Random;

public class BoardServices {
    public Board board;


    public boolean isValidMove(Tetrimino currentPiece, int deltaX, int deltaY) {
        int[][] shape = currentPiece.getShape();
        int[][] boardGrid = board.getGrid();  // Get the grid array

        int newX = currentPiece.getX() + deltaX;
        int newY = currentPiece.getY() + deltaY;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                int gridX = newX + col;
                int gridY = newY + row;
                // for walls
                if (gridX < 0 || gridX >= board.getWidth() ||
                        gridY < 0 || gridY >= board.getHeight()) {
                    return false;
                }
                // for collision
                if (gridY >= 0 && boardGrid[gridX][gridY] != 0) {
                    return false;
                }
            }

        }

        return true;
    }

    public void movePieceDown() {
        Tetrimino currentPiece = board.getCurrentPiece();
        if (currentPiece == null) {
            return;
        }
        if (isValidMove(currentPiece, 0, 1)) {
            currentPiece.setY(board.getCurrentPiece().getY() + 1);

        } else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void movePieceRight() {
        Tetrimino currentPiece = board.getCurrentPiece();
        if (currentPiece == null) {
            return;
        }
        if (isValidMove(currentPiece, 1, 0)) {
            currentPiece.setY(board.getCurrentPiece().getX() + 1);

        } else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void movePieceLeft() {
        Tetrimino currentPiece = board.getCurrentPiece();
        if (currentPiece == null) {
            return;
        }
        if (isValidMove(currentPiece, -1, 0)) {
            currentPiece.setY(board.getCurrentPiece().getX() - 1);

        } else {
            lockPiece();
            spawnNewPiece();
            clearLines();
        }
    }

    public void lockPiece() {
        Tetrimino currentPiece = board.getCurrentPiece();
        int[][] boardGrid = board.getGrid();
        int[][] shape = currentPiece.getShape();

        for (int row = 0; row < shape.length; row++) {
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
        int lines = 0;
        int[][] boardGrid = board.getGrid();
        int rowLength = boardGrid.length;
        int colLength = boardGrid[0].length;
        for (int row = rowLength-1; row >= 0; row--) {
            boolean isFull = true;
            for (int col = 0; col < colLength; col++) {
                if (boardGrid[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }

            if (isFull) {
                boardGrid[row][colLength-1] = 0;
                for (int moveRow = row; moveRow >0; moveRow--) {
                    System.arraycopy(boardGrid[moveRow - 1], 0, boardGrid[moveRow], 0, colLength);
                }
                Arrays.fill(boardGrid[0], 0);
                row ++;
                lines++;

            }

        }

        board.setGrid(boardGrid);
        return lines;
    }

    public void spawnNewPiece() {
        Random random = new Random();
        Shapes shapes = new Shapes();
        int[][][] allShapes = {
                shapes.IShape[0],
                shapes.squareShape[0],
                shapes.TShape[0],
                shapes.LShape[0],
                shapes.JShape[0],
                shapes.SShape[0],
                shapes.ZShape[0]
        };

        int pieceIndex = random.nextInt(allShapes.length);
        int[][] shape = allShapes[pieceIndex];

        Tetrimino newPiece = new Tetrimino(
                shape,
                ((board.getWidth() - shape[0].length) / 2),
                0,
                0 // does not implement color yet
        );

        if(!isValidMove(newPiece, 0, 0)) {
            board.setGameOver(true);
        }else {
            board.setCurrentPiece(newPiece);
        }
    }

}
