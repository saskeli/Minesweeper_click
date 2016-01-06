package minesweeper.util;

import java.io.Serializable;

/**
 * Game Type enumerator
 * 
 * @author dongesa
 */
public enum GameType implements Serializable {
    /**
     * 16 x 30 with 99 mines.
     */
    HARD,
    /**
     * 16 x 16 with 40 mines.
     */
    NORMAL,
    /**
     * 9 x 9 with 10 mines.
     */
    EASY,
    /**
     * 1-32 x 1-45 with 1-* mines.
     */
    CUSTOM
}
