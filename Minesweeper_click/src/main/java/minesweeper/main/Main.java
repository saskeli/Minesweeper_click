package minesweeper.main;

import java.io.Serializable;
import minesweeper.gui.Gui;
import javax.swing.SwingUtilities;
import minesweeper.util.*;

/**
 * Entry point class for Minesweeper_click
 * 
 * @author Saskeli
 */
public class Main {

    /**
     * Starts the Minesweeper GUI. Using a stored game state is available.
     * 
     * @param args  can't currently be used with arguments
     */
    public static void main(String[] args) {
        try {
            Serializable object = ObjectStorage.retrieveObject("savegame.dat");
            Game g;
            if (object != null) {
                g = (Game) object;
            } else {
                g = new Game(GameType.NORMAL);
            }
            SwingUtilities.invokeLater(new Gui(g));
        } catch (Exception e) { 
            System.out.println("terminating due to unexpected exception");
        }
    }
}
