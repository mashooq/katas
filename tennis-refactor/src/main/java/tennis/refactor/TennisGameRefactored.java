package tennis.refactor;

public class TennisGameRefactored {
    private Player playerOne;
    private Player playerTwo;

    public TennisGameRefactored(String player1Name, String player2Name) {
        playerOne = new Player(player1Name);
        playerTwo = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        getPlayer(playerName).wonPoint();
    }

    private Player getPlayer(String playerName) {
        return playerOne.getName().equals(playerName) ? playerOne : playerTwo;
    }

    public String getScore() {
        String score;
        if (pointsAreEqual()) {
            int pointsDrawn = playerOne.getPoints();
            if (pointsDrawn <= 3) score = getScore(pointsDrawn) + "-All";
            else score = "Deuce";
        } else if (atLeastOnePlayerHasScoredFourOrMore()) {
            score = getResultAfterDeuce();
            score += getPlayerWithWinOrAdvantage();
        } else {
            score = getScore(playerOne.getPoints());
            score += "-" + getScore(playerTwo.getPoints());
        }
        return score;
    }

    private boolean atLeastOnePlayerHasScoredFourOrMore() {
        return playerOne.getPoints() >= 4 ||
                playerTwo.getPoints() >= 4;
    }

    private boolean pointsAreEqual() {
        return playerOne.getPoints() == playerTwo.getPoints();
    }

    private String getPlayerWithWinOrAdvantage() {
        return playerOne.getPoints() >= playerTwo.getPoints() ?
                playerOne.getName() : playerTwo.getName();
    }


    private String getResultAfterDeuce() {
        int resultDifference = Math.abs(playerOne.getPoints() - playerTwo.getPoints());
        return resultDifference < 2 ? "Advantage " : "Win for ";
    }

    private String getScore(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }
}
