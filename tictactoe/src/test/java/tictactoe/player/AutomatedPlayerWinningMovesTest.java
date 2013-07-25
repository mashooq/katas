package tictactoe.player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tictactoe.game.GameBoard;
import tictactoe.game.LineGenerator;
import tictactoe.game.Mark;
import tictactoe.game.Move;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;
import static tictactoe.game.Mark.*;
import static tictactoe.game.Move.move;

@RunWith(Parameterized.class)
public class AutomatedPlayerWinningMovesTest {
    static final Mark myMark = X;

    Mark[][] gameInProgress;
    Move expectedNextMove;
    Move actualMove;

    GameBoard gameBoard;
    Player player;

    @Parameters
    public static Collection<Object[]> expectedMoveForAnInProgressGame() {
        return Arrays.asList(new Object[][] {
                {new Mark[][] { {O, _, X},
                                {_, _, X},
                                {O, _, _} }, move(myMark, 2, 2)},
                {new Mark[][] { {X, _, X},
                                {_, O, O},
                                {X, O, _} }, move(myMark, 0, 1)},
                {new Mark[][] { {O, X, O},
                                {_, _, _},
                                {O, X, _} }, move(myMark, 1, 1)},
                {new Mark[][] { {_, _, _},
                                {_, O, _},
                                {X, _, X} }, move(myMark, 2, 1)},
                {new Mark[][] { {O, _, X},
                                {O, X, _},
                                {_, _, _} }, move(myMark, 2, 0)}
        });
    }

    public AutomatedPlayerWinningMovesTest(Mark[][] gameInProgress, Move expectedNextMove) {
        this.gameInProgress = gameInProgress;
        this.expectedNextMove = expectedNextMove;
    }

    @Before
    public void setupBoardAndPlayer() {
        LineGenerator lineGenerator = new LineGenerator();
        gameBoard = new GameBoard(lineGenerator) {
            public Mark[][] cloneCurrentGrid() { return gameInProgress; }
            public void make(Move move) { actualMove = move; }
        };

        player = new AutomatedPlayer(myMark, lineGenerator);
    }

    @Test
    public void shouldMakeAWinningMoveWhenOneIsAvailable() {
        player.makeMove(gameBoard);
        assertThat(actualMove, is(expectedNextMove));
    }
}
