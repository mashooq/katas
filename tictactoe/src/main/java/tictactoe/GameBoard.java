package tictactoe;

import java.util.List;
import java.util.Map;

class GameBoard {
    private Player[][] gameBoard = new Player[3][3];

    public void initialiseGameBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                gameBoard[row][col] = null;
            }
        }
    }

    public GameBoard clone() {
        GameBoard clone = new GameBoard();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                clone.gameBoard[row][col] = gameBoard[row][col];
            }
        }
        return clone;
    }

    public void make(Move move) {
        int row = move.getRow();
        int col = move.getCol();

        if (positionOccupied(row, col))
            throw new RuntimeException("Illegal Move!");

        gameBoard[row][col] = move.getPlayer();
    }

    private boolean positionOccupied(int row, int col) {
        return !empty(gameBoard[row][col]);
    }

    public int calculateScore(Player player) {
        int score = calculateHorizontal(player);
        score += calculateVertical(player);
        score += calculateDiagonalFromLeft(player);
        score += calculateDiagonalFromRight(player);

        return score;
    }

    int calculateHorizontal(Player player) {
        int score = 0;
        for (int row = 0; row < 3; row++) {
            score += calculateRowScore(player, gameBoard[row]);
        }
        return score;
    }

    private int calculateRowScore(Player player, Player[] row) {
        double playerMarks = 0;
        double oppositionMarks = 0;

        for (int i = 0; i < 3; i++) {
            if (empty(row[i])) continue;
            else if (row[i] == player) playerMarks++;
            else oppositionMarks++ ;
        }

        return calculateScoreBaseOnPositions(playerMarks, oppositionMarks);
    }

    private boolean empty(Player player) {
        return player == null;
    }

    private int calculateScoreBaseOnPositions(double playerMarks, double oppositionMarks) {
        return (int) (Math.pow(10d, playerMarks) - Math.pow(10d, oppositionMarks));
    }

    int calculateVertical(Player player) {
        Player[] positionsInARow = new Player[3];
        int score = 0;

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                positionsInARow[row] = gameBoard[row][col];
            }

            score += calculateRowScore(player, positionsInARow);
        }
        return score;
    }

    int calculateDiagonalFromLeft(Player player) {
        Player[] positionsInARow = new Player[3];
        for (int colRow = 0; colRow < 3; colRow++) {
            positionsInARow[colRow] = gameBoard[colRow][colRow];
        }

        return calculateRowScore(player, positionsInARow);
    }

    int calculateDiagonalFromRight(Player player) {
        Player[] positionsInARow = new Player[3];
        for (int row = 0; row < 3; row++) {
            positionsInARow[row] = gameBoard[row][2 - row];
        }

        return calculateRowScore(player, positionsInARow);
    }

    public List<Move> calculateFutureMoveScores(Player player) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}