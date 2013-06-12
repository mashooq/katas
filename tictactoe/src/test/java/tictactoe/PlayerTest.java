package tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {
    public static final String PLAYER_X = "Player X";

    @Test
    public void shouldGetThePlayersSymbol() {
        Player player = new Player(PLAYER_X) {
            @Override
            public void takeTurn(GameBoard gameBoard) {}
        };

        assertThat(player.getSymbol(), is(PLAYER_X));
    }
}
