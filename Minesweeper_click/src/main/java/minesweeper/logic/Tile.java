
package minesweeper.logic;

/**
 * Abstract representation of a minesweeper tile
 * 
 * @author Saskeli
 */
public class Tile {
    private boolean mine;
    private boolean checked;
    private boolean flagged;

    /**
     * 
     * @param mine
     * @param checked 
     */
    public Tile(boolean mine, boolean checked) {
        this(mine, checked, false);
    }

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

    public void toggleFlag() {
        this.flagged = !this.flagged;
    }
}
