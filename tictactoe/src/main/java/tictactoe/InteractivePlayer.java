package tictactoe;

public class InteractivePlayer extends Player {
    private final CommandPrompt commandPrompt;

    public InteractivePlayer(Mark mark, CommandPrompt prompt) {
        super(mark);
        commandPrompt = prompt;
    }

    @Override
    public Move takeTurn(Mark[][] currentGrid) {
        commandPrompt.displayBoard(currentGrid);
        return commandPrompt.readMove(mark);
    }
}
