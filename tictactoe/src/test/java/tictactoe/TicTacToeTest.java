package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tictactoe.TicTacToe.Player.O;

public class TicTacToeTest {

    private TicTacToe ticTacToe;

    @Before
    public void setupGame() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void shouldAlternateSymbolsBetweenComputerAndPerson() {
        assertThat(ticTacToe.getComputerSymbol(), is(O));
        ticTacToe.reset();
        assertThat(ticTacToe.getPersonSymbol(), is(O));
    }

    @Test
    public void shouldAlternateFirstTurnBetweenComputerAndPerson() {
        assertThat(ticTacToe.isGameBoardEmpty(), is(false));
        ticTacToe.reset();
        assertThat(ticTacToe.isGameBoardEmpty(), is(true));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        ticTacToe.makeMove(1, 1);
    }

    @Test
    public void shouldBeAbleToCalculateWinningPositions() {
        ticTacToe.makeMove(0,0);
        ticTacToe.makeMove(0,1);

        TicTacToe.Player person = ticTacToe.getPersonSymbol();
        TicTacToe.Player computer = ticTacToe.getComputerSymbol();

        assertThat(ticTacToe.calculateWinningPositions(person), is(4));
        assertThat(ticTacToe.calculateWinningPositions(computer), is(4));
    }
}
