package tictactoe;

import java.io.Reader;
import java.io.Writer;

public class InteractivePlayer extends Player {
    private final CommandPrompt commandPrompt;

    public InteractivePlayer(String symbol, Reader reader, Writer writer) {
        this(symbol, new CommandPrompt(reader, writer));
    }

    public InteractivePlayer(String symbol, CommandPrompt prompt) {
        super(symbol);
        commandPrompt = prompt;
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        commandPrompt.displayBoard(gameBoard.getPlayedMoves());

        boolean legalMove = false;
        while (!legalMove) {
            try {
                Move move = commandPrompt.readMove(this);
                gameBoard.make(move);
                legalMove = true;
            } catch (IllegalArgumentException exception) {
                commandPrompt.tryAgain();
            }
        }
    }
}
