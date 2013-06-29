package tddfirststeps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static tddfirststeps.GameBoard.PLAYER_1;
import static tddfirststeps.GameBoard.PLAYER_2;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardTest {
    @Mock Display display;

    @Test
    public void playerOneServesFirst() {
       new GameBoard(display);
       verify(display).display(eq(PLAYER_1), anyString());
    }

    @Test
    public void whenGameStartsBothPlayersHaveZeroScore() {
        new GameBoard(display);
        verify(display).display(anyString(), eq(PLAYER_1 + ": 0, " + PLAYER_2 + ": 0"));
    }

    @Test
    public void whenServingPlayerWinsARallyTheyWinAPoint() {
        GameBoard gameBoard = new GameBoard(display);
        gameBoard.winsRally(PLAYER_1);
        verify(display).display(anyString(), eq(PLAYER_1 + ": 1, " + PLAYER_2 + ": 0"));
    }

    @Test
    public void whenReceivingPlayerWinsTheRallyTheyWinTheServe() {
        GameBoard gameBoard = new GameBoard(display);
        gameBoard.winsRally(PLAYER_2);
        verify(display).display(eq(PLAYER_2), anyString());
    }

}
