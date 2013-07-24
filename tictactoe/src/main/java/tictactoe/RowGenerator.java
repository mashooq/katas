package tictactoe;

import java.util.ArrayList;
import java.util.Collection;

class RowGenerator {
    public Collection<Mark[]> getAllGameRows(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        rows.addAll(getHorizontal(currentGrid));
        rows.addAll(getVertical(currentGrid));
        rows.addAll(getDiagonalFromLeft(currentGrid));
        rows.addAll(getDiagonalFromRight(currentGrid));
        return rows;
    }

    Collection<Mark[]> getHorizontal(Mark[][] currentGrid) {
        Collection<Mark[]> horizontalRows = new ArrayList<Mark[]>();
        for (int row = 0; row < 3; row++) {
            horizontalRows.add(currentGrid[row].clone());
        }
        return horizontalRows;
    }

    Collection<Mark[]> getVertical(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        for (int col = 0; col < 3; col++) {
            Mark[] marksInARow = new Mark[3];
            for (int row = 0; row < 3; row++) {
                marksInARow[row] = currentGrid[row][col];
            }

            rows.add(marksInARow);
        }
        return rows;
    }

    Collection<Mark[]> getDiagonalFromLeft(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        Mark[] marksInARow = new Mark[3];
        for (int colRow = 0; colRow < 3; colRow++) {
            marksInARow[colRow] = currentGrid[colRow][colRow];
        }

        rows.add(marksInARow);
        return rows;
    }

    Collection<Mark[]> getDiagonalFromRight(Mark[][] currentGrid) {
        Collection<Mark[]> rows = new ArrayList<Mark[]>();
        Mark[] marksInARow = new Mark[3];
        for (int row = 0; row < 3; row++) {
            marksInARow[row] = currentGrid[row][2 - row];
        }

        rows.add(marksInARow);
        return rows;
    }
}