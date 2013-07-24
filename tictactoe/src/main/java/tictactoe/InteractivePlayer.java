package tictactoe;

import static tictactoe.Move.move;

public class InteractivePlayer extends Player {
    private final CommandPrompt commandPrompt;

    public InteractivePlayer(Mark mark, CommandPrompt prompt) {
        super(mark);
        commandPrompt = prompt;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        boolean legalMove = false;
        while (!legalMove) {
            try {
                gameBoard.make(chooseMove(gameBoard.cloneCurrentGrid()));
                legalMove = true;
            } catch (IllegalArgumentException e) {
                commandPrompt.tryAgain();
            }
        }
    }

    private Move chooseMove(Mark[][] currentGrid) {
        commandPrompt.displayBoard(currentGrid, myMark);
        int position = readPosition();
        return convertToMove(position);
    }

    private Move convertToMove(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return move(myMark, row, col);
    }

    private int readPosition() {
        int position;
        while(true) {
            position = commandPrompt.readMove();
            if(valid(position)) break;
            else commandPrompt.tryAgain();
        }
        return position;
    }

    private boolean valid(int position) {
        return position > 0 && position < 10;
    }
}
