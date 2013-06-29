package tddfirststeps;

public class GameBoard {
    public static final String PLAYER_1 = "PLAYER_1";
    public static final String PLAYER_2 = "PLAYER_2";
    private int player1Score = 0;
    private int player2Score = 0;
    private String servingPlayer = PLAYER_1;
    private Display display;

    public GameBoard(Display display) {
        this.display = display;
        display.display(servingPlayer, score());
    }

    public void winsRally(String player) {
        if (player.equals(servingPlayer)) {
            if (player.equals(PLAYER_1)) {
                player1Score++;
            } else {
                player2Score++;
            }
        } else {
            servingPlayer = player;

        }

        display.display(servingPlayer, score());
    }

    private String score() {
        return PLAYER_1 + ": " + player1Score + ", " + PLAYER_2 + ": " + player2Score;
    }
}
