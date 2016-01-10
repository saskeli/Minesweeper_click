package minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;

/**
 * Panel for the minwsweeper GUI that shows stats and provides the 
 * new game button.
 * 
 * @author dongesa
 */
public class StatPanel extends JPanel {
    /**
     * Label that tracks number of actions.
     */
    private final JLabel clickCountLabel;
    /**
     * Lable that tracks number of tiles to clear.
     */
    private final JLabel toClearLabel;
    
    /**
     * Panel for the minwsweeper GUI that shows stats and provides the 
     * new game button.
     * 
     * @param buttonActionListener  the action listener for the new game button
     */
    public StatPanel(ButtonActionListener buttonActionListener) {
        Dimension d = new Dimension(40, 10);
        
        toClearLabel = new JLabel("-1", JLabel.CENTER);
        toClearLabel.setPreferredSize(d);
        toClearLabel.setName("toClearLabel");
        add(toClearLabel, BorderLayout.WEST);
        
        JButton reStartButton = new JButton("New game");
        reStartButton.setName("NewButton");
        reStartButton.addActionListener(buttonActionListener);
        add(reStartButton, BorderLayout.CENTER);
        
        clickCountLabel = new JLabel("0", JLabel.CENTER);
        clickCountLabel.setPreferredSize(d);
        add(clickCountLabel, BorderLayout.EAST);
    }

    /**
     * Update the action label with the given number.
     * 
     * @param actionCount  the action count to display
     */
    public void setActions(int actionCount) {
        clickCountLabel.setText(actionCount + "");
    }

    /**
     * Update the remaining tiles count with the given number.
     * 
     * @param remainingTiles  the tile coint to display
     */
    public void setRemainingCount(int remainingTiles) {
        toClearLabel.setText(remainingTiles + "");
    }
}
