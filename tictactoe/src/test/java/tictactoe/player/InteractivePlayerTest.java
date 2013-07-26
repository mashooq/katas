package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.game.GameBoard;
import tictactoe.game.LineGenerator;
import tictactoe.game.Mark;
import tictactoe.game.Move;
import tictactoe.ui.Prompt;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.game.GameBoard.GRID_SIZE;
import static tictactoe.game.Mark.X;
import static tictactoe.game.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class InteractivePlayerTest {
    GameBoard gameBoard;
    @Mock Player opposition;
    @Mock Prompt prompt;
    InteractivePlayer player;
    Mark[][] currentGrid = new Mark[GRID_SIZE][GRID_SIZE];
    Move actualMove;

    @Before
    public void setupPlayer() {
        player = new InteractivePlayer(X, prompt);
        gameBoard = new GameBoard(new LineGenerator()) {
            public Mark[][] cloneCurrentGrid() { return currentGrid; }
            public void make(Move move) { actualMove = move; }
        };
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardAndPromptsForNextMove() {
        given(prompt.readMove()).willReturn(3);

        player.makeMove(gameBoard);

        verify(prompt).displayBoard(currentGrid, X);
    }

    @Test
    public void takesMoveFromUsersInput() throws IOException {
        given(prompt.readMove()).willReturn(3);

        player.makeMove(gameBoard);

        assertThat(actualMove, is(move(X, 0, 2)));
    }

    @Test
    public void promptsTheUserToTryAgainIfThePositionIsUnknown() {
        given(prompt.readMove()).willReturn(10).willReturn(3);

        player.makeMove(gameBoard);

        verify(prompt).tryAgain();
        assertThat(actualMove, is(move(X, 0, 2)));
    }
}
