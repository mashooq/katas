package tictactoe;

import static tictactoe.Player.*;

public class Move {
    private Mark mark;
    private int row;
    private int col;

    private Move(Mark mark, int row, int col) {
        this.mark = mark;
        this.row = row;
        this.col = col;
    }

    public Mark getMark() {
        return mark;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static Move move(Mark mark, int row, int col) {
        return new Move(mark, row, col);
    }

    public String toString() {
        return mark + "," + row + "," + col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (col != move.col) return false;
        if (row != move.row) return false;
        if (mark != move.mark) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mark.hashCode();
        result = 31 * result + row;
        result = 31 * result + col;
        return result;
    }
}
