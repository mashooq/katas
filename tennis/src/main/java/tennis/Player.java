package tennis;

import static tennis.Score.LOVE;

public class Player {

    private Score score = LOVE;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
