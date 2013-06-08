package tictactoe;

public class TicTacToe {
    private final GameBoard gameBoard = new GameBoard();
    private Player firstPlayer;
    private Player secondPlayer;
    private Player nextTurn;


    public TicTacToe(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.nextTurn = firstPlayer;
        gameBoard.initialiseGameBoard();
    }

    private void initialiseGameBoard() {
        gameBoard.initialiseGameBoard();
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void make(PlayerMove move) {
        if (move.getPlayer() != nextTurn) throw new RuntimeException("Not your turn!");

        gameBoard.make(move);
        switchTurns();
    }

    public Player getNextTurn() {
        return nextTurn;
    }

    public void reset() {
        initialiseGameBoard();
        switchTurns();
    }

    private void switchTurns() {
        nextTurn = nextTurn == firstPlayer ? secondPlayer : firstPlayer;
    }

    public int calculateScore(Player player) {
       return gameBoard.calculateScore(player);
    }
}
