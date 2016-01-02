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
    private final JLabel clickCountLabel;
    private final JLabel toClearLabel;
    
    public StatPanel(ButtonActionListener buttonActionListener) {
        Dimension d = new Dimension(40, 10);
        
        toClearLabel = new JLabel("-1", JLabel.CENTER);
        toClearLabel.setPreferredSize(d);
        add(toClearLabel, BorderLayout.WEST);
        
        JButton reStartButton = new JButton("New game");
        reStartButton.setName("NewButton");
        reStartButton.addActionListener(buttonActionListener);
        add(reStartButton, BorderLayout.CENTER);
        
        clickCountLabel = new JLabel("0", JLabel.CENTER);
        clickCountLabel.setPreferredSize(d);
        add(clickCountLabel, BorderLayout.EAST);
    }

    public void setActions(int actionCount) {
        clickCountLabel.setText(actionCount + "");
    }

    public void setRemainingCount(int remainingTiles) {
        toClearLabel.setText(remainingTiles + "");
    }
}
