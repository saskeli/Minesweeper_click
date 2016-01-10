package minesweeper.score;

import java.io.Serializable;
import minesweeper.util.GameType;

/**
 * Score class that represents the score of a cleared minesweeper game.
 * 
 * @author dongesa
 */
public class Score implements Serializable {
    /**
     * the alias for the clearer.
     */
    private final String name;
    /**
     * number of actions to clear.
     */
    private final int actions;
    /**
     * type of game cleared.
     */
    private final GameType gameType;

    /**
     * Score class that represents the score of a cleared minesweeper game.
     * 
     * @param name     the alias for the clearer
     * @param actions  number of actions to clear
     * @param gameType type of game cleared
     */
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
