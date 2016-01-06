
package minesweeper.logic;

import java.io.Serializable;

/**
 * Abstract representation of a minesweeper tile
 * 
 * @author Saskeli
 */
public class Tile implements Serializable {
    /**
     * whether this tile has a mine
     */
    private boolean mine;
    /**
     * whether this tile has been checked
     */
    private boolean checked;
    /**
     * whether this tile has been flagged
     */
    private boolean flagged;

    /**
     * Abstract representation of a minesweeper tile
     * 
     * @param mine     whether this tile has a mine
     * @param checked  whether this tile is checked
     */
    public Tile(boolean mine, boolean checked) {
        this(mine, checked, false);
    }

    /**
     * Abstract representation of a minesweeper tile
     * 
     * @param mine     whether this tile has a mine
     * @param checked  whether this tile is checked
     * @param flagged  whether this tile is flagged
     */
    public Tile(boolean mine, boolean checked, boolean flagged) {
        this.mine = mine;
        this.checked = checked;
        this.flagged = flagged;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Set the checked attribute of the tile.
     * If checked is set to true, flagged will be set to false
     * 
     * @param checked  whether this Tile is set to checked or unchecked
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
        if (checked) {
            flagged = false;
        }
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public String toString() {
        if (flagged) {
            return "1";
        }
        if (mine) {
            if (checked) {
                return "¤";
            }
            return "*";
        }
        if (checked) {
            return " ";
        }
        return "#";
    }

    /**
     * Toggles the flagged attribute of this Tile
     */
    public void toggleFlag() {
        this.flagged = !this.flagged;
    }
}
