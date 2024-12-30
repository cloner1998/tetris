package models;

public class Tetrimino {
    private int[][] shape;
    private int x;
    private int y;
    private int color;

    public Tetrimino(int[][] shape, int x, int y, int color) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }
}
