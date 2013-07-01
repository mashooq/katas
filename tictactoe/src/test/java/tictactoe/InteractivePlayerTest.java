package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;

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
        player = new InteractivePlayer("X", prompt);
        given(opposition.getSymbol()).willReturn("O");
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardAndPromptsForNextMove() {
        Collection<Move> playedMoves = new ArrayList<Move>();
        given(gameBoard.getPlayedMoves()).willReturn(playedMoves);

        player.takeTurn(gameBoard);

        verify(prompt).displayBoard(playedMoves);
    }

    @Test
    public void takesTurnFromUsersInput() throws IOException {
        Move move = move(player, 0, 1);
        given(prompt.readMove(player)).willReturn(move);
        player.takeTurn(gameBoard);

        verify(gameBoard).make(move);
    }

    @Test
    public void promptsUserAgainToTryAgain() {
        Move move = move(player, 0, 2);
        doThrow(IllegalArgumentException.class).doNothing().when(gameBoard).make(move);
        given(prompt.readMove(player)).willReturn(move);

        player.takeTurn(gameBoard);

        verify(prompt).tryAgain();
    }
}
