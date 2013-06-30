package tictactoe;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

public class CommandPrompt {
    final Scanner reader;
    final PrintWriter writer;

    public CommandPrompt(Reader reader, Writer writer) {
        this.reader = new Scanner(reader);
        this.writer = new PrintWriter(writer);
    }

    Move readMove(Player player) {
        Move move = null;
        boolean legalMoveMade = false;
        while (!legalMoveMade) {
            try {
                int receivedMove = reader.nextInt();
                int row = (receivedMove - 1) / 3;
                int col = (receivedMove - 1) % 3;
                move = Move.move(player, row, col);
                legalMoveMade = true;
            } catch (RuntimeException e) {
                writer.print("Illegal Move! Try again: ");
            }
        }
        return move;
    }

    void displayPrompt(String playedMoves) {
        writer.print(playedMoves);
        writer.print("\nNext Move: ");
        writer.flush();
    }

    String displayBoard(Collection<Move> playedMoves) {
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

    Move findMove(Collection<Move> playedMoves, int col, int row) {
        for (Move move : playedMoves) {
            if (move.getCol() == col && move.getRow() == row) {
                return move;
            }
        }

        return null;
    }
}