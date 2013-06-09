package tictactoe;

public class Move {
    private int row;
    private int col;
    private Player player;
    private int score;

    private Move(Player player, int row, int col) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        Move that = (Move) o;

        if (col != that.col) return false;
        if (row != that.row) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "player: " + row + ", " + col;
    }


    public static Move move(Player player, int row, int col) {
       return new Move(player, row, col);
    }

    public int getScore() {
        return score;
    }

    public Move withScore(int score) {
        this.score = score;
        return this;
    }
}
