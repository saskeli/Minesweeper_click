package minesweeper.main;

import minesweeper.util.Coordinate;
import minesweeper.logic.*;
import minesweeper.util.GameType;

/**
 * Abstract representation of a minesweeper game.
 * 
 * @author Saskeli
 */
public class Game {
    private GameGrid gameGrid;
    private boolean over;
    private int actions;

    public Game(int widht, int height, int mines) {
        this.gameGrid = new GameGrid(widht, height, mines);
        this.over = false;
        actions = 0;
    }
    
    public Game(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.over = false;
        actions = 0;
    }

    public Game() {
        this(30, 16, 99);
        actions = 0;
    }
    
    public void clear(Coordinate coordinate) {
        if (over || getRemainingTiles() == 0) {
            return;
        }
        if (gameGrid.isCleared(coordinate)) {
            return;
        }
        boolean cleared = gameGrid.clear(coordinate);
        actions++;
        over = !cleared;
    }
    
    public void newGame(int width, int height, int mines) {
        if (width < 1 || width > 45) {
            width = 30;
        }
        if (height < 1 || height > 32) {
            height = 16;
        }
        if (mines < 1) {
            mines = 99;
        }
        gameGrid = new GameGrid(width, height, mines);
        actions = 0;
        over = false;
    }
    
    public void newGame(GameType gameType) {
        gameGrid = new GameGrid(gameType);
        actions = 0;
        over = false;
    }
    
    public void newGame() {
        if (gameGrid.getGameType() == GameType.CUSTOM) {
            newGame(gameGrid.getWidth(), gameGrid.getHeight(), gameGrid.getMines());
        } else {
            newGame(gameGrid.getGameType());
        }
    }
    
    public int gameWidth() {
        return gameGrid.getWidth();
    }
    
    public int gameHeight() {
        return gameGrid.getHeight();
    }
    
    public int getRemainingTiles() {
        return gameGrid.leftToClear();
    }
    
    public int getTileState(Coordinate coordinate) {
        if (gameGrid.isCleared(coordinate)) {
            return gameGrid.getValue(coordinate);
        }
        return 0;
    }

    public int getActionCount() {
        return actions;
    }

    public boolean isFlagged(Coordinate coordinate) {
        return gameGrid.isFlagged(coordinate);
    }

    public void toggleFlag(Coordinate coordinate) {
        if (over) {
            return;
        }
        gameGrid.toggleFlag(coordinate);
    }

    public boolean isChecked(Coordinate coordinate) {
        return gameGrid.isCleared(coordinate);
    }

    public void clearSurrounding(Coordinate coordinate) {
        if (isChecked(coordinate)) {
            gameGrid.clearSurrounding(coordinate);
            actions++;
        }
    }
}
