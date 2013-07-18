package tictactoe;

import java.util.SortedMap;
import java.util.TreeMap;

import static tictactoe.Move.move;
import static tictactoe.Player.Mark.*;

public class AutomatedPlayer extends Player {
    private final Mark oppositionsMark;
    public AutomatedPlayer(Mark mark) {
        super(mark);
        oppositionsMark = mark == X ? O : X;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
    }

    private Move chooseMove(Mark[][] currentGrid) {
        SortedMap<Double, Move> potentialMovesForOpposition =  findPotentialMovesFor(oppositionsMark, currentGrid);
        SortedMap<Double, Move> potentialMovesForMe = findPotentialMovesFor(mark, currentGrid);

        if (potentialMovesForOpposition.lastKey() > 1000 && potentialMovesForMe.lastKey() < 1000) {
            Move oppositionMove = potentialMovesForOpposition.get(potentialMovesForOpposition.lastKey());
            return move(mark, oppositionMove.getRow(), oppositionMove.getCol());
        } else {
            return potentialMovesForMe.get(potentialMovesForMe.lastKey());
        }
    }

    private SortedMap<Double, Move> findPotentialMovesFor(Mark mark, Mark[][] currentGrid) {
        SortedMap<Double, Move> potentialMoves = new TreeMap<Double, Move>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (currentGrid[row][col] == null) {
                    Move move = move(mark, row, col);
                    Double winningPositions = findWinningPositions(currentGrid, move);
                    potentialMoves.put(winningPositions, move);
                }
            }
        }
        return potentialMoves;
    }

    private double findWinningPositions(Mark[][] currentGrid, Move move) {
        Mark[][] grid = makeMove(move, currentGrid);
        return findHorizontal(move.getMark(), grid) + findVertical(move.getMark(), grid) +
                findDiagonalFromLeft(move.getMark(), grid) + findDiagonalFromRight(move.getMark(), grid);

    }

    private Mark[][] makeMove(Move move, Mark[][] currentGrid) {
        Mark[][] clonedGrid = new Mark[3][3];
        for (int row = 0; row < 3; row++) {
            clonedGrid[row] = currentGrid[row].clone();
        }

        clonedGrid[move.getRow()][move.getCol()] = move.getMark();
        return clonedGrid;
    }

    private double findHorizontal(Mark playersMark, Mark[][] currentGrid) {
        double score = 0;
        for (int row = 0; row < 3; row++) {
            score += calculateRowScore(playersMark, currentGrid[row]);
        }
        return score;
    }

    private double calculateRowScore(Mark playersMark, Mark[] row) {
        int playerMarks = 0;
        for (int i = 0; i < 3; i++) {
            if (row[i] == playersMark) {
                playerMarks++;
            } else if (row[i] != null) {
                return 0;
            }
        }
        return playerMarks == 0? 0 : Math.pow(10, playerMarks);
    }

    private int findVertical(Mark playersMark, Mark[][] currentGrid) {
        Mark[] marksInARow = new Mark[3];
        int score = 0;

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                marksInARow[row] = currentGrid[row][col];
            }

            score += calculateRowScore(playersMark, marksInARow);
        }
        return score;
    }

    private double findDiagonalFromLeft(Mark playersMark, Mark[][] currentGrid) {
        Mark[] marksInARow = new Mark[3];
        for (int colRow = 0; colRow < 3; colRow++) {
            marksInARow[colRow] = currentGrid[colRow][colRow];
        }

        return calculateRowScore(playersMark, marksInARow);
    }

    private double findDiagonalFromRight(Mark playersMark, Mark[][] currentGrid) {
        Mark[] marksInARow = new Mark[3];
        for (int row = 0; row < 3; row++) {
            marksInARow[row] = currentGrid[row][2 - row];
        }

        return calculateRowScore(playersMark, marksInARow);
    }
}
