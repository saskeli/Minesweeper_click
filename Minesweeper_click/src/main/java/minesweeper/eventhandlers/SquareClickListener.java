package minesweeper.eventhandlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import minesweeper.gui.GamePanel;
import minesweeper.gui.Square;
import minesweeper.main.Game;

/**
 * Event handler class for tile clicks.
 * 
 * @author Saskeli
 */
public class SquareClickListener implements MouseListener {
    /**
     * Game interface
     */
    private final Game game;
    /**
     * Visual representation of the game grid
     */
    private final GamePanel gamePanel;
    private boolean mouseOnePressed = false;
    private boolean mouseTwoPressed = false;

    /**
     * Event handler class for tile clicks.
     * 
     * @param game  The game logic interface
     * @param gamePanel     The game panel object of the GUI frame.
     */
    public SquareClickListener(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseOnePressed = true;
        } else {
            mouseTwoPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Square s = (Square) e.getSource();
        if (mouseOnePressed && mouseTwoPressed) {
            game.clearSurrounding(s.getCoordinate());
            mouseOnePressed = mouseTwoPressed = false;
            gamePanel.updateGrid();
            return;
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (mouseOnePressed) {
                game.clear(s.getCoordinate());
                gamePanel.updateGrid();
            }
        } else {
            if (mouseTwoPressed) {
                game.toggleFlag(s.getCoordinate());
                gamePanel.updateGrid();
            }
        }
        mouseOnePressed = mouseTwoPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (mouseOnePressed || mouseTwoPressed) {
            mouseOnePressed = mouseTwoPressed = false;
        }
    }
}
