package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tictactoe.TicTacToe.Player.*;
import static tictactoe.TicTacToe.WinningPosition.*;

public class TicTacToe {
    public enum Player {O, X}
    public enum WinningPosition {D1,D2,V1,V2,V3,H1,H2,H3}

    public Map<Player, List<WinningPosition>> winningPositions =
            new HashMap<Player, List<WinningPosition>>() {{
                put(Player.O, new ArrayList<WinningPosition>());
                put(Player.X, new ArrayList<WinningPosition>());
            }};

    public void reset() {
        currentPlayer = currentPlayer == O ? X : O;
    }

    public int possibleWinningPositionsFor(Player player) {
        return winningPositions.get(player).size();
    }

    public void makeNextMove() {
        List<WinningPosition> currentPlayersPositions = winningPositions.get(currentPlayer);
        if (currentPlayer == O) {
            currentPlayersPositions.add(D1);
            currentPlayersPositions.add(D2);
            currentPlayersPositions.add(H2);
            currentPlayersPositions.add(V2);
        } else {
            currentPlayersPositions.add(V1);
            currentPlayersPositions.add(H1);
        }

        currentPlayer = currentPlayer == O ? X : O;
    }

    Player currentPlayer = O;

    public boolean isGameBoardEmpty() {
        return true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
