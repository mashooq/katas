package tictactoe;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Scanner;

import static tictactoe.Move.move;

public class CommandPrompt {
    final Scanner reader;
    final PrintWriter writer;

    public CommandPrompt(Reader reader, Writer writer) {
        this.reader = new Scanner(reader);
        this.writer = new PrintWriter(writer);
    }

    public CommandPrompt(Scanner reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Move readMove(Player player) {
        int receivedMove = reader.nextInt();
        int row = (receivedMove - 1) / 3;
        int col = (receivedMove - 1) % 3;
        return move(player, row, col);
    }

    private void displayPrompt(String playedMoves) {
        writer.println(playedMoves);
        writer.println("Next Move: ");
        writer.flush();
    }

    public void displayBoard(Collection<Move> playedMoves) {
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
        displayPrompt(currentBoard.toString());
    }

    Move findMove(Collection<Move> playedMoves, int col, int row) {
        for (Move move : playedMoves) {
            if (move.getCol() == col && move.getRow() == row) {
                return move;
            }
        }

        return null;
    }

    public void tryAgain() {
        writer.println("Illegal Move! Try Again: ");
        writer.flush();
    }
}