package models;

public class Tetrimino {
    private int[][] shape;
    private int[][] coordinates;
    private int color;

    public Tetrimino(int[][] shape, int[][] coordinates, int color) {
        this.shape = shape;
        this.coordinates = coordinates;
        this.color = color;
    }


}
