package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class InteractivePlayerTest {
    @Mock
    GameBoard gameBoard;

    Reader reader;
    StringWriter writer;
    private InteractivePlayer player;
    @Mock
    Player opposition;

    @Before
    public void setupPlayer() {
        reader = new StringReader("8");
        writer = new StringWriter();
        player = new InteractivePlayer("X", reader, writer);

        stub(opposition.getSymbol()).toReturn("O");
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardAndPromptsForNextMove() {
        Collection<Move> playedMoves = new ArrayList<Move>();
        playedMoves.add(move(player, 0, 0));
        playedMoves.add(move(opposition, 0, 1));
        playedMoves.add(move(player, 1, 0));
        given(gameBoard.getPlayedMoves()).willReturn(playedMoves);


        player.takeTurn(gameBoard);


        String expectedBoard = "X|O|3\nX|5|6\n7|8|9\nNext Move: ";
        assertThat(writer.toString(), is(expectedBoard));
    }

    @Test
    public void takesTurnFromUsersInput() throws IOException {
        player.takeTurn(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());

        Move expectedMove = move(player, 2, 1);
        assertThat(argumentCaptor.getValue(), is(expectedMove));

    }
}
