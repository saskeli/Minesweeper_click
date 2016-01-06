package minesweeper.gui;

import java.awt.Container;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;
import minesweeper.main.Game;
import minesweeper.score.Scores;
import minesweeper.util.GameType;

/**
 * Minesweeper GUI.
 * 
 * @author Saskeli
 */
public class Gui implements Runnable {
    /**
     * Visual representatio of the actual grid
     */
    private final GamePanel gamePanel;
    /**
     * Frame where all UI elements are contained
     */
    private JFrame frame;
    /**
     * Current high scores
     */
    private final Scores highScores;

    /**
     * Minesweeper GUI.
     * 
     * @param game  Game interface
     */
    public Gui(Game game) {
        this.highScores = new Scores();
        this.gamePanel = new GamePanel(game, this);
    }
    
    @Override
    public void run() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        addComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds GUI components to this.
     * 
     * @param contentPane  the pane to add components to
     */
    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        ButtonActionListener buttonActionListener = new ButtonActionListener(gamePanel, this);
        StatPanel statPanel = new StatPanel(buttonActionListener);
        contentPane.add(statPanel);
        frame.setJMenuBar(new MineSweeperMenuBar(buttonActionListener));
        gamePanel.setStatPanel(statPanel);
        contentPane.add(gamePanel);
    }

    /**
     * Repack the frame. I.e. Force complete redraw of the gamegrid.
     */
    public void rePack() {
        frame.pack();
    }

    /**
     * Check if the current score is better than a previous high score
     * and stores the new score if appropriate.
     * 
     * @param gameType     the type of the current game
     * @param actionCount  number of actions to clear the current game
     */
    public void checkScore(GameType gameType, int actionCount) {
        if (highScores.isNewRecord(gameType, actionCount)) {
            String name = (String) JOptionPane.showInputDialog("New record!\nEnter name:");
            highScores.setRecord(gameType, actionCount, name);
            showHighScores();
        }
    }

    /**
     * Display the high scores.
     */
    public void showHighScores() {
        JOptionPane.showMessageDialog(frame, highScores.toString(), "High Scores!", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Reset all high scores
     */
    public void resetScores() {
        highScores.clear();
    }

    /**
     * Create a new custom game with the attributes supplied by the player
     */
    public void createCustomGame() {
        int width = getInt("Enter desired width of custom game, no promises though");
        int height = getInt("Enter desired height of custom game, no promises though");
        int mines = getInt("Enter desired number of mines in the custom game, no promises though");
        gamePanel.startNewGame(width, height, mines);
    }

    /**
     * prompt the user for integer input
     * 
     * @param prompt  the prompt to display in the dialog
     * @return        an integer
     */
    private int getInt(String prompt) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(prompt));
        } catch (Exception e) {
            return -1;
        }
    }
}
