package tictactoe;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tictactoe.Move.move;

public class GameBoardTest {
    private GameBoard gameBoard;
    private Player person;
    private Player computer;

    private Move personMove;
    private Move computerMove;

    @Before
    public void setupGame() {
        person = createPersonPlayer();
        computer = createComputerPlayer();
        gameBoard = new GameBoard();
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotAllowAMoveToAlreadyOccupiedPlace() {
        computerMoves(0, 0);
        personMoves(0, 0);
    }

    @Test
    public void playerWithMultipleOneInARowHasScoreBetween10And100() {
        computerMoves(1, 1);
        assertThat(gameBoard.calculateScore(computer), isBetween(10, 100));
    }

    @Test
    public void oppositionWithMultipleOneInARowHasScoreBetweenMinus10AndMinus100() {
        personMoves(1, 1);
        assertThat(gameBoard.calculateScore(computer), isBetween(-100, -10));
    }

    @Test
    public void playerWithASingle_OneInARow_HasScoreBetween1And10() {
        computerMoves(1, 1);
        personMoves(0, 0);

        assertThat(gameBoard.calculateScore(computer), isBetween(1, 10));
    }

    @Test
    public void playerWithASingle_TwoInARow_HasScoreMoreThan100AndLessThan110() {
        computerMoves(1, 1);
        personMoves(0, 0);
        computerMoves(0, 1);

        assertThat(gameBoard.calculateScore(computer), isBetween(100, 110));
    }

    @Test
    public void playerWithAMultiple_TwoInARow_HasScoreMoreThan110AndLessThan200() {
        computerMoves(1, 1);
        personMoves(0, 0);
        computerMoves(0, 2);
        personMoves(0, 1);
        computerMoves(1, 2);

        assertThat(gameBoard.calculateScore(computer), isBetween(110, 200));
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

        assertThat(gameBoard.calculateScore(computer), isGreaterThan(200));
    }

    @Test
    public void canCalculateAvailableMovesWithAssociatedScores() {
        Collection<Move> futureMoves = gameBoard.getAvailableMovesWithScores(computer);
        assertThat(futureMoves.size(), is(9));
    }

    @Test
    public void canCalculatePlayedMoves() {
        personMoves(0, 0);
        computerMoves(1, 1);
        Collection<Move> futureMoves = gameBoard.getPlayedMoves(computer);
        assertThat(futureMoves.size(), is(2));
    }

    private void computerMoves(int row, int col) {
        computerMove = move(computer, row, col);
        computer.takeTurn(gameBoard);
    }

    private void personMoves(int row, int col) {
        personMove = move(person, row, col);
        person.takeTurn(gameBoard);
    }

    private Player createComputerPlayer() {
        return new Player("Player Y") {
            @Override
            public void takeTurn(GameBoard gameBoard) {
                gameBoard.make(computerMove);
            }
        };
    }

    private Player createPersonPlayer() {
        return new Player("Player X") {
            @Override
            public void takeTurn(GameBoard gameBoard) {
                gameBoard.make(personMove);
            }
        };
    }

    private Matcher<Integer> isGreaterThan(final Integer lowerValue) {
        return new BaseMatcher<Integer>() {
            @Override
            public boolean matches(final Object value) {
                final Integer score = (Integer) value;
                return score > lowerValue;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("value greather than ").appendValue(lowerValue);
            }
        };
    }

    private Matcher<Integer> isBetween(final Integer lowest, final Integer highest) {
        return new BaseMatcher<Integer>() {
            @Override
            public boolean matches(final Object value) {
                final Integer score = (Integer) value;
                return score > lowest && score < highest;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("value between <" + lowest + " and " + highest + ">");
            }
        };
    }
}
