package minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.main.Game;

/**
 *
 * @author Saskeli
 */
class Square extends JPanel {
    private Game game;
    private final int row;
    private final int column;
    private JLabel value;
    private JButton button;
    
    public Square(Game game, int row, int column) {
        setLayout(new BorderLayout());
        this.game = game;
        this.row = row;
        this.column = column;
        this.value = new JLabel();
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(25, 25));
        add(this.button);
    }
    
}
