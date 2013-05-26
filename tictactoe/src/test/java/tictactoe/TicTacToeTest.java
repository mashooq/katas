package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tictactoe.TicTacToe.Player;
import static tictactoe.TicTacToe.Player.O;
import static tictactoe.TicTacToe.Player.X;

public class TicTacToeTest {

    private TicTacToe ticTacToe;

    @Before
    public void setupGame() {
       ticTacToe = new TicTacToe();
    }

    @Test
    public void shouldCreateAGameWithAnEmptyGrid() {
        assertThat(ticTacToe.isGameBoardEmpty(), is(true));
    }

    @Test
    public void shouldAlternateTheFirstGameBetweenPlayers() {
       assertThat(ticTacToe.getCurrentPlayer(), is(O));
       ticTacToe.reset();
       assertThat(ticTacToe.getCurrentPlayer(), is(X));
    }

    @Test
    public void shouldMakeFistMoveWithMaximumWinningPositions() {
        Player currentPlayer = ticTacToe.getCurrentPlayer();
        ticTacToe.makeNextMove();
        assertThat(ticTacToe.possibleWinningPositionsFor(currentPlayer), is(4));
    }

    @Test
    public void shouldMakeSecondMoveWithMaximumPossibleWinningPositions() {
        ticTacToe.makeNextMove();
        Player currentPlayer = ticTacToe.getCurrentPlayer();
        ticTacToe.makeNextMove();
        assertThat(ticTacToe.possibleWinningPositionsFor(currentPlayer), is(2));
    }
}
