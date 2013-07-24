package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.verify;
import static tictactoe.Mark.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandPromptTest {
    public static final String PLAYER_INPUT = "3";
    @Mock java.io.PrintWriter writer;
    private CommandPrompt prompt;

    @Before
    public void setup() {
        reader = new Scanner(new StringReader(PLAYER_INPUT));
        prompt = new CommandPrompt(reader, writer);
    }

    Scanner reader;

    @Test
    public void readsPositionFromCommandPrompt() throws IOException {
        int position = prompt.readMove();
        assertThat(position, is(new Integer(PLAYER_INPUT)));
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardOnCommandPrompt() {
        Mark[][] currentGrid = new Mark[][]{
                {X, O, _},
                {X, _, _},
                {_, _, _}
        };

        prompt.displayBoard(currentGrid, X);

        String expectedBoard =    "\n | |3    X|O| \n";
        expectedBoard +=            " |5|6    X| | \n";
        expectedBoard +=            "7|8|9     | | \n";
        verify(writer).println(expectedBoard);
        verify(writer).print(startsWith("Next Move"));
    }

    @Test
    public void promptsTheUserToTryAgain() {
        prompt.tryAgain();
        verify(writer).println(contains("Try Again"));
    }

    @Test
    public void asksTheUserToTryAgain() {
        boolean answer = prompt.askToPlayAgain();

        verify(writer).print(startsWith("Play again"));
        assertThat(answer, is(false));
    }

    @Test
    public void announcesTheWinner() {
        prompt.announceWinner(X);
        verify(writer).println(contains(X + " wins"));
    }

    @Test
    public void announcesDraw() {
        prompt.announceDraw();
        verify(writer).println(contains("draw"));
    }

}
