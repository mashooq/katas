package tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static tictactoe.Move.move;

@RunWith(MockitoJUnitRunner.class)
public class AutomatedPlayerTest {
    public static final String PLAYER_X = "player x";
    @Mock
    GameBoard gameBoard;
    private AutomatedPlayer player;

    @Before
    public void setupPlayer() {
        player = new AutomatedPlayer(PLAYER_X);
    }


    @Test
    public void willTakeTurnWithTheHighestScore() {
        List<Move> movesWithScore =
                new ArrayList<Move>() {{
                    add(move(player, 0, 0).withScore(30));
                    add(move(player, 1, 1).withScore(40));
                }};

        Move firstMoveWithHighestScore = move(player, 1, 1);
        given(gameBoard.getAvailableMovesWithScores(player)).willReturn(movesWithScore);

        player.takeTurn(gameBoard);

        verify(gameBoard).make(firstMoveWithHighestScore);
    }
}
