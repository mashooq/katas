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
        given(gameBoard.cloneCurrentGrid()).willReturn(new Mark[3][3]);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), isIn(safeFirstMoves()));
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
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), isIn(oneOfTheFurthestCorners()));
    }

    @Test
    public void givenOppositionIsAboutToWin_IStealTheirWinningPosition() {
        Mark[][] gameGrid = {
                {O, null, null},
                {O, X, null},
                {null, null, X}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(move(X, 2, 0)));
    }

    @Test
    public void givenIAmAboutToWin_IPlayTheWinningMove() {
        Mark[][] gameGrid = {
                {O, null, null},
                {O, X, null},
                {null, X, null}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(theWinningMove()));
    }

    @Test
    public void favoursTheCenterCell() {
        Mark[][] gameGrid = {
                {null, null, null},
                {O, null, null},
                {null, null, null}
        };
        given(gameBoard.cloneCurrentGrid()).willReturn(gameGrid);

        player.makeMove(gameBoard);

        ArgumentCaptor<Move> argumentCaptor = ArgumentCaptor.forClass(Move.class);
        verify(gameBoard).make(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is((move(X,1,1))));
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
