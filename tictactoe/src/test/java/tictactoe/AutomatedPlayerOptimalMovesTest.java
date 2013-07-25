package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tictactoe.game.GameBoard;
import tictactoe.game.Mark;
import tictactoe.game.Move;
import tictactoe.player.AutomatedPlayer;
import tictactoe.player.Player;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;
import static tictactoe.game.Mark.*;
import static tictactoe.game.Move.move;

@RunWith(Parameterized.class)
public class AutomatedPlayerOptimalMovesTest {
    static final Mark myMark = X;

    Mark[][] gameInProgress;
    Move[] expectedNextMove;
    Move actualMove;

    GameBoard gameBoard;
    Player player;

    @Parameters
    public static Collection<Object[]> expectedMoveForAnInProgressGame() {
        return Arrays.asList(new Object[][] {
                {new Mark[][] { {_, _, _},
                                {_, _, _},
                                {_, _, _} }, safeFirstMoves()},
                {new Mark[][] { {_, O, _},
                                {O, _, X},
                                {X, _, _} }, moves(move(myMark, 0, 2))},
                {new Mark[][] { {X, _, _},
                                {O, _, O},
                                {X, _, _} }, moves(move(myMark, 1, 1))}
        });
    }

    public AutomatedPlayerOptimalMovesTest(Mark[][] gameInProgress, Move[] expectedNextMove) {
        this.gameInProgress = gameInProgress;
        this.expectedNextMove = expectedNextMove;
    }

    @Before
    public void setupBoardAndPlayer() {
        gameBoard = new GameBoard() {
            public Mark[][] cloneCurrentGrid() { return gameInProgress; }
            public void make(Move move) { actualMove = move; }
        };

        player = new AutomatedPlayer(myMark);
    }

    @Test
    public void shouldMakeMovesWithBestChanceForAWin() {
        player.makeMove(gameBoard);
        assertThat(actualMove, isIn(expectedNextMove));
    }

    private static Move[] safeFirstMoves() {
       return moves(
                move(myMark, 0, 0), move(myMark, 0, 2),
                move(X, 1, 1),
                move(X, 2, 0), move(X, 2, 2)
       );
    }

    private static Move[] moves(Move ... listOfMoves) {
        return listOfMoves;
    }
}
