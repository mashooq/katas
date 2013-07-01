package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class CommandPromptTest {
    @Mock java.io.PrintWriter writer;
    Scanner reader;
    @Mock Player player;
    @Mock Player opposition;
    private CommandPrompt prompt;

    @Before
    public void setup() {
        reader = new Scanner(new StringReader("3"));
        prompt = new CommandPrompt(reader, writer);
        given(player.getSymbol()).willReturn("X");
        given(opposition.getSymbol()).willReturn("O");
    }

    @Test
    public void takesTurnFromUsersInput() throws IOException {
        Move move = prompt.readMove(player);
        assertThat(move, is(move(player, 0, 2)));
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardOnCommandPrompt() {
        Collection<Move> playedMoves = new ArrayList<Move>();
        playedMoves.add(move(player, 0, 0));
        playedMoves.add(move(opposition, 0, 1));
        playedMoves.add(move(player, 1, 0));

        prompt.displayBoard(playedMoves);

        String expectedBoard = "X|O|3\nX|5|6\n7|8|9";
        verify(writer).println(expectedBoard);
        verify(writer).println("Next Move: ");
    }

    @Test
    public void promptTheUserToTryAgain() {
        prompt.tryAgain();
        verify(writer).println("Illegal Move! Try Again: ");

    }

}
