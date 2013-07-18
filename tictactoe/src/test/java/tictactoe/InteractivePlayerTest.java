package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark.X;

@RunWith(MockitoJUnitRunner.class)
public class InteractivePlayerTest {
    @Mock
    GameBoard gameBoard;
    @Mock
    Player opposition;
    @Mock
    CommandPrompt prompt;
    InteractivePlayer player;

    @Before
    public void setupPlayer() {
        player = new InteractivePlayer(X, prompt);
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardAndPromptsForNextMove() {
        Mark[][] currentGrid = new Mark[3][3];
        given(gameBoard.cloneCurrentGrid()).willReturn(currentGrid);
        given(prompt.readMove()).willReturn(3);

        player.chooseMove(currentGrid);

        verify(prompt).displayBoard(currentGrid);
    }

    @Test
    public void takesMoveFromUsersInput() throws IOException {
        given(prompt.readMove()).willReturn(3);

        Move move = player.chooseMove(new Mark[3][3]);
        Move expectedMove = move(X, 0, 2);
        assertThat(move, is(expectedMove));
    }

    @Test
    public void promptsTheUserToTryAgainIfThePositionIsUnknown() {
        given(prompt.readMove()).willReturn(10).willReturn(3);

        Move move = player.chooseMove(new Mark[3][3]);

        verify(prompt).tryAgain();
        Move expectedMove = move(X, 0, 2);
        assertThat(move, is(expectedMove));
    }
}
