package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.Mark.X;
import static tictactoe.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class InteractivePlayerTest {
    @Mock GameBoard gameBoard;
    @Mock Player opposition;
    @Mock CommandPrompt prompt;
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

        player.makeMove(gameBoard);

        verify(prompt).displayBoard(currentGrid, X);
    }

    @Test
    public void takesMoveFromUsersInput() throws IOException {
        Mark[][] currentGrid = new Mark[3][3];
        given(gameBoard.cloneCurrentGrid()).willReturn(currentGrid);
        given(prompt.readMove()).willReturn(3);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(move(X, 0, 2)));
    }

    @Test
    public void promptsTheUserToTryAgainIfThePositionIsUnknown() {
        given(prompt.readMove()).willReturn(10).willReturn(3);

        player.makeMove(gameBoard);

        verify(prompt).tryAgain();
        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(move(X, 0, 2)));
    }
}
