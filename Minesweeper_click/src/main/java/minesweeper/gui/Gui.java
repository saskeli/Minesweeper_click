package minesweeper.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import minesweeper.main.Game;

public class Gui implements Runnable {
    private final Game game;
    private JFrame frame;

    public Gui(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addMenuBar(frame);
        addComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void addMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu newMenu = new JMenu("New");
        newMenu.setMnemonic(KeyEvent.VK_N);
        newMenu.getAccessibleContext().setAccessibleDescription("Create new game");
        menuBar.add(newMenu);
        
        JMenuItem customMenuItem = new JMenuItem("Custom", KeyEvent.VK_C);
        customMenuItem.getAccessibleContext().setAccessibleDescription("Create custom game");
        newMenu.add(customMenuItem);
        
        JMenuItem easyMenuItem = new JMenuItem("Easy", KeyEvent.VK_E);
        easyMenuItem.getAccessibleContext().setAccessibleDescription("Create new easy game");
        newMenu.add(easyMenuItem);
        
        JMenuItem normalMenuItem = new JMenuItem("Normal", KeyEvent.VK_N);
        normalMenuItem.getAccessibleContext().setAccessibleDescription("Create new normal game");
        newMenu.add(normalMenuItem);
        
        JMenuItem hardMenuItem = new JMenuItem("Hard", KeyEvent.VK_H);
        hardMenuItem.getAccessibleContext().setAccessibleDescription("Create new hard game");
        newMenu.add(hardMenuItem);
        
        frame.setJMenuBar(menuBar);
    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JPanel statPanel = new JPanel(new BorderLayout(0, 3));
        populateStatPanel(statPanel);
        contentPane.add(statPanel);
        GamePanel gamePanel = new GamePanel(this.game);
        contentPane.add(gamePanel);
    }

    private void populateStatPanel(JPanel statPanel) {
        JLabel toClearLabel = new JLabel(game.getRemainingTiles() + "", JLabel.CENTER);
        Dimension d = new Dimension(40, 10);
        toClearLabel.setPreferredSize(d);
        statPanel.add(toClearLabel, BorderLayout.WEST);
        JButton reStartButton = new JButton("New game");
        statPanel.add(reStartButton, BorderLayout.CENTER);
        JLabel clickCountLabel = new JLabel(game.getActionCount() + "", JLabel.CENTER);
        clickCountLabel.setPreferredSize(d);
        statPanel.add(clickCountLabel, BorderLayout.EAST);
    }
}
