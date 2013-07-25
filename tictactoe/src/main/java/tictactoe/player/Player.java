package tictactoe.player;

import tictactoe.game.GameBoard;
import tictactoe.game.Mark;

public abstract class Player {
    protected Mark myMark;

    protected Player(Mark mark) {
        this.myMark = mark;
    }

    public void setMark(Mark mark) {
        myMark = mark;
    }

    public abstract void makeMove(GameBoard gameBoard);
}
