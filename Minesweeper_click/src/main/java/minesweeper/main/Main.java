package minesweeper.main;

import minesweeper.gui.Gui;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game g = new Game(30, 16, 99);
            SwingUtilities.invokeLater(new Gui(g));
        }
    }
}
