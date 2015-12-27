package minesweeper.main;

import minesweeper.gui.Gui;
import minesweeper.textUI.TextGame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game g = new Game(30, 16, 99);
            SwingUtilities.invokeLater(new Gui(g));
        } else {
            Game g = new Game(16, 16, 40);
            TextGame tg = new TextGame(g);
            tg.run();
        }
    }
}
