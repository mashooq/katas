package tictactoe.player;

import tictactoe.game.GameBoard;
import tictactoe.game.Mark;
import tictactoe.game.Move;
import tictactoe.ui.Prompt;

import static tictactoe.game.GameBoard.GRID_SIZE;
import static tictactoe.game.Move.move;

public class InteractivePlayer extends Player {
    private final Prompt prompt;

    public InteractivePlayer(Mark mark, Prompt prompt) {
        super(mark);
        this.prompt = prompt;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        boolean legalMove = false;
        while (!legalMove) {
            try {
                gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
                legalMove = true;
            } catch (IllegalArgumentException e) {
                prompt.tryAgain();
            }
        }
    }

    private Move chooseMove(Mark[][] currentGrid) {
        prompt.displayBoard(currentGrid, myMark);
        int position = readPosition();
        return convertToMove(position);
    }

    private Move convertToMove(int position) {
        int row = (position - 1) / GRID_SIZE;
        int col = (position - 1) % GRID_SIZE;
        return move(myMark, row, col);
    }

    private int readPosition() {
        int position;
        while(true) {
            position = prompt.readMove();
            if(valid(position)) break;
            else prompt.tryAgain();
        }
        return position;
    }

    private boolean valid(int position) {
        return position > 0 && position < 10;
    }
}
