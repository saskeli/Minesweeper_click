package minesweeper.gui;

import minesweeper.eventhandlers.SquareActionListener;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import minesweeper.main.Game;

public class GamePanel extends JPanel {
    private Game game;
    private StatPanel statPanel = null;

    public GamePanel(Game game) {
        this.game = game;
        SquareActionListener l = new SquareActionListener(game, this);
        setLayout(new GridLayout(game.gameHeight(), game.gameWidth()));
        for (int i = 0; i < game.gameHeight(); i++) {
            for (int j = 0; j < game.gameWidth(); j++) {
                add(new Square(game, i, j, l));
            }
        }
    }

    public void updateGrid() {
        for (Component component : this.getComponents()) {
            Square s = (Square)component;
            s.update();
        }
        updateUI();
    }

    public void setStatPanel(StatPanel statPanel) {
        this.statPanel = statPanel;
    }

    public void startNewGame() {
        game.newGame();
        updateGrid();
    }

    public void startNewGame(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
