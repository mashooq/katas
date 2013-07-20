package tictactoe;

import java.util.*;

import static java.lang.Math.*;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark.*;

public class AutomatedPlayer extends Player {
    private final Mark oppositionsMark;
    private final RowGenerator rowGenerator = new RowGenerator();

    public AutomatedPlayer(Mark mark) {
        super(mark);
        oppositionsMark = mark == X ? O : X;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
    }

    private Move chooseMove(Mark[][] board) {
        Move bestMove = null;
        int score = Integer.MIN_VALUE;
        List<Move> availableMoves = generateAvailableMoves(board, myMark);
        for (Move move : availableMoves) {
            make(board, move);
            int minScore = minMove(board);
            if (minScore > score) {
                score = minScore;
                bestMove = move;
            } else if (score == minScore && middleCell(move)) {
                bestMove = move;
            }
            retract(board, move);
        }

        return bestMove;
    }

    private boolean middleCell(Move move) {
        return move.getRow() == 1 && move.getCol() == 1;
    }


    private int maxMove (Mark[][] board) {
        List<Move> availableMoves = generateAvailableMoves(board, myMark);
        if (availableMoves.size() == 0 || hasWinner(board)) {
            return calculateScore(board, false, availableMoves.size());
        }

        int score = Integer.MIN_VALUE;
        for (Move move : availableMoves) {
            make(board, move);
            int minScore = minMove(board);
            if (minScore > score) {
                score = minScore;
            }
            retract(board, move);
        }

        return score;
    }

    private void retract(Mark[][] board, Move move) {
        board[move.getRow()][move.getCol()] = null;
    }

    private void make(Mark[][] board, Move move) {
        board[move.getRow()][move.getCol()] = move.getMark();
    }

    private int minMove(Mark[][] board) {
        List<Move> availableMoves = generateAvailableMoves(board, oppositionsMark);
        if (availableMoves.size() == 0 || hasWinner(board)) {
            return calculateScore(board, true, availableMoves.size());
        }

        int score = Integer.MAX_VALUE;
        for (Move move : availableMoves) {
            make(board, move);
            int maxScore = maxMove(board);
            if (maxScore < score) {
                score = maxScore;
            }
            retract(board, move);
        }

        return score;
    }

    private int calculateScore(Mark[][] board, boolean myTurn, int numberOfAvailableMoves) {
        int score = 0;
        Collection<Mark[]> gameRows = rowGenerator.getAllGameRows(board);
        for (Mark[] row : gameRows) {
            int rowScore = calculateRowScore(row, myTurn);
            score += rowScore;
        }

        return score * numberOfAvailableMoves;
    }

    private int calculateRowScore(Mark[] row, boolean myTurn) {
        int myMarks = 0;
        int oppositionMarks = 0;

        for (Mark mark : row) {
            if (mark == myMark) myMarks++;
            else if (mark == oppositionsMark) oppositionMarks++;
        }

        return calculateScoreBasedOnMarks(myTurn, myMarks, oppositionMarks);
    }

    private int calculateScoreBasedOnMarks(boolean myTurn, int myMarks, int oppositionMarks) {
        int advantage = 1;
        if (oppositionMarks == 0) {
            if (myTurn) advantage = 3;
            return (int) pow(10, myMarks) * advantage;
        } else if (myMarks == 0) {
            if (!myTurn) advantage = 3;
            return -(int) pow(10, oppositionMarks) * advantage;
        } else {
            return 0;
        }
    }

    private List<Move> generateAvailableMoves(Mark[][] board, Mark playersMark) {
        List<Move> availableMoves = new ArrayList<Move>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) {
                    availableMoves.add(move(playersMark, row, col));
                }
            }
        }
        return availableMoves;
    }

    public boolean hasWinner(Mark[][] board) {
        Collection<Mark[]> rows = rowGenerator.getAllGameRows(board);

        for (Mark[] row : rows) {
            if (row[0] != null && row[0] == row[1] && row[1] == row[2]) {
                return true;
            }
        }

        return false;
    }
}
