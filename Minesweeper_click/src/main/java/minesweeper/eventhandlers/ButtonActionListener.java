package minesweeper.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import minesweeper.gui.GamePanel;
import minesweeper.util.GameType;

/**
 * Action listener class for all the menu buttons and the new gama button.
 *
 * @author dongesa
 */
public class ButtonActionListener implements ActionListener {

    private final GamePanel gamePanel;

    public ButtonActionListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
            default:
                gamePanel.startNewGame(GameType.CUSTOM);
                break;
        }
    }
}
