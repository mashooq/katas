package tictactoe.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.player.Player;
import tictactoe.ui.Prompt;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static tictactoe.game.Mark.X;
import static tictactoe.game.Mark._;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {
    @Mock Player player1;
    @Mock Player player2;
    @Mock GameBoard gameBoard;
    @Mock Prompt prompt;
    private TicTacToe ticTacToe;

    @Before
    public void setup() {
        ticTacToe = new TicTacToe(player1, player2, gameBoard, prompt);
    }

    @Test
    public void shouldPlayGameUntilAPlayerWins() {
        given(gameBoard.findWinner()).willReturn(_).willReturn(X);

        ticTacToe.start();

        verify(player1).makeMove(gameBoard);
        verify(prompt).announceWinner(X);
    }

    @Test
    public void shouldPlayGameUntilItsADraw() {
        given(gameBoard.findWinner()).willReturn(_);

        ticTacToe.start();

        verify(prompt).announceDraw();
    }

    @Test
    public void asksToPlayAgainOnceTheGameIsOver() {
        given(gameBoard.findWinner()).willReturn(_).willReturn(X);
        given(prompt.askToPlayAgain()).willReturn(true).willReturn(false);

        ticTacToe.start();

        verify(gameBoard, times(1)).reset();
    }
}
