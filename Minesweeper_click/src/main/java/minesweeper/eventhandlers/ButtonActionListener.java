package minesweeper.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import minesweeper.gui.GamePanel;

/**
 *
 * @author dongesa
 */
public class ButtonActionListener implements ActionListener {
    private GamePanel gamePanel;

    public ButtonActionListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (((JButton)ae.getSource()).getName()) {
            case "EasyButton":
                gamePanel.startNewGame(9, 9, 10);
                break;
            case "NormalButton":
                gamePanel.startNewGame(16, 16, 40);
                break;
            case "HardButton":
                gamePanel.startNewGame(16, 30, 99);
                break;
            default:
                gamePanel.startNewGame();
                break;
        }
    }
}
