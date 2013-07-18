package tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static tictactoe.Player.Mark;
import static tictactoe.Player.Mark.X;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {
    @Mock Player player1;
    @Mock Player player2;
    @Mock GameBoard gameBoard;
    @Mock CommandPrompt commandPrompt;

    @Test
    public void shouldPlayGameUntilAPlayerWins() {
        given(gameBoard.make(any(Move.class))).willReturn(gameBoard);
        given(gameBoard.cloneCurrentGrid()).willReturn(new Mark[3][3]);
        given(gameBoard.findWinner()).willReturn(null).willReturn(X);
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);

        ticTacToe.start();

        verify(player1).chooseMove(isA(Mark[][].class));
        verify(commandPrompt).announceWinner(X);
    }

    @Test
    public void shouldPlayGameUntilItsADraw() {
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);
        given(gameBoard.make(any(Move.class))).willReturn(gameBoard);
        given(gameBoard.cloneCurrentGrid()).willReturn(new Mark[3][3]);
        given(gameBoard.make(isA(Move.class))).willReturn(gameBoard);
        given(gameBoard.findWinner()).willReturn(null);

        ticTacToe.start();

        verify(commandPrompt).announceDraw();
    }

    @Test
    public void promptsUserAgainToTryAgainInCaseTheMoveIsIllegal() {
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);
        doThrow(IllegalArgumentException.class).doReturn(gameBoard).when(gameBoard).make(any(Move.class));
        given(gameBoard.findWinner()).willReturn(X);

        ticTacToe.start();

        verify(commandPrompt).tryAgain();
    }
}
