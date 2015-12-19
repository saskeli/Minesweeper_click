
package logic;

public class GameGrid {
    private final Tile[][] tiles;
    private boolean started;
    
    private GameGrid(int width, int height, int mines) {
        tiles = new Tile[height][width];
        this.started = false;
    }
    
    private GameGrid(Tile[][] tiles) {
        this.tiles = tiles;
        this.started = true;
    }

    int getValue(Coordinate coord) {
        if (!started) {
            return 0;
        }
        if (hasMine(coord)) {
            return -1;
        }
        return surroundingMines(coord);
    }

    private int surroundingMines(Coordinate coord) {
        int sum = 0;
        for (Coordinate c : coord.getSurroundingCoords()) {
            if (hasMine(c)) {
                sum += 1;
            }
        }
        return sum;
    }

    private boolean hasMine(Coordinate coord) {
        try {
            return tiles[coord.getRow()][coord.getRow()].hasMine();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
