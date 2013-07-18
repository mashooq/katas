package tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
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
        given(gameBoard.cloneCurrentGrid()).willReturn(new Mark[3][3]);
        given(gameBoard.findWinner()).willReturn(null).willReturn(X);
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);

        ticTacToe.start();

        verify(player1).makeMove(gameBoard);
        verify(commandPrompt).announceWinner(X);
    }

    @Test
    public void shouldPlayGameUntilItsADraw() {
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, commandPrompt);
        given(gameBoard.cloneCurrentGrid()).willReturn(new Mark[3][3]);
        given(gameBoard.findWinner()).willReturn(null);

        ticTacToe.start();

        verify(commandPrompt).announceDraw();
    }
}
