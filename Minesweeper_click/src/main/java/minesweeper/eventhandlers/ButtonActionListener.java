package minesweeper.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import minesweeper.gui.GamePanel;

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
                gamePanel.startNewGame(9, 9, 10);
                break;
            case "NormalButton":
                gamePanel.startNewGame(16, 16, 40);
                break;
            case "HardButton":
                gamePanel.startNewGame(30, 16, 99);
                break;
            default:
                gamePanel.startNewGame();
                break;
        }
    }
}
