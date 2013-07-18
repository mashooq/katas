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

    public Mark[][] cloneCurrentGrid() {
        Mark[][] clonedGrid = new Mark[CELLS_IN_A_ROW][CELLS_IN_A_ROW];
        for (int row = 0; row < CELLS_IN_A_ROW; row++) {
            clonedGrid[row] = currentGrid[row].clone();
        }
        return clonedGrid;
    }
}