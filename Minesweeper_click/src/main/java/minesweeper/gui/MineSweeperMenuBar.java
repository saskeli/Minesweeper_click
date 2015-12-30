package minesweeper.gui;

import java.awt.event.KeyEvent;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;

/**
 *
 * @author dongesa
 */
class MineSweeperMenuBar extends JMenuBar {
    
    public MineSweeperMenuBar(ButtonActionListener buttonActionListener) {
        
        JMenu newMenu = new JMenu("New");
        newMenu.setMnemonic(KeyEvent.VK_N);
        newMenu.getAccessibleContext().setAccessibleDescription("Create new game");
        add(newMenu);
        
        JMenuItem customMenuItem = new JMenuItem("Custom", KeyEvent.VK_C);
        customMenuItem.setName("CustomButton");
        customMenuItem.getAccessibleContext().setAccessibleDescription("Create custom game");
        customMenuItem.addActionListener(buttonActionListener);
        newMenu.add(customMenuItem);
        
        JMenuItem easyMenuItem = new JMenuItem("Easy", KeyEvent.VK_E);
        easyMenuItem.setName("EasyButton");
        easyMenuItem.getAccessibleContext().setAccessibleDescription("Create new easy game");
        easyMenuItem.addActionListener(buttonActionListener);
        newMenu.add(easyMenuItem);
        
        JMenuItem normalMenuItem = new JMenuItem("Normal", KeyEvent.VK_N);
        normalMenuItem.setName("NormalButton");
        normalMenuItem.getAccessibleContext().setAccessibleDescription("Create new normal game");
        normalMenuItem.addActionListener(buttonActionListener);
        newMenu.add(normalMenuItem);
        
        JMenuItem hardMenuItem = new JMenuItem("Hard", KeyEvent.VK_H);
        hardMenuItem.setName("HardButton");
        hardMenuItem.getAccessibleContext().setAccessibleDescription("Create new hard game");
        hardMenuItem.addActionListener(buttonActionListener);
        newMenu.add(hardMenuItem);
    }
}
