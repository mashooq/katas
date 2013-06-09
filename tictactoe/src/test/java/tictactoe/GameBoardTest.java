package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static tictactoe.Move.move;

public class GameBoardTest {
    private GameBoard gameBoard;
    private Player person;
    private Player computer;

    private Move personMove;
    private Move computerMove;

    @Before
    public void setupGame() {
        person = new Player() {
            @Override
            public void takeTurn(GameBoard gameBoard) {
                gameBoard.make(personMove);
            }
        };

        computer = new Player() {
            @Override
            public void takeTurn(GameBoard gameBoard) {
                gameBoard.make(computerMove);
            }
        };

        gameBoard = new GameBoard();
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        computerMoves(0, 0);
        personMoves(0, 0);
    }

    private void computerMoves(int row, int col) {
        computerMove = move(computer, row, col);
        computer.takeTurn(gameBoard);
    }

    private void personMoves(int row, int col) {
        personMove = move(person, row, col);
        person.takeTurn(gameBoard);
    }

    @Test
    public void playerWithMultipleOneInARowHasScoreMoreThan10AndLessThan100() {
        computerMoves(1, 1);
        assertTrue(gameBoard.calculateScore(computer) > 10);
        assertTrue(gameBoard.calculateScore(computer) < 100);
    }

    @Test
    public void oppositionWithMultipleOneInARowHasScoreLessThan10AndMoreThan100() {
        computerMoves(1, 1);
        assertTrue(gameBoard.calculateScore(person) < -10);
        assertTrue(gameBoard.calculateScore(person) > -100);
    }

    @Test
    public void playerWithASingle_OneInARow_HasScoreMoreThan1AndLessThan10() {
        computerMoves(1, 1);
        personMoves(0, 0);

        assertTrue(gameBoard.calculateScore(computer) > 1);
        assertTrue(gameBoard.calculateScore(computer) < 10);
    }

    @Test
    public void playerWithASingle_TwoInARow_HasScoreMoreThan100AndLessThan110() {
        computerMoves(1, 1);
        personMoves(0, 0);
        computerMoves(0, 1);

        assertTrue(gameBoard.calculateScore(computer) > 100);
        assertTrue(gameBoard.calculateScore(computer) < 110);
    }

    @Test
    public void playerWithAMultiple_TwoInARow_HasScoreMoreThan110AndLessThan200() {
        computerMoves(1, 1);
        personMoves(0, 0);
        computerMoves(0, 2);
        personMoves(0, 1);
        computerMoves(1, 2);

        assertTrue(gameBoard.calculateScore(computer) > 110);
        assertTrue(gameBoard.calculateScore(computer) < 200);
    }

    @Test
    public void playerWithA_ThreeInARow_MoreThan200() {
        computerMoves(1, 1);
        personMoves(0, 0);
        computerMoves(0, 2);
        personMoves(0, 1);
        computerMoves(1, 2);
        personMoves(2, 0);
        computerMoves(1, 0);

        assertTrue(gameBoard.calculateScore(computer) > 200);
    }
}
