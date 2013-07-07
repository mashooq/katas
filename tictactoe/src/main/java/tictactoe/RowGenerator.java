package tictactoe;

import java.util.ArrayList;
import java.util.Collection;

public class RowGenerator {
    public Collection<Player.Mark[]> getAllGameRows(Player.Mark[][] currentGrid) {
        Collection<Player.Mark[]> rows = new ArrayList<Player.Mark[]>();
        rows.addAll(getHorizontal(currentGrid));
        rows.addAll(getVertical(currentGrid));
        rows.addAll(getDiagonalFromLeft(currentGrid));
        rows.addAll(getDiagonalFromRight(currentGrid));
        return rows;
    }

    Collection<Player.Mark[]> getHorizontal(Player.Mark[][] currentGrid) {
        Collection<Player.Mark[]> horizontalRows = new ArrayList<Player.Mark[]>();
        for (int row = 0; row < 3; row++) {
            horizontalRows.add(currentGrid[row].clone());
        }
        return horizontalRows;
    }

    Collection<Player.Mark[]> getVertical(Player.Mark[][] currentGrid) {
        Collection<Player.Mark[]> rows = new ArrayList<Player.Mark[]>();
        for (int col = 0; col < 3; col++) {
            Player.Mark[] marksInARow = new Player.Mark[3];
            for (int row = 0; row < 3; row++) {
                marksInARow[row] = currentGrid[row][col];
            }

            rows.add(marksInARow);
        }
        return rows;
    }

    Collection<Player.Mark[]> getDiagonalFromLeft(Player.Mark[][] currentGrid) {
        Collection<Player.Mark[]> rows = new ArrayList<Player.Mark[]>();
        Player.Mark[] marksInARow = new Player.Mark[3];
        for (int colRow = 0; colRow < 3; colRow++) {
            marksInARow[colRow] = currentGrid[colRow][colRow];
        }

        rows.add(marksInARow);
        return rows;
    }

    Collection<Player.Mark[]> getDiagonalFromRight(Player.Mark[][] currentGrid) {
        Collection<Player.Mark[]> rows = new ArrayList<Player.Mark[]>();
        Player.Mark[] marksInARow = new Player.Mark[3];
        for (int row = 0; row < 3; row++) {
            marksInARow[row] = currentGrid[row][2 - row];
        }

        rows.add(marksInARow);
        return rows;
    }
}