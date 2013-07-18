package tictactoe;

public abstract class Player {
    public enum Mark {X, O}

    protected final Mark mark;

    protected Player(Mark mark) {
        this.mark = mark;
    }

    public abstract Move chooseMove(Mark[][] gameGrid);
}
