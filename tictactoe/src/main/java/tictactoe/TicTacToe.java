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
    private Player currentPlayer;
    private int numberOfTurnsTaken = 0;
    private Mark winner;

    public TicTacToe(Player player1, Player player2, GameBoard gameBoard, CommandPrompt commandPrompt) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;
        this.commandPrompt = commandPrompt;
        this.currentPlayer = player1;
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
        while (noWinner() && !aDraw()) {
            makeMove(currentPlayersChosenMove());
            switchPlayer();
            determineWinner();
        }

        if (aDraw()) commandPrompt.announceDraw();
        else commandPrompt.announceWinner(winner);
    }

    private Move currentPlayersChosenMove() {
        return currentPlayer.chooseMove(gameBoard.cloneCurrentGrid());
    }

    private void determineWinner() {
        winner = gameBoard.findWinner();
    }

    private boolean noWinner() {
        return winner == null;
    }

    private boolean aDraw() {
        return numberOfTurnsTaken == 9;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private void makeMove(Move move) {
        boolean legalMove = false;
        while (!legalMove) {
            try {
                gameBoard = gameBoard.make(move);
                numberOfTurnsTaken++;
                legalMove = true;
            } catch (IllegalArgumentException e) {
                commandPrompt.tryAgain();
            }
        }
    }
}
