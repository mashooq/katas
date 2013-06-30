package tictactoe;

import java.util.Collection;

public class AutomatedPlayer extends Player {
    public AutomatedPlayer(String symbol) {
        super(symbol);
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        Collection<Move> futureMoves = gameBoard.getAvailableMovesWithScores(this);

        System.out.println("Future Moves: ");
        System.out.println(futureMoves);

        Move bestMove = null;

        for (Move move : futureMoves) {
            if (bestMove == null || bestMove.getScore() < move.getScore()) {
                bestMove = move;
            }
        }

        gameBoard.make(bestMove);
    }

}
