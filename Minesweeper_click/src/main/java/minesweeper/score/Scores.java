package minesweeper.score;

import java.io.Serializable;
import java.util.*;
import minesweeper.util.*;

/**
 *
 * @author dongesa
 */
public class Scores {
    private EnumMap<GameType, Score> scores;

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
        List<String> sb = new ArrayList<>();
        for (Score s : scores.values()) {
            sb.add(s.toString());
        }
        return String.join("\n\n", sb);
    }
    
    public boolean isNewRecord(GameType gameType, int actions) {
        if (scores.get(gameType) == null) {
            return true;
        }
        return scores.get(gameType).getActions() > actions;
    }
    
    public void setRecord(GameType gameType, int actions, String name) {
        scores.put(gameType, new Score(name, actions, gameType));
        ObjectStorage.storeObject(scores, "highscores.dat");
    }

    public void clear() {
        scores.clear();
    }
}
