package tictactoe;

import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static tictactoe.Player.Mark;

public class CommandPrompt {
    final Scanner reader;
    final PrintWriter writer;

    public CommandPrompt(Scanner reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public int readMove() {
        int position = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                position = reader.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                tryAgain();
            }
        }

        return position;
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

    public void tryAgain() {
        writer.println("Illegal Move, Try Again: ");
        writer.flush();
    }

    public void announceWinner(Mark winner) {
        writer.flush();
        writer.println((winner + " wins"));
    }

    public void announceDraw() {
        writer.println("It's a draw!");
        writer.flush();
    }
}