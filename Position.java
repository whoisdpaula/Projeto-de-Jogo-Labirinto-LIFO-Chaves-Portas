package br.mack.labirinto.core;

public class Position {
    public final int row;
    public final int col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Position move(int dRow, int dCol) {
        return new Position(row + dRow, col + dCol);

    }
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Position)) return false;
        Position p = (Position)o;
        return this.row == p.row && this.col == p.col;
    }
    @Override
    public int hashCode() {
        return 31 * row + col;

    }
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
