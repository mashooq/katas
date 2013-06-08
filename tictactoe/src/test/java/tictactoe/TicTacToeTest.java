package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static tictactoe.PlayerMove.move;

public class TicTacToeTest {
    private TicTacToe ticTacToe;
    private Player person = Player.O;
    private Player computer = Player.X;

    @Before
    public void setupGame() {
        ticTacToe = new TicTacToe(computer, person);
        person = ticTacToe.getSecondPlayer();
        computer = ticTacToe.getFirstPlayer();
    }

    @Test
    public void shouldAlternateFirstTurnBetweenFirstAndSecondPlayer() {
        assertThat(ticTacToe.getNextTurn(), is(computer));
        ticTacToe.reset();
        assertThat(ticTacToe.getNextTurn(), is(person));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        ticTacToe.make(move(computer, 0, 0));
        ticTacToe.make(move(person, 0, 0));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowOutOfTurnMove() {
       assertThat(ticTacToe.getNextTurn(), is(computer));
       ticTacToe.make(move(person, 0, 0));
    }

    @Test
    public void playerWithMultipleOneInARowHasScoreMoreThan10AndLessThan100() {
        ticTacToe.make(move(computer, 1, 1));
        assertTrue(ticTacToe.calculateScore(computer) > 10);
        assertTrue(ticTacToe.calculateScore(computer) < 100);
    }

    @Test
    public void oppositionWithMultipleOneInARowHasScoreLessThan10AndMoreThan100() {
        ticTacToe.make(move(computer, 1, 1));
        System.out.println(ticTacToe.calculateScore(person));
        assertTrue(ticTacToe.calculateScore(person) < -10);
        assertTrue(ticTacToe.calculateScore(person) > -100);
    }

    @Test
    public void playerWithASingle_OneInARow_HasScoreMoreThan1AndLessThan10() {
        ticTacToe.make(move(computer, 1, 1));
        ticTacToe.make(move(person, 0, 0));

        assertTrue(ticTacToe.calculateScore(computer) > 1);
        assertTrue(ticTacToe.calculateScore(computer) < 10);
    }

    @Test
    public void playerWithASingle_TwoInARow_HasScoreMoreThan100AndLessThan110() {
        ticTacToe.make(move(computer, 1, 1));
        ticTacToe.make(move(person, 0, 0));
        ticTacToe.make(move(computer, 0, 1));

        assertTrue(ticTacToe.calculateScore(computer) > 100);
        assertTrue(ticTacToe.calculateScore(computer) < 110);
    }

    @Test
    public void playerWithAMultiple_TwoInARow_HasScoreMoreThan110AndLessThan200() {
        ticTacToe.make(move(computer, 1, 1));
        ticTacToe.make(move(person, 0, 0));
        ticTacToe.make(move(computer, 0, 2));
        ticTacToe.make(move(person, 0, 1));
        ticTacToe.make(move(computer, 1, 2));

        assertTrue(ticTacToe.calculateScore(computer) > 110);
        assertTrue(ticTacToe.calculateScore(computer) < 200);
    }

    @Test
    public void playerWithA_ThreeInARow_MoreThan200() {
        ticTacToe.make(move(computer, 1, 1));
        ticTacToe.make(move(person, 0, 0));
        ticTacToe.make(move(computer, 0, 2));
        ticTacToe.make(move(person, 0, 1));
        ticTacToe.make(move(computer, 1, 2));
        ticTacToe.make(move(person, 2, 0));
        ticTacToe.make(move(computer, 1, 0));

        assertTrue(ticTacToe.calculateScore(computer) > 200);
    }
}
