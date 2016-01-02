package minesweeper.util;

import java.util.*;

/**
 * Class for working with coordinates
 * 
 * @author Saskeli
 */
public class Coordinate {
    private final int row;
    private final int column;

    /**
     * Constructor for coordinate object
     * @param row   row of coordinate
     * @param column    column of coordinate
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the row of the coordinate
     * @return integer row of coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the coordinate
     * @return integer row of coordinate
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Get coordinates adjacent to this coordinate
     * @param max   Maximum coordinate. Needed so only valid coordinates are returned
     * @return List of coordinates surrounding this coordinate
     */
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
    
    /**
     * Validate this coordinate
     * @param max   The coordinate
     * @return true if this coordinate is valid
     */
    public boolean isValid(Coordinate max) {
        return !this.hasBiggerThan(max) && !this.hasSmallerThan(new Coordinate(0, 0));
    }

    /**
     * Coordinate comparison to check if this coordinate is lower or further 
     * to the right than the other coorinate
     * 
     * @param other Coordinate to compare to
     * @return true if this coorinate is either lower ot further to the right
     */
    private boolean hasBiggerThan(Coordinate other) {
        return this.row > other.getRow() || this.column > other.getColumn();
    }

    /**
     * Coordinate comparison to check if this coordinate is higher or further 
     * to the left than the other coorinate
     * 
     * @param other Coordinate to compare to
     * @return true if this coorinate is either higher ot further to the left
     */
    private boolean hasSmallerThan(Coordinate other) {
        return this.row < other.getRow() || this.column < other.getColumn();
    }
}
