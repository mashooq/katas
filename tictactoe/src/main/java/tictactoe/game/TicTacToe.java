package tictactoe.game;

import tictactoe.player.AutomatedPlayer;
import tictactoe.player.InteractivePlayer;
import tictactoe.player.Player;
import tictactoe.ui.CommandPrompt;
import tictactoe.ui.Prompt;

import java.io.PrintWriter;
import java.util.Scanner;

import static tictactoe.game.Mark.*;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private GameBoard gameBoard;
    private Prompt prompt;
    private Player currentPlayer;
    private int numberOfTurnsTaken = 0;
    private Mark winner;

    public TicTacToe(Player player1, Player player2, GameBoard gameBoard, Prompt prompt) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;
        this.prompt = prompt;
        this.currentPlayer = player1;
        this.winner = _;
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        Prompt prompt =
                new CommandPrompt(new Scanner(System.in), new PrintWriter(System.out));

        new TicTacToe(
                new InteractivePlayer(X, prompt),
                new AutomatedPlayer(O),
                gameBoard,
                prompt)
                .start();
    }

    public void start() {
        boolean continueGame = true;
        while(continueGame) {
            playGame();

            announceResult();

            continueGame = prompt.askToPlayAgain();
            if (continueGame) resetGame();
        }
    }

    private void playGame() {
        while (noWinner() && !aDraw()) {
            makeMove();
            incrementNumberOfTurnsTaken();
            switchPlayer();
            determineWinner();
        }
    }

    private void announceResult() {
        if (aDraw()) prompt.announceDraw();
        else prompt.announceWinner(winner);
    }

    private void resetGame() {
        numberOfTurnsTaken = 0;
        winner = _;
        switchPlayers();
        gameBoard.reset();
    }

    private void switchPlayers() {
        Player player = player1;
        player1 = player2;
        player2 = player;

        player1.switchMark();
        player2.switchMark();
    }

    private void incrementNumberOfTurnsTaken() {
        numberOfTurnsTaken++;
    }

    private void makeMove() {
        currentPlayer.makeMove(gameBoard);
    }

    private void determineWinner() {
        winner = gameBoard.findWinner();
    }

    private boolean noWinner() {
        return winner == _;
    }

    private boolean aDraw() {
        return numberOfTurnsTaken == 9;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }
}
