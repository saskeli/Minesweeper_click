package minesweeper.logic;

import java.io.Serializable;
import java.util.*;
import minesweeper.util.*;

/**
 * Abstract representation of a minegrid.
 *
 * @author Saskeli
 */
public class GameGrid implements Serializable {
    /**
     * The actual game grid. A square array of tiles
     */
    private final Tile[][] tiles;
    /**
     * Indicates if the game has been started.
     * I.e. if the tile array has been populated.
     */
    private boolean started;
    /**
     * Number of mines specified by the constructor.
     * Note: May be higher than the actual number of mines
     * in the array.
     */
    private final int mines;
    /**
     * Coordinate of the bottom right tile in the array.
     * Usefull for tile validation.
     */ 
    private final Coordinate max;
    /**
     * The type of game used for creating this grid.
     */
    private final GameType gameType;

    /**
     * Abstract representation of a minegrid.
     * 
     * @param width     the width of the grid to be created
     * @param height    the hight of the grid to be created
     * @param mines     the desired mine count
     */
    public GameGrid(int width, int height, int mines) {
        gameType = GameType.CUSTOM;
        this.mines = mines;
        tiles = new Tile[Math.max(1, height)][Math.max(1, width)];
        this.started = false;
        this.max = new Coordinate(height - 1, width - 1);
    }
    
    /**
     * Abstract representation of a minegrid.
     * 
     * @param tiles  a square array of tiles.
     */
    public GameGrid(Tile[][] tiles) {
        gameType = GameType.CUSTOM;
        this.tiles = tiles;
        this.started = true;
        this.mines = -1;
        this.max = new Coordinate(this.tiles.length - 1, this.tiles[0].length - 1);
    }
    
    /**
     * Abstract representation of a minegrid.
     * Note: With the CUSTOM game type. A HARD game will be started instead.
     * 
     * @param gameType the type of game to create
     */
    public GameGrid(GameType gameType) {
        if (gameType == GameType.EASY) {
            this.gameType = gameType;
            this.mines = 10;
            this.tiles = new Tile[9][9];
            this.started = false;
            this.max = new Coordinate(8, 8);
        } else if (gameType == GameType.NORMAL) {
            this.gameType = gameType;
            this.mines = 40;
            this.tiles = new Tile[16][16];
            this.started = false;
            this.max = new Coordinate(15, 15);
        } else {
            this.gameType = GameType.HARD;
            this.mines = 99;
            this.tiles = new Tile[16][30];
            this.started = false;
            this.max = new Coordinate(15, 29);
        }
    }

    public GameType getGameType() {
        return gameType;
    }

    /**
     * get the number of mines that are known to be adjacent to 
     * the given coordinate
     * 
     * @param coord the coodinate to check around
     * @return      0 if the game has not started, 9 if the tile is a mine, and 0-8 otherwise.
     */
    public int getValue(Coordinate coord) {
        if (!started) {
            return 0;
        }
        if (hasMine(coord)) {
            return 9;
        }
        return surroundingMines(coord);
    }
    
    /**
     * Check if a given coordinate is valid.
     * I.e. that the coordinate is withing the curren game.
     * 
     * @param coord the coordinate to check
     * @return      true if the coordinate is on the grid.
     */
    public boolean isValid(Coordinate coord) {
        return coord.isValid(max);
    }

    /**
     * Get the number of mines known to be adjacent to a given coordinate.
     * 
     * @param coord Coordinate to check around
     * @return      Number of mines 0-8.
     */
    private int surroundingMines(Coordinate coord) {
        int adjacentMines = 0;
        for (Coordinate coordinate : coord.getAdjacentCoordinates(max)) {
            if (tiles[coordinate.getRow()][coordinate.getColumn()].isMine()) {
                adjacentMines += 1;
            }
        }
        return adjacentMines;
    }
    
