package tennis;

import static tennis.Score.*;

public class TennisGame {
    private final Player playerOne;
    private final Player playerTwo;

    public TennisGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player winner() {
        if (playerOne.getScore() == WIN) return playerOne;
        else if (playerTwo.getScore() == WIN) return playerTwo;
        else return null;
    }

    public void scores(Player player) {
        switch (player.getScore()) {
            case LOVE:
                player.setScore(FIFTEEN);
                break;
            case FIFTEEN:
                player.setScore(THIRTY);
                break;
            case THIRTY:
                determineFortyOrDeuce(player);
                break;
            case DEUCE:
                determineAdvantageOrDeuce(player);
                break;
            case FORTY:
            case ADVANTAGE:
                player.setScore(WIN);
        }
    }

    private void determineAdvantageOrDeuce(Player player) {
        Player opponent = getOpponent(player);
        if (opponent.getScore() == ADVANTAGE) {
            opponent.setScore(DEUCE);
        } else {
            player.setScore(ADVANTAGE);
        }
    }

    private void determineFortyOrDeuce(Player player) {
        Player opponent = getOpponent(player);
        if (opponent.getScore() == FORTY) {
            player.setScore(DEUCE);
            opponent.setScore(DEUCE);
        } else {
            player.setScore(FORTY);
        }
    }

    private Player getOpponent(Player player) {
        return player == playerOne ? playerTwo : playerOne;
    }
}
