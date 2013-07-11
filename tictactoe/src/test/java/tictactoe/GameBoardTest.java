package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark;

public class GameBoardTest {
    private GameBoard gameBoard;
    private Mark person = Mark.O;
    private Mark computer = Mark.X;

    @Before
    public void setupGame() {
        gameBoard = new GameBoard();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        gameBoard.make(computerMove(0, 0)).make(personMove(0, 0));
    }

    @Test
    public void shouldFindTheWinnerIfOneExists() {
        assertThat(makeWinningMovesForComputer().findWinner(), is(computer));
    }

    @Test
    public void shouldReturnNullIfNoWinnerExists() {
        assertThat(gameBoard.findWinner(), is(nullValue()));
    }

    @Test
    public void shouldKeepInstanceOfGameBoardImmutable() {
        GameBoard newGameBoard = gameBoard.make(computerMove(0, 0));
        assertThat(newGameBoard, is(not(gameBoard)));
    }

    private GameBoard makeWinningMovesForComputer() {
        return gameBoard.make(computerMove(0, 0))
                .make(computerMove(1, 1))
                .make(computerMove(2, 2));
    }

    private Move computerMove(int row, int col) {
        return move(computer, row, col);
    }

    private Move personMove(int row, int col) {
        return move(person, row, col);
    }
}
