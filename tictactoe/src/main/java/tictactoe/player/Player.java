package tictactoe.player;

import tictactoe.game.GameBoard;
import tictactoe.game.Mark;

public abstract class Player {
    protected Mark myMark;

    protected Player(Mark mark) {
        this.myMark = mark;
    }

    public void switchMark() {
        myMark = myMark == Mark.X ? Mark.O : Mark.X;
    }

    public abstract void makeMove(GameBoard gameBoard);
}