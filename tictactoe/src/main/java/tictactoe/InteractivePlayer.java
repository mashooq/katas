package tictactoe;

import java.io.Reader;
import java.io.Writer;

public class InteractivePlayer extends Player {
    private final CommandPrompt commandPrompt;

    public InteractivePlayer(String symbol, Reader reader, Writer writer) {
        super(symbol);
        commandPrompt = new CommandPrompt(reader, writer);
    }

    @Override
    public void takeTurn(GameBoard gameBoard) {
        String playedMoves = commandPrompt.displayBoard(gameBoard.getPlayedMoves());
        commandPrompt.displayPrompt(playedMoves);
        Move move = commandPrompt.readMove(this);
        gameBoard.make(move);
    }
}
