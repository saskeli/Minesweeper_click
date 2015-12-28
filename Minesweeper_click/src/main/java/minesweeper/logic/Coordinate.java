package minesweeper.logic;

import java.util.*;

public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public List<Coordinate> getAdjacentCoordinates(Coordinate max) {
        List<Coordinate> coords = new ArrayList<>();
        Coordinate coord = new Coordinate(this.row - 1, this.column - 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row, this.column - 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row + 1, this.column - 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row - 1, this.column);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row + 1, this.column);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row - 1, this.column + 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row, this.column + 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        coord = new Coordinate(this.row + 1, this.column + 1);
        if (coord.isValid(max)) {
            coords.add(coord);
        }
        return coords;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.row;
        hash = 61 * hash + this.column;
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
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
    
    public boolean isValid(Coordinate max) {
        return !this.hasBiggerThan(max) && !this.hasSmallerThan(new Coordinate(0, 0));
    }

    private boolean hasBiggerThan(Coordinate other) {
        return this.row > other.getRow() || this.column > other.getColumn();
    }

    private boolean hasSmallerThan(Coordinate other) {
        return this.row < other.getRow() || this.column < other.getColumn();
    }
}
