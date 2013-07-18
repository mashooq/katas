package tictactoe;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark.X;

public class MoveTest {

    @Test
    public void shouldProvideAStringRepresentationOfTheMove() {
        assertThat(move(X, 1, 1).toString(), is("X,1,1"));
    }

    @Test
    public void movesThatAreEqualMustHaveTheSameHashCode() {
        Move move = move(X,0,0);
        Move equalMove = move(X,0,0);

        assertThat(move.hashCode(), is(equalMove.hashCode()));
    }


    @Test
    public void movesByTheSamePlayerInTheSamePositionAreEqual() {
        Move move = move(X,0,0);
        Move equalMove = move(X,0,0);

        assertThat(move, is(equalMove));
    }

}
