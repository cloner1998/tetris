package models;

public class GameState {

    public Board board;
    private int score;
    private boolean isPaused;
    private int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
