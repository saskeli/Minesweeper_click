package minesweeper.logic;

import minesweeper.util.Coordinate;
import java.util.*;

/**
 * Abstract representation of a minegrid.
 * 
 * @author Saskeli
 */
public class GameGrid {

    private final Tile[][] tiles;
    private boolean started;
    private final int mines;
    private final Coordinate max;

    public GameGrid(int width, int height, int mines) {
        this.mines = mines;
        tiles = new Tile[Math.max(1, height)][Math.max(1, width)];
        this.started = false;
        this.max = new Coordinate(height - 1, width - 1);
    }

    public GameGrid(Tile[][] tiles) {
        this.tiles = tiles;
        this.started = true;
        this.mines = -1;
        this.max = new Coordinate(this.tiles.length - 1, this.tiles[0].length - 1);
    }

    public int getValue(Coordinate coord) {
        if (!started) {
            return -1;
        }
        if (hasMine(coord)) {
            return 9;
        }
        return surroundingMines(coord);
    }

    public boolean isValid(Coordinate coord) {
        return coord.isValid(max);
    }

    public int surroundingMines(Coordinate coord) {
        int adjacentMines = 0;
        for (Coordinate coordinate : coord.getAdjacentCoordinates(max)) {
            if (tiles[coordinate.getRow()][coordinate.getColumn()].isMine()) {
                adjacentMines += 1;
            }
        }
        return adjacentMines;
    }

    public boolean hasMine(Coordinate coord) {
        try {
            return tiles[coord.getRow()][coord.getColumn()].isMine();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean clear(Coordinate coord) {
        if (!coord.isValid(max)) {
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
            for (Coordinate coordinate : coord.getAdjacentCoordinates(max)) {
                if (!tiles[coordinate.getRow()][coordinate.getColumn()].isChecked()) {
                    clear(coordinate);
                }
            }
        }
        return true;
    }

    public void generateTiles(Coordinate coord) {
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

    public void populateList(List<Boolean> mineList, int specialSquares) {
        for (int i = 0; i < (tiles.length * tiles[0].length) - specialSquares; i++) {
            if (i < this.mines) {
                mineList.add(true);
            } else {
                mineList.add(false);
            }
        }
        Collections.shuffle(mineList);
    }

    public void populateTileGrid(List<Boolean> mineList, List<Coordinate> specialSquares) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                Coordinate coord = new Coordinate(i, j);
                if (!isOnList(coord, specialSquares)) {
                    tiles[i][j] = new Tile(mineList.get(0), false);
                    mineList.remove(0);
                } else {
                    tiles[i][j] = new Tile(false, false);
                }
            }
        }
    }

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

    public boolean isOnList(Coordinate coordinate, List<Coordinate> specialSquares) {
        return specialSquares.contains(coordinate);
    }

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

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public boolean isCleared(Coordinate coord) {
        if (!started) {
            return false;
        }
        return tiles[coord.getRow()][coord.getColumn()].isChecked();
    }

    public int getMines() {
        return mines;
    }

    public boolean isFlagged(Coordinate coordinate) {
        if (!started) {
            return false;
        }
        return tiles[coordinate.getRow()][coordinate.getColumn()].isFlagged();
    }

    public void toggleFlag(Coordinate coordinate) {
        if (!started) {
            return;
        }
        tiles[coordinate.getRow()][coordinate.getColumn()].toggleFlag();
    }
}
