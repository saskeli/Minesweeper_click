
package minesweeper.logic;

public class Tile {
    private boolean mine;
    private boolean checked;

    public Tile(boolean mine, boolean checked) {
        this.mine = mine;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isMine() {
        return mine;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public String toString() {
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
    
    
}
