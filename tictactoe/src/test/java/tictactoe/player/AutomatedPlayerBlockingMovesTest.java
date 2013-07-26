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
public class AutomatedPlayerBlockingMovesTest {
    Mark[][] gameInProgress;
    Move expectedNextMove;
    Move actualMove;

    GameBoard gameBoard;

    @Parameters
    public static Collection<Object[]> expectedMoveForAnInProgressGame() {
        return Arrays.asList(new Object[][] {
                {new Mark[][] { {O, X, X},
                                {_, O, O},
                                {_, X, _} }, move(X, 1, 0)},

                {new Mark[][] { {X, _, _},
                                {_, O, O},
                                {_, _, _} }, move(X, 1, 0)},

                {new Mark[][] { {X, _, _},
                                {_, O, O},
                                {_, _, _} }, move(X, 1, 0)},

                {new Mark[][] { {O, O, X},
                                {_, X, _},
                                {O, X, _} }, move(X, 1, 0)},

                {new Mark[][] { {_, _, _},
                                {_, X, _},
                                {O, _, O} }, move(X, 2, 1)},

                {new Mark[][] { {_, _, X},
                                {_, X, _},
                                {O, _, O} }, move(X, 2, 1)},

                {new Mark[][] { {O, _, _},
                                {O, X, _},
                                {_, _, _} }, move(X, 2, 0)}
        });
    }

    Player player;

    public AutomatedPlayerBlockingMovesTest(Mark[][] gameInProgress, Move expectedNextMove) {
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

        player = new AutomatedPlayer(X, lineGenerator);
    }

    @Test
    public void shouldMakeABlockingMoveWhenPlayerIsAboutToWin() {
        player.makeMove(gameBoard);
        assertThat(actualMove, is(expectedNextMove));
    }
}
