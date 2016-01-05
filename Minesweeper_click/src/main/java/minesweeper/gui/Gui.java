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
    private final Game game;
    private JFrame frame;
    private Scores highScores;

    public Gui(Game game) {
        this.game = game;
        this.highScores = new Scores();
    }
    
    @Override
    public void run() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        GamePanel gamePanel = new GamePanel(this.game, this);
        ButtonActionListener buttonActionListener = new ButtonActionListener(gamePanel, this);
        StatPanel statPanel = new StatPanel(buttonActionListener);
        contentPane.add(statPanel);
        frame.setJMenuBar(new MineSweeperMenuBar(buttonActionListener));
        gamePanel.setStatPanel(statPanel);
        contentPane.add(gamePanel);
    }

    public void rePack() {
        frame.pack();
    }

    public void checkScore(GameType gameType, int actionCount) {
        if (highScores.isNewRecord(gameType, actionCount)) {
            String name = (String) JOptionPane.showInputDialog("New record!\nEnter name:");
            highScores.setRecord(gameType, actionCount, name);
            showHighScores();
        }
    }

    public void showHighScores() {
        JOptionPane.showMessageDialog(frame, highScores.toString(), "High Scores!", JOptionPane.PLAIN_MESSAGE);
    }

    public void resetScores() {
        highScores.clear();
    }
}
