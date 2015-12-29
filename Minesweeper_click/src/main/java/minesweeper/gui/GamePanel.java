package minesweeper.gui;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import minesweeper.main.Game;

public class GamePanel extends JPanel {
    private Game game;

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

    void updateGrid() {
        for (Component component : this.getComponents()) {
            Square s = (Square)component;
            s.update();
        }
        updateUI();
    }
}
