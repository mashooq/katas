package tictactoe;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import static tictactoe.Move.move;
import static tictactoe.Player.Mark;

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

    public Move readMove(Mark mark) {
        int receivedMove = reader.nextInt();
        int row = (receivedMove - 1) / 3;
        int col = (receivedMove - 1) % 3;
        return move(mark, row, col);
    }

    private void displayPrompt(String playedMoves) {
        writer.println(playedMoves);
        writer.println("Next Move: ");
        writer.flush();
    }

    public void displayBoard(Mark[][] currentGrid) {
        StringBuilder currentBoard = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col != 0) currentBoard.append("|");
                Mark mark = currentGrid[row][col];
                if (mark == null) {
                    currentBoard.append((row * 3) + (col + 1));
                } else {
                    currentBoard.append(mark);
                }
            }
            if (row < 2) currentBoard.append("\n");
        }
        displayPrompt(currentBoard.toString());
    }

    public void tryAgain(Move move) {
        writer.println("Illegal Move (" + move.getMovePosition() + ") Try Again: ");
        writer.flush();
    }

    public void announceWinner(Mark winner) {
        writer.println((winner + " wins"));
        writer.flush();
    }

    public void announceDraw() {
        writer.println("It's a draw!");
        writer.flush();
    }
}