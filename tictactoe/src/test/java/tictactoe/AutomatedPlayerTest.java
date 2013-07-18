package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark.O;
import static tictactoe.Player.Mark.X;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    @Mock GameBoard gameBoard;
    private AutomatedPlayer player;

    @Before
    public void setupPlayer() {
        player = new AutomatedPlayer(X);
    }

    @Test
    public void givenAnEmptyBoardIChooseTheSafeMove() {
        Mark[][] gameGrid = new Mark[3][3];

        Move move = player.chooseMove(gameGrid);

        assertThat(move, isIn(safeFirstMoves()));
    }

    private Move[] safeFirstMoves() {
        return new Move[]{
                move(X,0,0), move(X,0,2),
                move(X,1,1),
                move(X,2,0), move(X,2,2),
        };
    }

    @Test
    public void givenIMovedInCenterAndOppositionMadeAnEdgeMove_MyNextMoveIsFurthestCorner() {
        Mark[][] gameGrid = {
                {null, null, null},
                {O, X, null},
                {null, null, null}
        };

        Move move = player.chooseMove(gameGrid);

        assertThat(move, isIn(oneOfTheFurthestCorners()));
    }

    @Test
    public void givenOppositionIsAboutToWin_IStealTheirWinningPosition() {
        Mark[][] gameGrid = {
                {O, null, null},
                {O, X, null},
                {null, null, X}
        };

        Move move = player.chooseMove(gameGrid);

        assertThat(move, is(move(X, 2, 0)));
    }

    @Test
    public void givenIAmAboutToWin_IPlayTheWinningMove() {
        Mark[][] gameGrid = {
                {O, null, null},
                {O, X, null},
                {null, X, null}
        };

        Move move = player.chooseMove(gameGrid);

        assertThat(move, is(theWinningMove()));
    }

    private Move theWinningMove() {
        return move(X, 0, 1);
    }

    private Move[] oneOfTheFurthestCorners() {
        return new Move[]{
                move(X,0,2),
                move(X,2,2),
        };
    }
}
