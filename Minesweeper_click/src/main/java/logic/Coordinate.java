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
