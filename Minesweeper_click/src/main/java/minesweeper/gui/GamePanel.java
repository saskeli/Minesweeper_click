package minesweeper.gui;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import minesweeper.eventhandlers.SquareClickListener;
import minesweeper.main.Game;
import minesweeper.util.Coordinate;
import minesweeper.util.GameType;

/**
 * Class describing minefield in JPanel form
 *
 * @author Saskeli
 */
public class GamePanel extends JPanel {
    /**
     * Game logic interface
     */
    private final Game game;
    /**
     * Gui parent
     */
    private final Gui gui;
    /**
     * Panel to be updated with game stats
     */
    private StatPanel statPanel = null;
    /**
     * Action listener to be used with all Squares
     */
    private final SquareClickListener squareClickListener;

    /**
     * Class describing minefield in JPanel form
     * 
     * @param game  Game logic interface
     * @param gui   The parent GUI.
     */
    public GamePanel(Game game, Gui gui) {
        this.game = game;
        this.gui = gui;
        squareClickListener = new SquareClickListener(game, this);
        populateGrid();
        updateGrid();
    }
    
    /**
     * Refreshes the visual representation of the game
     */
    public final void updateGrid() {
        for (Component component : this.getComponents()) {
            Square s = (Square) component;
            s.update();
        }
        if (statPanel != null) {
            statPanel.setActions(game.getActionCount());
            statPanel.setRemainingCount(game.getRemainingTiles());
        }
        if (game.getRemainingTiles() == 0) {
            gui.checkScore(game.getGameType(), game.getActionCount());
        }
    }
    
    public void setStatPanel(StatPanel statPanel) {
        this.statPanel = statPanel;
    }

    /**
     * Starts a new game with the same parameters as the original
     * and updates the visual representation
     */
    public void startNewGame() {
        game.newGame();
        updateGrid();
    }

    /**
     * Starts an new custom game and updates the visual representation
     * 
     * @param width     Width of the game to be created
     * @param height    height of the game to be created
     * @param mines     Number of mines for the new game
     */
    public void startNewGame(int width, int height, int mines) {
        game.newGame(width, height, mines);
        removeAll();
        populateGrid();
        gui.rePack();
    }
    
    /**
     * Starts a new game with one of the standart settings.
     * Note: Custom will start a hard game
     * 
     * @param gameType  Type of game to be started
     */
    public void startNewGame(GameType gameType) {
        game.newGame(gameType);
        removeAll();
        populateGrid();
        gui.rePack();
    }
    
    /**
     * Add the sqares to the gird and the action listener 
     * to the sqares
     */
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
