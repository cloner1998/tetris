package controller;

import models.Board;
import models.GameState;
import models.Tetrimino;
import serivces.BoardServices;

import javax.swing.*;

public class GameController{
    private BoardServices boardServices;

    public Board board;
    public GameState gameState;
    public Timer gameTimer;

    public GameController() {
        this.board = new Board(10, 20);
        this.gameState = new GameState();
        initializeGameTimer();
    }

    private void moveCurrentPieceDown() {
        if(boardServices.isValidMove(board.getCurrentPiece(), 0, 1)){
            boardServices.movePieceDown();
        }else{
            lockPieceAndSpawnNew();
        }
        notifyViewUpdate();
    }

    private void notifyViewUpdate() {
    }

    private void lockPieceAndSpawnNew() {
        
    }

    private void initializeGameTimer() {
        gameTimer = new Timer(1000, e ->{
            moveCurrentPieceDown();
        });
        gameTimer.start();
    }



}
