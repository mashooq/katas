package tictactoe;

public class PlayerMove {
    private int row;
    private int col;
    private Player player;

    private PlayerMove(Player player, int row, int col) {
        this.player = player;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public static PlayerMove move(Player player, int row, int col) {
       return new PlayerMove(player, row, col);
    }
}
