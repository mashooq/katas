package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;
import static tictactoe.Player.*;
import static tictactoe.Player.Mark.X;

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

        player.takeTurn(currentGrid);

        verify(prompt).displayBoard(currentGrid);
    }

    @Test
    public void takesTurnFromUsersInput() throws IOException {
        player.takeTurn(new Mark[3][3]);
        verify(prompt).readMove(X);
    }

}
