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
        printLine(playedMoves);
        printLine("Next Move: ");
    }

    public void displayBoard(Mark[][] currentGrid) {
        StringBuilder currentBoard = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            displayEmptyPositions(currentGrid[row], currentBoard, row);
            currentBoard.append("    ");
            displayTakenPositions(currentGrid[row], currentBoard);

            if (row < 2) currentBoard.append("\n");
        }
        displayPrompt(currentBoard.toString());
    }

    private void displayTakenPositions(Mark[] marks, StringBuilder currentBoard) {
        for (int col = 0; col < 3; col++) {
            if (col != 0) currentBoard.append("|");
            Mark mark = marks[col];
            if (mark == null) {
                currentBoard.append(" ");
            } else {
                currentBoard.append(mark);
            }
        }
    }

    private void displayEmptyPositions(Mark[] marks, StringBuilder currentBoard, int row) {
        for (int col = 0; col < 3; col++) {
            if (col != 0) currentBoard.append("|");
            Mark mark = marks[col];
            if (mark == null) {
                currentBoard.append((row * 3) + (col + 1));
            } else {
                currentBoard.append(" ");
            }
        }
    }

    public void tryAgain() {
        printLine("Illegal Move, Try Again: ");
    }

    private void printLine(String line) {
        writer.println(line);
        writer.flush();
    }

    public void announceWinner(Mark winner) {
        printLine((winner + " wins"));
    }

    public void announceDraw() {
        printLine("It's a draw!");
    }
}