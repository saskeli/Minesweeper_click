package minesweeper.score;

import java.io.Serializable;
import minesweeper.util.GameType;

/**
 *
 * @author dongesa
 */
class Score implements Serializable {

    private String name;
    private int actions;
    private GameType gameType;

    public Score(String name, int actions, GameType gameType) {
        this.name = name;
        this.actions = actions;
        this.gameType = gameType;
    }

    public int getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public GameType getGameType() {
        return gameType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.gameType);
        sb.append(":\n");
        sb.append(" actions: ");
        sb.append(this.actions);
        sb.append("\n ");
        sb.append(this.name);
        return sb.toString();
    }
}
