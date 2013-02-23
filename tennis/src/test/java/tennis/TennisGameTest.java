package tennis;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tennis.Score.*;

public class TennisGameTest {
    private Player playerOne;
    private Player playerTwo;
    private TennisGame tennisGame;

    @Before
    public void setUp() {
        playerOne = new Player();
        playerTwo = new Player();
        tennisGame = new TennisGame(playerOne, playerTwo);
    }

    @Test
    public void whenAPlayer1andPlayer2ScoreOnceThenScoreIs_15_15() {
        scores(playerOne, 1);
        scores(playerTwo, 1);

        assertThat(playerOne.getScore(), is(FIFTEEN));
        assertThat(playerTwo.getScore(), is(FIFTEEN));
    }

    private void scores(Player player, int times) {
        for (int i=0; i<times; i++) {
            tennisGame.scores(player);
        }
    }

    @Test
    public void whenAPlayer1ScoresOncesandPlayer2ScoreTwiceThenScoreIs_30_15() {
        scores(playerOne, 1);
        scores(playerTwo, 2);

        assertThat(playerOne.getScore(), is(FIFTEEN));
        assertThat(playerTwo.getScore(), is(THIRTY));
    }

    @Test
    public void whenAPlayerIsAt40AndThatPlayerScoresThenTheyWin() {
        scores(playerOne, 3);
        scores(playerTwo, 2);
        scores(playerOne, 1);

        assertThat(tennisGame.winner(), is(playerOne));
    }

    @Test
    public void whenAPlayerIsOn30AndTheOtherAt40AndFirstPlayerScoresThenScoreIsDeuce() {
        scores(playerOne, 2);
        scores(playerTwo, 3);
        scores(playerOne, 1);

        assertThat(playerOne.getScore(), is(DEUCE));
        assertThat(playerTwo.getScore(), is(DEUCE));
    }

    @Test
    public void whenScoreIsAtDeuceAndAPlayerScoresThenThePlayerHasAdvantage() {
        scores(playerOne, 3);
        scores(playerTwo, 3);
        scores(playerOne, 1);

        assertThat(playerOne.getScore(), is(ADVANTAGE));
        assertThat(playerTwo.getScore(), is(DEUCE));
    }
}
