package minesweeper.gui;

import java.awt.PopupMenu;
import java.awt.event.KeyEvent;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;

/**
 * Menu bar for minesweeper_click
 * 
 * @author dongesa
 */
class MineSweeperMenuBar extends JMenuBar {
    
    /**
     * Menu bar for minesweeper_click
     * 
     * @param buttonActionListener  the action listener to use for menu buttons
     */
    public MineSweeperMenuBar(ButtonActionListener buttonActionListener) {
        add(newMenu(buttonActionListener));
        add(scoreMenu(buttonActionListener));
    }

    /**
     * Create the new game menu
     * 
     * @param buttonActionListener  the action listener to use for menu buttons
     * @return                      the new game menu
     */
    private JMenu newMenu(ButtonActionListener buttonActionListener) {
        JMenu newMenu = new JMenu("New");
        newMenu.setMnemonic(KeyEvent.VK_N);
        newMenu.getAccessibleContext().setAccessibleDescription("Create new game");
        newMenu.add(menuItem("Custom", KeyEvent.VK_C, "CustomButton", "Create custom game", buttonActionListener));
        newMenu.add(menuItem("Easy", KeyEvent.VK_E, "EasyButton", "Create new easy game", buttonActionListener));
        newMenu.add(menuItem("Normal", KeyEvent.VK_N, "NormalButton", "Create new normal game", buttonActionListener));
        newMenu.add(menuItem("Hard", KeyEvent.VK_H, "HardButton", "Create new hard game", buttonActionListener));
        return newMenu;
    }

    /**
     * Create the high score menu
     * 
     * @param buttonActionListener  the action listener to use for menu buttons
     * @return                      the high score menu
     */
    private JMenu scoreMenu(ButtonActionListener buttonActionListener) {
        JMenu scoreMenu = new JMenu("Scores");
        scoreMenu.setMnemonic(KeyEvent.VK_S);
        scoreMenu.getAccessibleContext().setAccessibleDescription("High scores and reset");
        scoreMenu.add(menuItem("High Scores", KeyEvent.VK_H, "ScoreButton", "Show high scores", buttonActionListener));
        scoreMenu.add(menuItem("Clear scores", KeyEvent.VK_C, "ResetButton", "Clear high scores", buttonActionListener));
        return scoreMenu;
    }
    
    /**
     * Create a JMenuItem
     * 
     * @param text                  the button text
     * @param mnemonic              the hotkey for the button
     * @param name                  the name of the button
     * @param tooltip               the tooltip for the button
     * @param buttonActionListener  the action listener to use for menu buttons
     * @return                      the new JMenuItem
     */
    private JMenuItem menuItem(String text, int mnemonic, String name, String tooltip, ButtonActionListener buttonActionListener) {
        JMenuItem item = new JMenuItem(text, mnemonic);
        item.setName(name);
        item.getAccessibleContext().setAccessibleDescription(tooltip);
        item.addActionListener(buttonActionListener);
        return item;
    }
}
