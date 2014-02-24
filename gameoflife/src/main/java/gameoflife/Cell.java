package gameoflife;

public class Cell {
    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isCellAlive() {
        return true;
    }

    public Cell withDeadNeighbours(int numberOfDeadNeighbours) {
        if (numberOfDeadNeighbours < 2 || numberOfDeadNeighbours > 3) {
            isAlive = false;
        }
        else {
            isAlive = true;
        }

        return this;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private boolean isAlive;

    public Cell wasDeadPreviously() {
        return this;
    }
}
