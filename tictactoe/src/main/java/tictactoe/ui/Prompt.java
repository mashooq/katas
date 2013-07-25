package tictactoe.ui;

import tictactoe.game.Mark;

public interface Prompt {
    int readMove();

    void displayBoard(Mark[][] currentGrid, Mark playerMark);

    void tryAgain();

    void announceWinner(Mark winner);

    void announceDraw();

    boolean askToPlayAgain();
}
