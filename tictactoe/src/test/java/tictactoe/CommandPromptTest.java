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
import static org.mockito.Mockito.verify;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark.O;
import static tictactoe.Player.Mark.X;

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
                {X,O,null}, {X,null,null}, {null,null,null}
        };

        prompt.displayBoard(currentGrid);

        String expectedBoard = " | |3    X|O| \n";
        expectedBoard +=       " |5|6    X| | \n";
        expectedBoard +=       "7|8|9     | | ";
        verify(writer).println(expectedBoard);
        verify(writer).println("Next Move: ");
    }

    @Test
    public void promptTheUserToTryAgain() {
        prompt.tryAgain();
        verify(writer).println(contains("Try Again"));
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
