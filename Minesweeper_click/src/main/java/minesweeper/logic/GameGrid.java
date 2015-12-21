package minesweeper.logic;

import java.util.*;

public class GameGrid {

    private final boolean[][][] tiles;
    private boolean started;
    private final int mines;

    public GameGrid(int width, int height, int mines) {
        this.mines = mines;
        tiles = new boolean[height][width][2];
        this.started = false;
    }

    public GameGrid(boolean[][][] tiles) {
        this.tiles = tiles;
        this.started = true;
        this.mines = -1;
    }

    public int getValue(int row, int column) {
        if (!started) {
            return 0;
        }
        if (hasMine(row, column)) {
            return -1;
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
            if (tiles[c[0]][c[1]][0]) {
                sum += 1;
            }
        }
        return sum;
    }

    public boolean hasMine(int row, int column) {
        try {
            return tiles[row][column][0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean clear(int row, int column) {
        if (!started) {
            generateTiles(row, column);
            started = true;
        }
        boolean[] t = tiles[row][column];
        t[1] = true;
        if (t[0]) {
            clearMines();
            return false;
        }
        if (surroundingMines(row, column) == 0) {
            for (int[] c : surroundingValidCoords(row, column)) {
                if (!tiles[c[0]][c[1]][1]) {
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
                    tiles[i][j][0] = mineList.get(0);
                    tiles[i][j][1] = false;
                    mineList.remove(0);
                } else {
                    tiles[i][j][0] = false;
                    tiles[i][j][1] = false;
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
            tiles[specials[j][0]][specials[j][1]][0] = mineList.get(j);
        }
    }

    public void clearMines() {
        for (boolean[][] tile : tiles) {
            for (boolean[] t : tile) {
                if (t[0]) {
                    t[1] = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[][] tile : tiles) {
            for (boolean[] t : tile) {
                sb.append(stringRepresentation(t));
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

    public String stringRepresentation(boolean[] t) {
        if (t[0]) {
            if (t[1]) {
                return "¤";
            }
            return "*";
        }
        if (t[1]) {
            return " ";
        }
        return "#";
    }

}
