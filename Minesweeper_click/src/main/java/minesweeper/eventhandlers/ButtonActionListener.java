package minesweeper.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import minesweeper.gui.*;
import minesweeper.util.GameType;

/**
 * Action listener class for all the menu buttons and the new gama button.
 *
 * @author dongesa
 */
public class ButtonActionListener implements ActionListener {

    private final GamePanel gamePanel;
    private final Gui gui;

    public ButtonActionListener(GamePanel gamePanel, Gui gui) {
        this.gamePanel = gamePanel;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (((JComponent) ae.getSource()).getName()) {
            case "EasyButton":
                gamePanel.startNewGame(GameType.EASY);
                break;
            case "NormalButton":
                gamePanel.startNewGame(GameType.NORMAL);
                break;
            case "HardButton":
                gamePanel.startNewGame(GameType.HARD);
                break;
            case "CustomButton":
                gamePanel.startNewGame(GameType.CUSTOM);
                break;
            case "ScoreButton":
                gui.showHighScores();
                break;
            case "ResetButton":
                gui.resetScores();
                break;
            default:
                gamePanel.startNewGame();
                break;
        }
    }
}
