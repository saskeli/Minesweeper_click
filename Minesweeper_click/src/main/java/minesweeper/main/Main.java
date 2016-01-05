package minesweeper.main;

import java.io.Serializable;
import minesweeper.gui.Gui;
import javax.swing.SwingUtilities;
import minesweeper.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Serializable object = ObjectStorage.retrieveObject("savegame.dat");
            Game g;
            if (object != null) {
                g = (Game) object;
            } else {
                g = new Game(GameType.NORMAL);
            }
            SwingUtilities.invokeLater(new Gui(g));
        }
    }
}
