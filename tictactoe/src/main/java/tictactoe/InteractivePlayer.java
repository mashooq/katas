package tictactoe;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

import static tictactoe.Move.move;

public class InteractivePlayer extends Player {
    private final String symbol;
    private final Scanner reader;
    private final PrintWriter writer;

    public InteractivePlayer(String symbol, Reader reader, Writer writer) {
        super(symbol);
        this.symbol = symbol;
        this.reader = new Scanner(reader);
        this.writer = new PrintWriter(writer);
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        writer.print(displayBoard(gameBoard.getPlayedMoves()));
        writer.print("\nNext Move: ");

        int receivedMove = reader.nextInt();
        int row = receivedMove / 3;
        int col = (receivedMove % 3) - 1;
        gameBoard.make(move(this, row, col));
    }


    private String displayBoard(Collection<Move> playedMoves) {
        StringBuilder currentBoard = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col != 0) currentBoard.append("|");
                Move move = findMove(playedMoves, col, row);
                if (move == null) {
                    currentBoard.append((row * 3) + (col + 1));
                } else {
                    currentBoard.append(move.getPlayer().getSymbol());
                }
            }
            if (row < 2) currentBoard.append("\n");
        }
        return currentBoard.toString();
    }

    private Move findMove(Collection<Move> playedMoves, int col, int row) {
        for (Move move : playedMoves) {
            if (move.getCol() == col && move.getRow() == row) {
                return move;
            }
        }

        return null;
    }
}
