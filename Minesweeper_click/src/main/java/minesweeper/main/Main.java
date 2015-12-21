
package minesweeper.main;

import minesweeper.logic.GameGrid;

public class Main {
    public static void main(String[] args) {
        GameGrid g = new GameGrid(5, 5, 14);
        g.clear(8, 15);
        System.out.println(g);
    }
}
