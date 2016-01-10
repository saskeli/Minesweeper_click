package minesweeper.score;

import java.io.Serializable;
import java.util.*;
import minesweeper.util.*;

/**
 * Class represenging a set of scores for minesweeper_click. Typically high
 * scores
 *
 * @author dongesa
 */
public class Scores {

    /**
     * Map of scores by GameType
     */
    private EnumMap<GameType, Score> scores;

    /**
     * Class representing a set of scores for minesweeper_click. Typically high
     * scores.
     */
    public Scores() {
        Serializable s = ObjectStorage.retrieveObject("highscores.dat");
        if (s != null) {
            scores = (EnumMap) s;
        } else {
            scores = new EnumMap<>(GameType.class);
        }
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>();
        for (Score s : scores.values()) {
            strings.add(s.toString());
        }
        return String.join("\n\n", strings);
    }

    /**
     * Checks if the given number of actions for the given gametype constitute a
     * new record.
     *
     * @param gameType  the type of game cleared
     * @param actions   the number of actions to clear
     * @return          true if the given description is better than the stored record
     */
    public boolean isNewRecord(GameType gameType, int actions) {
        if (scores.get(gameType) == null) {
            return true;
        }
        return scores.get(gameType).getActions() > actions;
    }

    /**
     * Update the record for the given game type.
     *
     * @param gameType  the type of game cleared
     * @param actions   the number of actions used to clear
     * @param name      the alias of the player
     */
    public void setRecord(GameType gameType, int actions, String name) {
        scores.put(gameType, new Score(name, actions, gameType));
        ObjectStorage.storeObject(scores, "highscores.dat");
    }

    /**
     * Resets all scores.
     */
    public void clear() {
        scores.clear();
    }
}
