package controller;

import models.Board;
import models.GameState;
import movement.InputType;
import serivces.BoardServices;
import serivces.GameStateService;
import view.GameObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameController{
    private BoardServices boardServices;
    private GameStateService gameStateService;

    public Board board;
    public GameState gameState;
    public Timer gameTimer;

    public GameController() {
        this.board = new Board(10, 20);
        this.gameState = new GameState();
        initializeGameTimer();
    }

    public void handleInput(InputType input) {
        if (gameState.isPaused()) return;

        switch(input) {
            case LEFT:
                moveCurrentPieceLeft();
                break;
            case RIGHT:
                moveCurrentPieceRight();
                break;
            case DOWN:
                moveCurrentPieceDown();
                break;
            case ROTATE:
                rotateCurrentPiece();
                break;
            case DROP:
                hardDrop();
                break;
            case PAUSE:
                gameStateService.togglePause();
                break;
        }
    }

    private void rotateCurrentPiece() {
        // ToDo(yet we do not have rotation)
    }

    private void moveCurrentPieceDown() {
        if(boardServices.isValidMove(board.getCurrentPiece(), 0, 1)){
            boardServices.movePieceDown();
        }else{
            lockPieceAndSpawnNew();
        }
        notifyViewUpdate();
    }
    private void moveCurrentPieceRight() {
        if(boardServices.isValidMove(board.getCurrentPiece(), 1, 0)){
            boardServices.movePieceRight();
        }else{
            lockPieceAndSpawnNew();
        }
        notifyViewUpdate();
    }

    private void moveCurrentPieceLeft() {
        if(boardServices.isValidMove(board.getCurrentPiece(), -1, 0)){
            boardServices.movePieceLeft();
        }else{
            lockPieceAndSpawnNew();
        }
        notifyViewUpdate();
    }

    private void hardDrop() {
        while (boardServices.isValidMove(board.getCurrentPiece(), 0, 1)){
            boardServices.movePieceDown();
        }
        lockPieceAndSpawnNew();
        notifyViewUpdate();
    }


    private void lockPieceAndSpawnNew() {
        boardServices.lockPiece();
        checkForLineClears();
        boardServices.spawnNewPiece();
        checkGameOver();
    }

    private void checkGameOver() {
        //ToDo()
    }

    private void checkForLineClears() {
        int linesCleared = boardServices.clearLines();
        if(linesCleared > 0){
            gameStateService.UpdateScore(linesCleared);
        }
        UpdateLevel();
    }

    private void UpdateLevel() {
        gameStateService.IncrementLevel();
    }

    private void initializeGameTimer() {
        gameTimer = new Timer(1000, e ->{
            moveCurrentPieceDown();
        });
        gameTimer.start();
    }

    private List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    private void notifyViewUpdate() {
        for (GameObserver observer : observers) {
            observer.onGameUpdate(board.getGrid(), gameState.getScore(), gameState.getLevel());
        }
    }


}
