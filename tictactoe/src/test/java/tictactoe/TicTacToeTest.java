package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TicTacToeTest {
    private TicTacToe ticTacToe;
    private Player person;
    private Player computer;

    @Before
    public void setupGame() {
        ticTacToe = new TicTacToe();
        person = ticTacToe.getSecondPlayer();
        computer = ticTacToe.getFirstPlayer();
    }

    @Test
    public void shouldAlternateFirstTurnBetweenFirstAndSecondPlayer() {
        assertThat(ticTacToe.getNextTurn(), is(Player.O));
        ticTacToe.reset();
        assertThat(ticTacToe.getNextTurn(), is(Player.X));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        ticTacToe.makeMove(computer, 0, 0);
        ticTacToe.makeMove(person, 0, 0);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowOutOfTurnMove() {
       assertThat(ticTacToe.getNextTurn(), is(Player.O));
       ticTacToe.makeMove(Player.X, 0, 0);
    }

    @Test
    public void playerWithMultipleOneInARowHasScoreMoreThan10AndLessThan100() {
        ticTacToe.makeMove(computer, 1, 1);
        assertTrue(ticTacToe.calcluateScore(computer) > 10);
        assertTrue(ticTacToe.calcluateScore(computer) < 100);
    }

    @Test
    public void oppositionWithMultipleOneInARowHasScoreLessThan10AndMoreThan100() {
        ticTacToe.makeMove(computer, 1, 1);
        System.out.println(ticTacToe.calcluateScore(person));
        assertTrue(ticTacToe.calcluateScore(person) < -10);
        assertTrue(ticTacToe.calcluateScore(person) > -100);
    }

    @Test
    public void playerWithASingle_OneInARow_HasScoreMoreThan1AndLessThan10() {
        ticTacToe.makeMove(computer, 1, 1);
        ticTacToe.makeMove(person, 0, 0);

        assertTrue(ticTacToe.calcluateScore(computer) > 1);
        assertTrue(ticTacToe.calcluateScore(computer) < 10);
    }

    @Test
    public void playerWithASingle_TwoInARow_HasScoreMoreThan100AndLessThan110() {
        ticTacToe.makeMove(computer, 1, 1);
        ticTacToe.makeMove(person, 0, 0);
        ticTacToe.makeMove(computer, 0, 1);

        assertTrue(ticTacToe.calcluateScore(computer) > 100);
        assertTrue(ticTacToe.calcluateScore(computer) < 110);
    }

    @Test
    public void playerWithAMultiple_TwoInARow_HasScoreMoreThan110AndLessThan200() {
        ticTacToe.makeMove(computer, 1, 1);
        ticTacToe.makeMove(person, 0, 0);
        ticTacToe.makeMove(computer, 0, 2);
        ticTacToe.makeMove(person, 0, 1);
        ticTacToe.makeMove(computer, 1, 2);

        assertTrue(ticTacToe.calcluateScore(computer) > 110);
        assertTrue(ticTacToe.calcluateScore(computer) < 200);
    }

    @Test
    public void playerWithA_ThreeInARow_MoreThan200() {
        ticTacToe.makeMove(computer, 1, 1);
        ticTacToe.makeMove(person, 0, 0);
        ticTacToe.makeMove(computer, 0, 2);
        ticTacToe.makeMove(person, 0, 1);
        ticTacToe.makeMove(computer, 1, 2);
        ticTacToe.makeMove(person, 2, 0);
        ticTacToe.makeMove(computer, 1, 0);

        assertTrue(ticTacToe.calcluateScore(computer) > 200);
    }
}
