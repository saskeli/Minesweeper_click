package minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;

/**
 *
 * @author dongesa
 */
public class StatPanel extends JPanel {
    private JLabel clickCountLabel;
    private JLabel toClearLabel;
    
    public StatPanel(ButtonActionListener buttonActionListener) {
        Dimension d = new Dimension(40, 10);
        
        toClearLabel = new JLabel("", JLabel.CENTER);
        toClearLabel.setPreferredSize(d);
        add(toClearLabel, BorderLayout.WEST);
        
        JButton reStartButton = new JButton("New game");
        reStartButton.setName("NewButton");
        reStartButton.addActionListener(buttonActionListener);
        add(reStartButton, BorderLayout.CENTER);
        
        clickCountLabel = new JLabel("", JLabel.CENTER);
        clickCountLabel.setPreferredSize(d);
        add(clickCountLabel, BorderLayout.EAST);
    }
    
}
