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
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandPromptTest {
    @Mock java.io.PrintWriter writer;
    Scanner reader;

    private CommandPrompt prompt;

    @Before
    public void setup() {
        reader = new Scanner(new StringReader("3"));
        prompt = new CommandPrompt(reader, writer);
    }

    @Test
    public void takesTurnFromUsersInput() throws IOException {
        Move move = prompt.readMove(X);
        assertThat(move, is(move(X, 0, 2)));
    }

    @Test
    public void displaysTheCurrentStateOfTheBoardOnCommandPrompt() {
        Mark[][] currentGrid = new Mark[][]{
                {X,O,null}, {X,null,null}, {null,null,null}
        };

        prompt.displayBoard(currentGrid);

        String expectedBoard = "X|O|3\nX|5|6\n7|8|9";
        verify(writer).println(expectedBoard);
        verify(writer).println("Next Move: ");
    }

    @Test
    public void promptTheUserToTryAgain() {
        Move move = move(X, 0, 0);
        prompt.tryAgain(move);
        verify(writer).println(contains("Try Again"));
    }

}
