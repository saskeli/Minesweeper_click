package minesweeper.main;

import minesweeper.gui.Gui;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game g = new Game(9, 9, 10);
            SwingUtilities.invokeLater(new Gui(g));
        }
    }
}
