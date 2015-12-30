package minesweeper.gui;

import minesweeper.eventhandlers.SquareActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.main.Game;

/**
 *
 * @author Saskeli
 */
public class Square extends JPanel {
    private Game game;
    private final int row;
    private final int column;
    private MineLabel value;
    private JButton button;
    private boolean isRevealed = false;
    
    public Square(Game game, int row, int column, SquareActionListener listener) {
        setLayout(new BorderLayout());
        this.game = game;
        this.row = row;
        this.column = column;
        this.value = new MineLabel();
        this.button = createButton(listener);
        add(this.button);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    public void update() {
        int tileState = game.getTileState(row, column);
        if (tileState == -1) {
            if (isRevealed) {
                this.removeAll();
                add(this.button);
            }
            return;
        }
        if (isRevealed) {
            return;
        }
        this.removeAll();
        value.setText("" + tileState);
        add(this.value);
    }

    private JButton createButton(SquareActionListener listener) {
        JButton b = new JButton();
        b.setPreferredSize(new Dimension(25, 25));
        b.setFocusable(false);
        b.addActionListener(listener);
        return b;
    }
}
