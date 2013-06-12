package tictactoe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static tictactoe.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class InteractivePlayerTest {
    @Mock
    GameBoard gameBoard;

    @Mock
    Player player;

    @Test
    public void displaysTheCurrentStateOfTheBoard() {
        Collection<Move> playedMoves = new ArrayList<Move>();
        playedMoves.add(move(player, 0, 0));
        playedMoves.add(move(player, 1, 0));
        given(gameBoard.getPlayedMoves(player)).willReturn(playedMoves);
        given(player.getSymbol()).willReturn("X");


        StringBuilder currentBoard = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (col != 0) currentBoard.append("|");
                Move move = findMove(playedMoves, col, row);
                if (move == null) {
                    currentBoard.append((row * 3) + (col+1));
                } else {
                    currentBoard.append(player.getSymbol());
                }
            }
            if (row < 2) currentBoard.append("\n");
        }

        String expectedBoard = "X|2|3\nX|5|6\n7|8|9";
        assertThat(currentBoard.toString(), is(expectedBoard));
    }

    private Move findMove(Collection<Move> playedMoves, int col, int row) {
        for (Move move : playedMoves) {
            if (move.getCol() == col && move.getRow() == row) {
                return move;
            }
        }

        return null;
    }
}