    /**
     * Check if there is a mine at a given coordiante.
     * 
     * @param coord  the coordinate to check
     * @return       true if there is a mine at the given coordinate
     */
    public boolean hasMine(Coordinate coord) {
        try {
            return tiles[coord.getRow()][coord.getColumn()].isMine();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Clears the tile at the given coordinate.
     * Esentially clicks the tile.
     * 
     * @param coord  the oordinate to clear
     * @return       false if the cleared tile was a mine
     */
    public boolean clear(Coordinate coord) {
        if (!coord.isValid(max) || isCleared(coord) || isFlagged(coord)) {
            return true;
        }
        if (!started) {
            generateTiles(coord);
            started = true;
        }
        Tile tile = tiles[coord.getRow()][coord.getColumn()];
        tile.setChecked(true);
        if (tile.isMine()) {
            clearMines();
            return false;
        }
        if (surroundingMines(coord) == 0) {
            clearSurroundingTiles(coord);
        }
        return true;
    }

    /**
     * Populates the tile array.
     * 
     * @param coord  the coordinate that can't be a mine
     */
    private void generateTiles(Coordinate coord) {
        List<Boolean> mineList = new ArrayList<>();
        List<Coordinate> specialSquares = coord.getAdjacentCoordinates(max);
        specialSquares.add(coord);
        populateList(mineList, specialSquares.size());
        populateTileGrid(mineList, specialSquares);
        int extraMines = mines - (tiles.length * tiles[0].length - specialSquares.size());
        if (extraMines > 0) {
            addMinesToSpecials(extraMines, coord);
        }
    }

    /**
     * Generates a randomized list of mines
     * 
     * @param mineList        the list of to populate
     * @param specialSquares  the minimum number of squares to leave clear
     */
    private void populateList(List<Boolean> mineList, int specialSquares) {
        for (int i = 0; i < (tiles.length * tiles[0].length) - specialSquares; i++) {
            if (i < this.mines) {
                mineList.add(true);
            } else {
                mineList.add(false);
            }
        }
        Collections.shuffle(mineList);
    }

    /**
     * Maps the list of booleans to the tile array.
     * 
     * @param mineList        the list of booleans signifying mines
     * @param specialSquares  the squares to leave clear
     */
    private void populateTileGrid(List<Boolean> mineList, List<Coordinate> specialSquares) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                Coordinate coord = new Coordinate(i, j);
                if (!specialSquares.contains(coord)) {
                    tiles[i][j] = new Tile(mineList.get(0), false);
                    mineList.remove(0);
                } else {
                    tiles[i][j] = new Tile(false, false);
                }
            }
        }
    }

    /**
     * Used to populate squares around the initially cleared square on 
     * game start if there are more mines than initially populated.
     * 
     * @param minesToAdd   the number of mines to add
     * @param aroundCoord  the coordinate to leave clear
     */
    public void addMinesToSpecials(int minesToAdd, Coordinate aroundCoord) {
        List<Boolean> mineList = new ArrayList<>();
        List<Coordinate> specialSquares = aroundCoord.getAdjacentCoordinates(max);
        for (int j = 0; j < specialSquares.size(); j++) {
            if (j < minesToAdd) {
                mineList.add(true);
            } else {
                mineList.add(false);
            }
        }
        Collections.shuffle(mineList);
        for (int j = 0; j < specialSquares.size(); j++) {
            tiles[specialSquares.get(j).getRow()][specialSquares.get(j).getColumn()].setMine(mineList.get(j));
        }
    }

    /**
     * Sets the state of all tiles with mines to cleared.
     * Usefull for clearing all mines on game over.
     */
    public void clearMines() {
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                if (tile.isMine()) {
                    tile.setChecked(true);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                stringBuilder.append(tile.toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Get the number of tiles left to clear. 
     * I.e. tiles that are not cleared and are not mines.
     * 
     * @return  the number of tiles left to clear
     */
    public int leftToClear() {
        if (!started) {
            return -1;
        }
        int count = 0;
        for (Tile[] tileRow : tiles) {
            for (Tile tile : tileRow) {
                if (tile.isMine() | tile.isChecked()) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    /**
     * @return the width of the game grid
     */
    public int getWidth() {
        return tiles[0].length;
    }

    /**
     * @return the height of the game grid
     */
    public int getHeight() {
        return tiles.length;
    }

    /**
     * Check wether the tile at the specified coordinate is cleared.
     * 
     * @param coord  the coordinate to check
     * @return       true if the tile at the specified coordinate is cleared
     */
    public boolean isCleared(Coordinate coord) {
        if (!started) {
            return false;
        }
        return tiles[coord.getRow()][coord.getColumn()].isChecked();
    }

    /**
     * @return the number of mines specified by the constructor of this GameGrid
     */
    public int getMines() {
        return mines;
    }

    /**
     * Check if the tile at the specified coordinate is flagged.
     * 
     * @param coordinate  the coordinate to check
     * @return            true if the specified tile is flagged
     */
    public boolean isFlagged(Coordinate coordinate) {
        if (!started) {
            return false;
        }
        return tiles[coordinate.getRow()][coordinate.getColumn()].isFlagged();
    }

    /**
     * Toggles the flag of the tile at the specified coordinate
     * 
     * @param coordinate  the coordinate for the tile to toggle
     */
    public void toggleFlag(Coordinate coordinate) {
        if (!started || isCleared(coordinate)) {
            return;
        }
        tiles[coordinate.getRow()][coordinate.getColumn()].toggleFlag();
    }

    /**
     * Clears the non-flagged tiles around the specified coordinate
     * if the value of the tile specified by the coordinate equals 
     * the number of flags adjacent to the tile.
     * 
     * @param coordinate  the coordinate to clear around
     */
    public void clearSurrounding(Coordinate coordinate) {
        if (surroundingMines(coordinate) == surroundingFlags(coordinate)) {
            clearSurroundingTiles(coordinate);
        }
    }

    /**
     * Get the number of flagged tiles surrounding the tile specified
     * by the given coordinate
     * 
     * @param coordinate  the coordinate to check around
     * @return            the number of flags surrounding the coordinate
     */
    private int surroundingFlags(Coordinate coordinate) {
        int adjacentFlags = 0;
        for (Coordinate coord : coordinate.getAdjacentCoordinates(max)) {
            if (tiles[coord.getRow()][coord.getColumn()].isFlagged()) {
                adjacentFlags += 1;
            }
        }
        return adjacentFlags;
    }

    /**
     * Calls clear for all the coordinates surrounding the specified coordiante
     * 
     * @param coord  the coordinate to clear around
     */
    private void clearSurroundingTiles(Coordinate coord) {
        for (Coordinate coordinate : coord.getAdjacentCoordinates(max)) {
            clear(coordinate);
        }
    }
}
