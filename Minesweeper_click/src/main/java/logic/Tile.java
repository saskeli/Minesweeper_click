
package logic;

public class Tile {
    private final Coordinate coord;
    private final GameGrid grid;
    private boolean mine;

    public Tile(Coordinate coord, GameGrid grid) {
        this(coord, grid, false);
    }
    
    public Tile(Coordinate coord, GameGrid grid, boolean mine) {
        this.coord = coord;
        this.mine = mine;
        this.grid = grid;
    }
    
    public boolean hasMine() {
        return mine;
    }
    
    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public Coordinate getCoord() {
        return coord;
    }
}
