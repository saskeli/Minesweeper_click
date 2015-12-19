package logic;

public class Coordinate {
    private final int column;
    private final int row;

    public Coordinate(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    public Coordinate[] getSurroundingCoords() {
        Coordinate[] surrounding = new Coordinate[8];
        surrounding[0] = new Coordinate(column - 1, row - 1);
        surrounding[1] = new Coordinate(column - 1, row);
        surrounding[2] = new Coordinate(column - 1, row + 1);
        surrounding[3] = new Coordinate(column, row - 1);
        surrounding[4] = new Coordinate(column, row + 1);
        surrounding[5] = new Coordinate(column + 1, row - 1);
        surrounding[6] = new Coordinate(column + 1, row);
        surrounding[7] = new Coordinate(column + 1, row + 1);
        return surrounding;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.column;
        hash = 89 * hash + this.row;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.column != other.column) {
            return false;
        }
        return this.row == other.row;
    }    
}
