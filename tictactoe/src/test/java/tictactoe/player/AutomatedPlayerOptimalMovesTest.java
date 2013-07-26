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
public class AutomatedPlayerOptimalMovesTest {
    Mark[][] gameInProgress;
    Move expectedNextMove;
    Move actualMove;

    GameBoard gameBoard;
    Player player;

    @Parameters
    public static Collection<Object[]> expectedMoveForAnInProgressGame() {
        return Arrays.asList(new Object[][]{
                {new Mark[][]{{_, _, _},
                              {_, _, _},
                              {_, _, _}}, centerMove()},

                {new Mark[][]{{_, O, _},
                              {_, _, _},
                              {_, _, _}}, centerMove()},

                {new Mark[][]{{_, _, _},
                              {_, _, _},
                              {_, O, _}}, centerMove()},

                {new Mark[][]{{_, _, _},
                              {_, _, O},
                              {_, _, _}}, centerMove()},

                {new Mark[][]{{_, _, _},
                              {O, _, _},
                              {_, _, _}}, centerMove()},

                {new Mark[][]{{_, _, _},
                              {_, O, _},
                              {_, _, _}}, move(X, 0, 0)},

                {new Mark[][]{{_, O, _},
                              {_, X, _},
                              {_, _, _}}, move(X, 1, 0)},

                {new Mark[][]{{X, O, _},
                              {_, X, _},
                              {_, _, O}}, move(X, 2, 0)},

                {new Mark[][]{{_, O, _},
                              {O, _, X},
                              {X, _, _}}, centerMove()},

                {new Mark[][]{{X, _, _},
                              {O, _, O},
                              {X, _, _}}, centerMove()}
        });
    }

    public AutomatedPlayerOptimalMovesTest(Mark[][] gameInProgress, Move expectedNextMove) {
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
    public void shouldMakeMovesWithBestChanceForAWin() {
        player.makeMove(gameBoard);
        assertThat(actualMove, is(expectedNextMove));
    }

    private static Move centerMove() {
        return move(X, 1, 1);
    }
}
