package minesweeper.logic;

import java.util.*;

public class GameGrid {

    private final Tile[][] tiles;
    private boolean started;
    private final int mines;

    public GameGrid(int width, int height, int mines) {
        this.mines = mines;
        tiles = new Tile[height][width];
        this.started = false;
    }

    public GameGrid(Tile[][] tiles) {
        this.tiles = tiles;
        this.started = true;
        this.mines = -1;
    }

    public int getValue(int row, int column) {
        if (!started) {
            return -1;
        }
        if (hasMine(row, column)) {
            return 9;
        }
        return surroundingMines(row, column);
    }

    public List<int[]> surroundingValidCoords(int row, int column) {
        int[][] possible = getSurroundingCoords(row, column);
        List<int[]> actual = new ArrayList<>();
        actual.addAll(Arrays.asList(possible));
        return actual;
    }

    public boolean isValid(int row, int column) {
        if (row < 0 || column < 0) {
            return false;
        }
        return tiles.length > row && tiles[0].length > column;
    }

    public int surroundingMines(int row, int column) {
        int sum = 0;
        for (int[] c : getSurroundingCoords(row, column)) {
            if (tiles[c[0]][c[1]].isMine()) {
                sum += 1;
            }
        }
        return sum;
    }

    public boolean hasMine(int row, int column) {
        try {
            return tiles[row][column].isMine();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean clear(int row, int column) {
        if (!isValid(row, column)) {
            return true;
        }
        if (!started) {
            generateTiles(row, column);
            started = true;
        }
        Tile t = tiles[row][column];
        t.setChecked(true);
        if (t.isMine()) {
            clearMines();
            return false;
        }
        if (surroundingMines(row, column) == 0) {
            for (int[] c : surroundingValidCoords(row, column)) {
                if (!tiles[c[0]][c[1]].isChecked()) {
                    clear(c[0], c[1]);
                }
            }
        }
        return true;
    }

    public void generateTiles(int row, int column) {
        List<Boolean> mineList = new ArrayList<>();
        List<int[]> special = surroundingValidCoords(row, column);
        int[] coord = {row, column};
        special.add(coord);
        populateList(mineList, special.size());
        populateTileGrid(mineList, special);
        int extraMines = mines - (tiles.length * tiles[0].length - special.size());
        if (extraMines > 0) {
            addMinesToSpecials(extraMines, coord);
        }
    }

    public void populateList(List<Boolean> mineList, int specials) {
        for (int i = 0; i < (tiles.length * tiles[0].length) - specials; i++) {
            if (i < this.mines) {
                mineList.add(true);
            } else {
                mineList.add(false);
            }
        }
        Collections.shuffle(mineList);
    }

    public void populateTileGrid(List<Boolean> mineList, List<int[]> special) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                int[] c = {i, j};
                if (!isOnList(c, special)) {
                    tiles[i][j] = new Tile(mineList.get(0), false);
                    mineList.remove(0);
                } else {
                    tiles[i][j] = new Tile(false, false);
                }
            }
        }
    }

    public void addMinesToSpecials(int i, int[] coord) {
        List<Boolean> mineList = new ArrayList<>();
        int[][] specials = getSurroundingCoords(coord[0], coord[1]);
        for (int j = 0; j < specials.length; j++) {
            if (j < i) {
                mineList.add(true);
            } else {
                mineList.add(false);
            }
        }
        Collections.shuffle(mineList);
        for (int j = 0; j < specials.length; j++) {
            tiles[specials[j][0]][specials[j][1]].setMine(mineList.get(j));
        }
    }

    public void clearMines() {
        for (Tile[] tile : tiles) {
            for (Tile t : tile) {
                if (t.isMine()) {
                    t.setChecked(true);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tile[] tile : tiles) {
            for (Tile t : tile) {
                sb.append(t.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int[][] getSurroundingCoords(int row, int column) {
        List<int[]> surroundings = new ArrayList<>();
        if (isValid(row - 1, column - 1)) {
            surroundings.add(new int[]{row - 1, column - 1});
        }
        if (isValid(row - 1, column)) {
            surroundings.add(new int[]{row - 1, column});
        }
        if (isValid(row - 1, column + 1)) {
            surroundings.add(new int[]{row - 1, column + 1});
        }
        if (isValid(row, column - 1)) {
            surroundings.add(new int[]{row, column - 1});
        }
        if (isValid(row, column + 1)) {
            surroundings.add(new int[]{row, column + 1});
        }
        if (isValid(row + 1, column - 1)) {
            surroundings.add(new int[]{row + 1, column - 1});
        }
        if (isValid(row + 1, column)) {
            surroundings.add(new int[]{row + 1, column});
        }
        if (isValid(row + 1, column + 1)) {
            surroundings.add(new int[]{row + 1, column + 1});
        }
        int[][] returnable = new int[surroundings.size()][2];
        for (int i = 0; i < surroundings.size(); i++) {
            returnable[i] = surroundings.get(i);
        }
        return returnable;
    }

    public boolean isOnList(int[] c, List<int[]> special) {
        for (int[] s : special) {
            if (s[0] == c[0] && s[1] == c[1]) {
                return true;
            }
        }
        return false;
    }

    public int leftToClear() {
        if (!started) {
            return -1;
        }
        int count = 0;
        for (Tile[] tile : tiles) {
            for (Tile t : tile) {
                if (t.isMine() | t.isChecked()) {
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

    public boolean isCleared(int row, int column) {
        if (!started) {
            return false;
        }
        return tiles[row][column].isChecked();
    }
}
