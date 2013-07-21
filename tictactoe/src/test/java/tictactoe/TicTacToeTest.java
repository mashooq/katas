package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tictactoe.GameBoard.newEmptyGrid;
import static tictactoe.Player.Mark.X;
import static tictactoe.Player.Mark._;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {
    @Mock Player player1;
    @Mock Player player2;
    @Mock GameBoard gameBoard;
    @Mock CommandPrompt commandPrompt;
    private TicTacToe ticTacToe;

    @Before
    public void setup() {
        given(gameBoard.cloneCurrentGrid()).willReturn(newEmptyGrid());
        ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);
    }

    @Test
    public void shouldPlayGameUntilAPlayerWins() {
        given(gameBoard.findWinner()).willReturn(_).willReturn(X);

        ticTacToe.start();

        verify(player1).makeMove(gameBoard);
        verify(commandPrompt).announceWinner(X);
    }

    @Test
    public void shouldPlayGameUntilItsADraw() {
        given(gameBoard.findWinner()).willReturn(_);

        ticTacToe.start();

        verify(commandPrompt).announceDraw();
    }

    @Test
    public void asksToPlayAgainOnceTheGameIsOver() {
        given(gameBoard.findWinner()).willReturn(_).willReturn(X);
        given(commandPrompt.askToPlayAgain()).willReturn(true).willReturn(false);

        ticTacToe.start();

        verify(gameBoard, times(1)).reset();
    }
}
