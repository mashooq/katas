package tictactoe;

public abstract class Player {
    public enum Mark {X, O}
    protected final Mark myMark;

    protected Player(Mark mark) {
        this.myMark = mark;
    }

    public abstract void makeMove(GameBoard gameBoard);
}
