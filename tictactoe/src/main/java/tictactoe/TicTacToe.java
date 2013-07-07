package tictactoe;

import java.io.*;
import java.util.Scanner;

import static tictactoe.Player.*;
import static tictactoe.Player.Mark.*;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private GameBoard gameBoard;
    private CommandPrompt commandPrompt;

    public TicTacToe(Player player1, Player player2, GameBoard gameBoard, CommandPrompt commandPrompt) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;
        this.commandPrompt = commandPrompt;
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        CommandPrompt commandPrompt = new CommandPrompt(new Scanner(System.in), new PrintWriter(System.out));
        Player player1 = new InteractivePlayer(X, commandPrompt);
        Player player2 = new AutomatedPlayer(O);

        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);

        ticTacToe.start();
    }

    public void start() {
        Player player = player1;
        boolean noWinner = true;
        for (int i = 0; i < 9; i++) {
            Move move = player.takeTurn(gameBoard.cloneCurrentGrid());
            makeMove(move);

            Mark winner = gameBoard.findWinner();
            if (winner != null) {
                commandPrompt.announceWinner(winner);
                noWinner = false;
                break;
            }

            player = switchPlayer(player);
        }

        if (noWinner) {
            commandPrompt.announceDraw();
        }
    }

    private Player switchPlayer(Player player) {
        player = player == player1 ? player2 : player1;
        return player;
    }

    private void makeMove(Move move) {
        boolean legalMove = false;
        while (!legalMove) {
            try {
                gameBoard = gameBoard.make(move);
                legalMove = true;
            } catch (IllegalArgumentException e) {
                commandPrompt.tryAgain(move);
            }
        }
    }
}
