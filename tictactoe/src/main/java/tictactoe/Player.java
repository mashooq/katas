package tictactoe;

public abstract class Player {
    private String symbol;

    protected Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
            return symbol;
    }

    public abstract void takeTurn(GameBoard gameBoard);
}
