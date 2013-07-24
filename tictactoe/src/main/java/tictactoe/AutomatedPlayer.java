package tictactoe;

import java.util.*;

import static java.lang.Math.*;
import static tictactoe.Move.move;
import static tictactoe.Mark.*;

public class AutomatedPlayer extends Player {
    private final Mark oppositionsMark;
    private final RowGenerator rowGenerator = new RowGenerator();

    public AutomatedPlayer(Mark mark) {
        super(mark);
        oppositionsMark = mark == X ? O : X;
    }

    public boolean hasWinner(Mark[][] board) {
        Collection<Mark[]> rows = rowGenerator.getAllGameRows(board);

        for (Mark[] row : rows) {
            if (row[0] != _ && row[0] == row[1] && row[1] == row[2]) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
    }

    class ScoredMove {
        Move move;
        int score;

        ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    private Move chooseMove(Mark[][] board) {
        ScoredMove bestMove = minMaxMove(board, myMark, -100000, 100000);
        return bestMove.move;
    }

    private ScoredMove minMaxMove(Mark[][] board, Mark mark, int alpha, int beta) {
        List<Move> availableMoves = generateAvailableMoves(board, mark);
        if (availableMoves.size() == 0 || hasWinner(board)) {
            int score = calculateBoardScore(board, availableMoves.size());
            return new ScoredMove(null, score);
        }

        return getBestMove(board, mark, alpha, beta, availableMoves);
    }

    private ScoredMove getBestMove(Mark[][] board, Mark mark, int alpha, int beta, List<Move> availableMoves) {
        Move bestMove = null;
        int score;
        for (Move move : availableMoves) {
            makeMove(board, move);
            score = minMaxMove(board, switchMark(mark), alpha, beta).score;
            if (mark == myMark) {
                if (score > alpha) {
                    alpha = score;
                    bestMove = move;
                }
            } else {
                if (score < beta) {
                    beta = score;
                    bestMove = move;
                }
            }
            retractMove(board, move);

            if (alpha > beta) break;
        }

        return new ScoredMove(bestMove, (mark == myMark) ? alpha : beta);
    }

    private List<Move> generateAvailableMoves(Mark[][] board, Mark playersMark) {
        List<Move> availableMoves = new ArrayList<Move>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == _) {
                    availableMoves.add(move(playersMark, row, col));
                }
            }
        }
        return availableMoves;
    }

    private Mark switchMark(Mark mark) {
        return mark == X ? O : X;
    }

    private void retractMove(Mark[][] board, Move move) {
        board[move.getRow()][move.getCol()] = _;
    }

    private void makeMove(Mark[][] board, Move move) {
        board[move.getRow()][move.getCol()] = move.getMark();
    }

    private int calculateBoardScore(Mark[][] board, int numberOfAvailableMoves) {
        int score = 0;
        Collection<Mark[]> gameRows = rowGenerator.getAllGameRows(board);
        for (Mark[] row : gameRows) {
            int rowScore = calculateRowScore(row);
            score += rowScore;
        }

        return score * numberOfAvailableMoves;
    }

    private int calculateRowScore(Mark[] row) {
        int myMarks = 0;
        int oppositionMarks = 0;

        for (Mark mark : row) {
            if (mark == myMark) myMarks++;
            else if (mark == oppositionsMark) oppositionMarks++;
        }

        return calculateScoreBasedOnMarks(myMarks, oppositionMarks);
    }

    private int calculateScoreBasedOnMarks(int myMarks, int oppositionMarks) {
        if (oppositionMarks == 0) {
            return (int) pow(10, myMarks);
        } else if (myMarks == 0) {
            return -(int) pow(10, oppositionMarks);
        } else {
            return 0;
        }
    }
}
