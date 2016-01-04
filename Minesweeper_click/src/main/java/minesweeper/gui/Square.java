package minesweeper.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.eventhandlers.SquareClickListener;
import minesweeper.main.Game;
import minesweeper.util.Coordinate;

/**
 * Representation of a minesweeper square for GUI
 * 
 * @author Saskeli
 */
public class Square extends JLabel {
    private Game game;
    private Coordinate coordinate;
    private final Color defaultColor;

    public Square(Game game, Coordinate coordinate, SquareClickListener listener) {
        super("");
        this.game = game;
        this.coordinate = coordinate;
        addMouseListener(listener);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setPreferredSize(new Dimension(25, 25));
        setBorder(BorderFactory.createRaisedBevelBorder());
        defaultColor = this.getBackground();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void update() {
        int tileState = game.getTileState(coordinate);
        if (game.isFlagged(coordinate)) {
            setBackground(Color.PINK);
            return;
        }
        if (!game.isChecked(coordinate)) {
            setBorder(BorderFactory.createRaisedBevelBorder());
            setBackground(defaultColor);
            setText("");
        } else if (tileState == 0) {
            setBackground(Color.WHITE);
            setText("");
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else if (tileState == 9) {
            setBackground(Color.red);
            setText("*");
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            setBackground(Color.WHITE);
            setText("" + tileState);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
    }

    public void toggleFlag() {
        game.toggleFlag(coordinate);
        if (isFlagged()) {
            setBackground(defaultColor);
        } else {
            
        }
    }

    public boolean isFlagged() {
        return game.isFlagged(coordinate);
    }

    public boolean isCleared() {
        return (game.isChecked(coordinate));
    }
}
