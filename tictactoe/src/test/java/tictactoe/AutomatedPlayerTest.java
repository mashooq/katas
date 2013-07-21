package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.GameBoard.newEmptyGrid;
import static tictactoe.Move.move;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark._;
import static tictactoe.Player.Mark.O;
import static tictactoe.Player.Mark.X;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    @Mock GameBoard gameBoard;
    private AutomatedPlayer player;

    @Before
    public void setup() {
        player = new AutomatedPlayer(X);
    }

    @Test
    public void givenAnEmptyBoardIChooseTheSafeMove() {
        given(gameBoard.cloneCurrentGrid()).willReturn(newEmptyGrid());

        player.makeMove(gameBoard);

        assertThat(actualMove(), isIn(safeFirstMoves()));
    }

    @Test
    public void givenIMovedInCenterAndOppositionMadeAnEdgeMove_MyNextMoveIsFurthestCorner() {
        Mark[][] gameGrid = new Mark[][]{
                {_, _, _},
                {O, X, _},
                {_, _, _}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        assertThat(actualMove(), isIn(oneOfTheFurthestCorners()));
    }

    @Test
    public void givenOppositionIsAboutToWin_IStealTheirWinningPosition() {
        Mark[][] gameGrid = new Mark[][]{
                {O, _, _},
                {O, X, _},
                {_, _, X}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        assertThat(actualMove(), is(move(X, 2, 0)));
    }

    @Test
    public void givenIAmAboutToWin_IPlayTheWinningMove() {
        Mark[][] gameGrid = new Mark[][]{
                {O, _, _},
                {O, X, _},
                {_, X, _}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        assertThat(actualMove(), is(theWinningMove()));
    }

    @Test
    public void favoursTheCenterCell() {
        Mark[][] gameGrid = new Mark[][]{
                {_, _, _},
                {O, _, _},
                {_, _, _}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        assertThat(actualMove(), is(centerCell()));
    }

    private Move[] safeFirstMoves() {
        return new Move[]{
                move(X,0,0), move(X,0,2),
                move(X,1,1),
                move(X,2,0), move(X,2,2),
        };
    }

    private Move actualMove() {
        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        return argumentCaptor.getValue();
    }

    private Move centerCell() {
        return move(X,1,1);
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
