package tictactoe.game;

import java.util.Arrays;
import java.util.Collection;

import static tictactoe.game.Mark.*;

public class GameBoard {
    public static final int GRID_SIZE = 3;
    private LineGenerator lineGenerator;
    private Mark[][] currentGrid;

    public GameBoard(LineGenerator lineGenerator) {
        this.lineGenerator = lineGenerator;
        currentGrid = newEmptyGrid();
    }

    public void make(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        validateMove(row, col);
        currentGrid[row][col] = move.getMark();
    }

    public Mark findWinner() {
        Collection<Mark[]> rows = lineGenerator.getAllGameRows(cloneCurrentGrid());

        for (Mark[] row : rows) {
            int xCount = 0, oCount = 0;
            for (Mark mark : row) {
                if (mark == X) xCount++;
                else if (mark == O) oCount++;
                else break;
            }

            if (xCount == GRID_SIZE) return X;
            else if (oCount == GRID_SIZE) return O;
        }

        return _;
    }

    public Mark[][] cloneCurrentGrid() {
        Mark[][] clonedGrid = newEmptyGrid();
        for (int row = 0; row < GRID_SIZE; row++) {
            clonedGrid[row] = currentGrid[row].clone();
        }
        return clonedGrid;
    }

    public void reset() {
        currentGrid = newEmptyGrid();
    }

    private void validateMove(int row, int col) {
        if (row < 0 || row > GRID_SIZE - 1 || col < 0 || col > GRID_SIZE - 1) {
            throw new IllegalArgumentException("Unknown move, selected position doesn't exist");
        } else if (!empty(currentGrid[row][col])) {
            throw new IllegalArgumentException("Illegal Move: position is taken.");
        }
    }

    private boolean empty(Mark mark) {
        return mark == _;
    }

    private Mark[][] newEmptyGrid() {
        Mark[][] newGrid = new Mark[GRID_SIZE][GRID_SIZE];
        for (Mark[] row : newGrid) {
            Arrays.fill(row, _);
        }

        return newGrid;
    }
}