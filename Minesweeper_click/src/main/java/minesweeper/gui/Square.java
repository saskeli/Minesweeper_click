package minesweeper.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import minesweeper.eventhandlers.SquareClickListener;
import minesweeper.main.Game;
import minesweeper.util.Coordinate;

/**
 * Representation of a minesweeper square for GUI.
 * 
 * @author Saskeli
 */
public class Square extends JLabel {
    /**
     * Game interface.
     */
    private final Game game;
    /**
     * Coordinate of tile that this square represents.
     */
    private final Coordinate coordinate;
    /**
     * The default color of this square. 
     * This is used when the square is neither checked nor flagged.
     */
    private final Color defaultColor;

    /**
     * Representation of a minesweeper square for GUI.
     * 
     * @param game        the game this square is tied to
     * @param coordinate  the game coordiante that this square represents
     * @param listener    the actions listener for this square
     */
    public Square(Game game, Coordinate coordinate, SquareClickListener listener) {
        super("");
        this.game = game;
        this.coordinate = coordinate;
        setName(coordinate.getRow() + " " + coordinate.getColumn());
        addMouseListener(listener);
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setPreferredSize(new Dimension(25, 25));
        setBorder(BorderFactory.createRaisedBevelBorder());
        defaultColor = this.getBackground();
    }

    /**
     * @return  the coordinate of this square
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Refresh the appearance of this Square.
     */
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
}
