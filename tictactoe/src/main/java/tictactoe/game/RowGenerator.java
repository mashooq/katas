package tictactoe.game;

import java.util.ArrayList;
import java.util.Collection;

import static tictactoe.game.GameBoard.GRID_SIZE;

public class RowGenerator {
    public Collection<Mark[]> getAllGameRows(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        rows.addAll(getHorizontal(currentGrid));
        rows.addAll(getVertical(currentGrid));
        rows.addAll(getDiagonalFromLeft(currentGrid));
        rows.addAll(getDiagonalFromRight(currentGrid));
        return rows;
    }

    private Collection<Mark[]> getHorizontal(Mark[][] currentGrid) {
        Collection<Mark[]> horizontalRows = new ArrayList<Mark[]>();
        for (Mark[] row : currentGrid) {
            horizontalRows.add(row.clone());
        }
        return horizontalRows;
    }

    private Collection<Mark[]> getVertical(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        for (int col = 0; col < GRID_SIZE; col++) {
            Mark[] marksInARow = new Mark[GRID_SIZE];
            for (int row = 0; row < GRID_SIZE; row++) {
                marksInARow[row] = currentGrid[row][col];
            }

            rows.add(marksInARow);
        }
        return rows;
    }

    private Collection<Mark[]> getDiagonalFromLeft(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        Mark[] marksInARow = new Mark[GRID_SIZE];
        for (int colRow = 0; colRow < GRID_SIZE; colRow++) {
            marksInARow[colRow] = currentGrid[colRow][colRow];
        }

        rows.add(marksInARow);
        return rows;
    }

    private Collection<Mark[]> getDiagonalFromRight(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        Mark[] marksInARow = new Mark[GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            marksInARow[row] = currentGrid[row][(GRID_SIZE-1) - row];
        }

        rows.add(marksInARow);
        return rows;
    }
}