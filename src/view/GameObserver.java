package view;

import models.GameState;

public interface GameObserver {
    void onGameUpdate(int[][] grid, int score, int level);
}
