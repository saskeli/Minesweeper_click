package minesweeper.eventhandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import minesweeper.main.Game;
import javax.swing.*;
import minesweeper.gui.GamePanel;
import minesweeper.gui.Square;
/**
 *
 * @author dongesa
 */
public class SquareActionListener implements ActionListener {
    private Game game;
    private GamePanel gamePanel;

    public SquareActionListener(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Square s = (Square)((JButton)ae.getSource()).getParent();
        game.clear(s.getRow(), s.getColumn());
        gamePanel.updateGrid();
    }
}
