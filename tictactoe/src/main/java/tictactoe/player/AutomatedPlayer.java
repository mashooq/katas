package tictactoe.player;

import tictactoe.game.GameBoard;
import tictactoe.game.LineGenerator;
import tictactoe.game.Mark;
import tictactoe.game.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.pow;
import static tictactoe.game.GameBoard.GRID_SIZE;
import static tictactoe.game.Mark.*;
import static tictactoe.game.Move.move;

public class AutomatedPlayer extends Player {
    private Mark oppositionsMark;
    private LineGenerator lineGenerator;

    public AutomatedPlayer(Mark mark, LineGenerator lineGenerator) {
        super(mark);
        this.lineGenerator = lineGenerator;
        oppositionsMark = mark == X ? O : X;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
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

            if (alpha == score && center(move)) bestMove = move;

            if (alpha > beta) break;
        }

        return new ScoredMove(bestMove, (mark == myMark) ? alpha : beta);
    }

    private boolean center(Move move) {
        return move.getCol() == 1 && move.getRow() == 1;
    }

    private List<Move> generateAvailableMoves(Mark[][] board, Mark playersMark) {
        List<Move> availableMoves = new ArrayList<Move>();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
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
        Collection<Mark[]> gameRows = lineGenerator.getAllGameRows(board);
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

    private boolean hasWinner(Mark[][] board) {
        Collection<Mark[]> rows = lineGenerator.getAllGameRows(board);

        for (Mark[] row : rows) {
            int xCount = 0, oCount = 0;
            for (Mark mark : row) {
                if (mark == X) xCount++;
                else if (mark == O) oCount++;
                else break;
            }

            if (xCount == GRID_SIZE || oCount == GRID_SIZE) return true;
        }

        return false;
    }

    class ScoredMove {
        Move move;
        int score;

        ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }

    }
}
