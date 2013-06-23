package tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {
    public static final String SYMBOL_X = "X";

    @Mock
    Player player1;
    @Mock
    Player player2;
    @Mock
    GameBoard gameBoard;
    @Mock
    PrintStream writer;

    @Test
    public void shouldPlayGameUntilAPlayerWins() {
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, writer);
        given(gameBoard.calculateScore(player1)).willReturn(10).willReturn(20);
        given(gameBoard.calculateScore(player2)).willReturn(10).willReturn(300);
        given(gameBoard.isADraw()).willReturn(false);
        given(player2.getSymbol()).willReturn(SYMBOL_X);

        ticTacToe.start();

        verify(writer).println(SYMBOL_X + " wins");
    }

    @Test
    public void shouldPlayGameUntilItsADraw() {
        TicTacToe ticTacToe = new TicTacToe(player1, player2, gameBoard, writer);
        given(gameBoard.calculateScore(player1)).willReturn(10);
        given(gameBoard.calculateScore(player2)).willReturn(10);

        ticTacToe.start();

        verify(writer).println("It's a draw!");
    }
}
