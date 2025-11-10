package br.mack.labirinto.core;
import br.mack.labirinto.model.Inventory;

public class Board {
    private char[][] grid;
    private int row;
    private int col;
    private int keycapacity;
    private Position start;
    private Position exit;

    public Board(char[][] grid, int row, int col, int keycapacity, Position start, Position exit) {
        this.row = row;
        this.grid = grid;
        this.col = col;
        this.keycapacity = keycapacity;
        this.start = start;
        this.exit = exit;

    }
    public boolean inBounds(Position p) {
        return p.row >= 0 && p.row < this.row && p.col >= 0 && p.col < this.col;
    }
    public char getCell(Position p) {
        return grid[p.row][p.col];
    }
    public void setCell(Position p, char c) {
        grid[p.row][p.col] = c;
    }
    public Position getStart() {
        return start;
    }
    public Position getExit() {
        return exit;
    }
    public int getKeycapacity() {
        return keycapacity;
    }
    public void render(Position playerPos, Inventory inventory){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(playerPos.row == i && playerPos.col == j){
                    System.out.print("@");
                }else{
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("Chaves:"+inventory.size());
    }
}
