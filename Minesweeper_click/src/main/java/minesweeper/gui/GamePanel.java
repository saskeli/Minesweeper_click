package minesweeper.gui;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import minesweeper.eventhandlers.SquareClickListener;
import minesweeper.main.Game;
import minesweeper.util.Coordinate;

/**
 * Class describing minefield in JPanel form
 *
 * @author Saskeli
 */
public class GamePanel extends JPanel {

    private final Game game;
    private final Gui gui;
    private StatPanel statPanel = null;
    private final SquareClickListener squareClickListener;

    public GamePanel(Game game, Gui gui) {
        this.game = game;
        this.gui = gui;
        squareClickListener = new SquareClickListener(game, this);
        populateGrid();
    }

    public void updateGrid() {
        for (Component component : this.getComponents()) {
            Square s = (Square) component;
            s.update();
        }
        if (statPanel != null) {
            statPanel.setActions(game.getActionCount());
            statPanel.setRemainingCount(game.getRemainingTiles());
        }
    }

    public void setStatPanel(StatPanel statPanel) {
        this.statPanel = statPanel;
    }

    public void startNewGame() {
        game.newGame();
        updateGrid();
    }

    public void startNewGame(int width, int height, int mines) {
        game.newGame(width, height, mines);
        removeAll();
        populateGrid();
        gui.rePack();
    }

    private void populateGrid() {
        setLayout(new GridLayout(game.gameHeight(), game.gameWidth()));
        for (int i = 0; i < game.gameHeight(); i++) {
            for (int j = 0; j < game.gameWidth(); j++) {
                add(new Square(game, new Coordinate(i, j), squareClickListener));
            }
        }
        if (statPanel != null) {
            statPanel.setActions(game.getActionCount());
            statPanel.setRemainingCount(game.getRemainingTiles());
        }
    }
}
