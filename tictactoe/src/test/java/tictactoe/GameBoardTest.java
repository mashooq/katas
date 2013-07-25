package tictactoe;

import org.junit.Before;
import org.junit.Test;
import tictactoe.game.GameBoard;
import tictactoe.game.Mark;
import tictactoe.game.Move;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static tictactoe.game.Mark._;
import static tictactoe.game.Move.move;

public class GameBoardTest {
    private GameBoard gameBoard;
    private Mark person = Mark.O;
    private Mark computer = Mark.X;

    @Before
    public void setupGame() {
        gameBoard = new GameBoard();
        gameBoard.reset();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        gameBoard.make(computerMove(0, 0));
        gameBoard.make(personMove(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowAMoveToAnUnknownPosition() {
        gameBoard.make(computerMove(3, 0));
    }

    @Test
    public void shouldFindTheWinnerIfOneExists() {
        prepareBoardWhereComputerHasWon();
        assertThat(gameBoard.findWinner(), is(computer));
    }

    @Test
    public void shouldReturnBlankIfNoWinnerExists() {
        assertThat(gameBoard.findWinner(), is(_));
    }

    private void prepareBoardWhereComputerHasWon() {
        gameBoard.make(computerMove(0, 0));
        gameBoard.make(computerMove(1, 1));
        gameBoard.make(computerMove(2, 2));
    }

    private Move computerMove(int row, int col) {
        return move(computer, row, col);
    }

    private Move personMove(int row, int col) {
        return move(person, row, col);
    }
}
