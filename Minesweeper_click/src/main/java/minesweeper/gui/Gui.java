package minesweeper.gui;

import java.awt.Container;
import javax.swing.*;
import minesweeper.eventhandlers.ButtonActionListener;
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
        addComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        GamePanel gamePanel = new GamePanel(this.game);
        ButtonActionListener buttonActionListener = new ButtonActionListener(gamePanel);
        StatPanel statPanel = new StatPanel(buttonActionListener);
        contentPane.add(statPanel);
        frame.setJMenuBar(new MineSweeperMenuBar(buttonActionListener));
        gamePanel.setStatPanel(statPanel);
        contentPane.add(gamePanel);
    }
}
