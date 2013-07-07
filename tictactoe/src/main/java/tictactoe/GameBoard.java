package tictactoe;

import java.util.Collection;

import static tictactoe.Player.Mark;

class GameBoard {
    public static final int CELLS_IN_A_ROW = 3;
    private final RowGenerator rowGenerator = new RowGenerator();
    private Mark[][] currentGrid;

    public GameBoard() {
        this(new Mark[CELLS_IN_A_ROW][CELLS_IN_A_ROW]);
    }

    private GameBoard(Mark[][] grid) {
        currentGrid = grid;
    }

    public GameBoard make(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        if (positionOccupied(row, col))
            throw new IllegalArgumentException("Illegal Move: " + (row * 3 + col + 1) + " is taken.");

        Mark[][] clonedGrid = cloneCurrentGrid();
        clonedGrid[row][col] = move.getMark();

        return new GameBoard(clonedGrid);
    }

    private boolean positionOccupied(int row, int col) {
        return !empty(currentGrid[row][col]);
    }

    private boolean empty(Mark mark) {
        return mark == null;
    }

    public Mark findWinner() {
        Collection<Mark[]> rows = rowGenerator.getAllGameRows(cloneCurrentGrid());

        for (Mark[] row : rows) {
            if (row[0] != null && row[0] == row[1] && row[1] == row[2]) {
                return row[0];
            }
        }

        return null;
    }

    public GameBoard cloneBoard() {
        return new GameBoard(cloneCurrentGrid());
    }

    public Mark[][] cloneCurrentGrid() {
        Mark[][] clonedGrid = new Mark[CELLS_IN_A_ROW][CELLS_IN_A_ROW];
        for (int row = 0; row < CELLS_IN_A_ROW; row++) {
            clonedGrid[row] = currentGrid[row].clone();
        }
        return clonedGrid;
    }
}