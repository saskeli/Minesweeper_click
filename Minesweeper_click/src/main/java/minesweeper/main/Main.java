package minesweeper.main;

import minesweeper.gui.Gui;
import javax.swing.SwingUtilities;
import minesweeper.util.GameType;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game g = new Game(GameType.NORMAL);
            SwingUtilities.invokeLater(new Gui(g));
        }
    }
}
