package tictactoe;

import java.util.Collection;

import static tictactoe.Mark._;

class GameBoard {
    public static final int CELLS_IN_A_ROW = 3;
    private final RowGenerator rowGenerator = new RowGenerator();
    private Mark[][] currentGrid;

    public GameBoard() {
       currentGrid = newEmptyGrid();
    }

    public void make(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        validateMove(row, col);
        currentGrid[row][col] = move.getMark();
    }

    private void validateMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Unknown move, selected position doesn't exist");
        } else if (!empty(currentGrid[row][col])) {
            throw new IllegalArgumentException("Illegal Move: position is taken.");
        }
    }

    private boolean empty(Mark mark) {
        return mark == _;
    }

    public Mark findWinner() {
        Collection<Mark[]> rows = rowGenerator.getAllGameRows(cloneCurrentGrid());

        for (Mark[] row : rows) {
            if (!empty(row[0]) && row[0] == row[1] && row[1] == row[2]) {
                return row[0];
            }
        }

        return _;
    }

    public Mark[][] cloneCurrentGrid() {
        Mark[][] clonedGrid = newEmptyGrid();
        for (int row = 0; row < CELLS_IN_A_ROW; row++) {
            clonedGrid[row] = currentGrid[row].clone();
        }
        return clonedGrid;
    }

    public void reset() {
        currentGrid = newEmptyGrid();
    }

    public static Mark[][] newEmptyGrid() {
       return new Mark[][] {
               {_, _, _},
               {_, _, _},
               {_, _, _}
       };
    }
}