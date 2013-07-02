package tictactoe;

import java.io.*;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private final GameBoard gameBoard;
    private PrintStream writer;

    public TicTacToe(Player player1, Player player2, GameBoard gameBoard, PrintStream writer) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;
        this.writer = writer;
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        Reader reader = new InputStreamReader(System.in);
        Writer writer = new OutputStreamWriter(System.out);
        Player player1 = new InteractivePlayer("X", reader, writer);
        Player player2 = new AutomatedPlayer("O");

        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, System.out);

        ticTacToe.start();

    }

    public void start() {
        Player player = player1;
        for (int i = 0; i < 9; i++) {
            player.takeTurn(gameBoard);
            if (gameBoard.calculateScore(player) > 250) {
                writer.println(player.getSymbol() + " wins");
                return;
            }

            player = player == player1 ? player2 : player1;
        }

        writer.println("It's a draw!");
    }
}
