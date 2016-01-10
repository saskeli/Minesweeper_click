package objectinjection;

import minesweeper.logic.*;

/**
 *
 * @author Saskeli
 */
public class Grid {

    private static final boolean[][] mines = new boolean[][]{
        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false},
        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, true, false, false, false, false, false, false, true, false, false, false, true, false, false},
        {false, false, false, false, false, false, false, true, false, false, false, true, true, false, false, false},
        {false, false, true, false, false, false, true, true, false, false, true, false, false, false, false, false},
        {true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false},
        {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false},
        {true, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false},
        {false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false},
        {false, false, false, true, false, false, false, false, false, false, false, true, true, false, true, false},
        {false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false},
        {false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, false},
        {true, false, false, true, false, false, false, true, true, false, false, true, false, true, false, false},
        {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false}};

    public static GameGrid gameGrid() {
        Tile[][] tiles = new Tile[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                tiles[i][j] = new Tile(mines[i][j], false);
            }
        }
        return new GameGrid(tiles);
    }
}
