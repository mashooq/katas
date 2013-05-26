package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tictactoe.TicTacToe.Player.*;

public class TicTacToe {
    public enum Player {O, X}
    private Player computerSymbol = O;
    private Player personSymbol = X;
    private Player[][] gameBoard = new Player[3][3];

    private boolean playerHasFirstTurn = false;

    public TicTacToe() {
        initialiseGameBoard();
        makeComputerMove();
    }

    private void initialiseGameBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                gameBoard[row][col] = null;
            }
        }
    }

    public Player getComputerSymbol() {
        return computerSymbol;
    }

    public Player getPersonSymbol() {
        return personSymbol;
    }

    public void makeMove(int row, int col) {
        if (gameBoard[row][col] != null) throw new RuntimeException("Illegal Move!");
        gameBoard[row][col] = getPersonSymbol();
    }
    public boolean isGameBoardEmpty() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        initialiseGameBoard();
        swapPlayerSymbols();
        swapFirstTurn();

        if (!playerHasFirstTurn) {
            makeComputerMove();
        }
    }

    private void swapFirstTurn() {
        playerHasFirstTurn = !playerHasFirstTurn;
    }

    private void swapPlayerSymbols() {
        Player oldComputerSymbol = computerSymbol;
        computerSymbol = personSymbol;
        personSymbol = oldComputerSymbol;
    }

    public int calculateWinningPositions(Player player) {
        int winningPositions = calculateHorizontal(player);
        winningPositions += calculateVertical(player);
        winningPositions += calculateDiagonalFromLeft(player);
        winningPositions += calculateDiagonalFromRight(player);

        return winningPositions;
    }

    private int calculateHorizontal(Player player) {
        int winningPositions = 0;
        for (int row = 0; row < 3; row++) {
            if (isWinningRow(player, gameBoard[row])) {
                winningPositions++;
            }
        }
        return winningPositions;
    }

    private int calculateVertical(Player player) {
        Player[] positionsInARow = new Player[3];
        int winningPositions = 0;

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                positionsInARow[row] = gameBoard[row][col];
            }

            if (isWinningRow(player, positionsInARow)) {
                winningPositions++;
            }
        }
        return winningPositions;
    }

    private int calculateDiagonalFromLeft(Player player) {
        Player[] positionsInARow = new Player[3];
        for(int colRow = 0; colRow<3; colRow++) {
            positionsInARow[colRow] = gameBoard[colRow][colRow];
        }

        return isWinningRow(player, positionsInARow) ? 1 : 0;
    }

    private int calculateDiagonalFromRight(Player player) {
        Player[] positionsInARow = new Player[3];
        for(int row = 0; row<3; row++) {
            positionsInARow[row] = gameBoard[row][2-row];
        }

        return isWinningRow(player, positionsInARow) ? 1 : 0;
    }

    private boolean isWinningRow(Player player, Player[] row) {
        for (int i = 0; i < 3; i++) {
            if (row[i] != player && row[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void makeComputerMove() {
        gameBoard[1][1] = computerSymbol;
    }
}
