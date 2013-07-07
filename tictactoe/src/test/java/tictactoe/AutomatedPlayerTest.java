package tictactoe;

import org.junit.Before;
import org.junit.Ignore;
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

        Move move = player.takeTurn(gameGrid);

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

        Move move = player.takeTurn(gameGrid);

        assertThat(move, isIn(furthestCorner()));
    }

    @Test
    public void givenOppositionIsAboutToWin_IStealTheirWinningPosition() {
        Mark[][] gameGrid = {
                {X, null, null},
                {X, O, null},
                {null, null, O}
        };

        Move move = player.takeTurn(gameGrid);

        assertThat(move, is(move(X, 2, 0)));
    }

    private Move[] furthestCorner() {
        return new Move[]{
                move(X,0,2),
                move(X,2,2),
        };
    }
}
