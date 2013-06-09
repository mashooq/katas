package tictactoe;

import java.util.List;

public class AutomatedPlayer implements Player {
    @Override
    public void takeTurn(GameBoard gameBoard) {
        List<Move> futureMoves = gameBoard.calculateFutureMoveScores(this);
        Move bestMove = null;

        for (Move move : futureMoves) {
            if (bestMove == null || bestMove.getScore() < move.getScore()) {
                bestMove = move;
            }
        }

        gameBoard.make(bestMove);
    }
}
