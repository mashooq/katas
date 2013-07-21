package tictactoe;

public abstract class Player {
    public enum Mark {X, O, _}

    protected Mark myMark;
    protected Player(Mark mark) {
        this.myMark = mark;
    }

    public void switchMark() {
        myMark = myMark == Mark.X ? Mark.O : Mark.X;
    }

    public abstract void makeMove(GameBoard gameBoard);
}
