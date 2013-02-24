package tennis.refactor;

public class Player {
    private int points = 0;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void wonPoint() {
        points += 1;
    }
}