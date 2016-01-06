package minesweeper.main;

import java.io.Serializable;
import minesweeper.logic.*;
import minesweeper.util.*;

/**
 * Abstract representation of a minesweeper game.
 * 
 * @author Saskeli
 */
public class Game implements Serializable {
    /**
     * The abstract game grid of the current game
     */
    private GameGrid gameGrid;
    /**
     * true if this game is over
     */
    private boolean over;
    /**
     * Number of action taken so far during the game
     */
    private int actions;

    /**
     * Abstract representation of a minesweeper game.
     * 
     * @param widht   the width of the game grid
     * @param height  the height of the game grid
     * @param mines   the number of mines desired for the game grid
     */
    public Game(int widht, int height, int mines) {
        this.gameGrid = new GameGrid(widht, height, mines);
        this.over = false;
        actions = 0;
        store();
    }
    
    /**
     * Abstract representation of a minesweeper game.
     * 
     * @param gameType  the type of game to create
     */
    public Game(GameType gameType) {
        this.gameGrid = new GameGrid(gameType);
        this.over = false;
        actions = 0;
        store();
    }
    
    /**
     * Abstract representation of a minesweeper game.
     * 
     * @param gameGrid  the GameGrid to use with the game
     */
    public Game(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.over = false;
        actions = 0;
        store();
    }

    /**
     * Abstract representation of a minesweeper game.
     * Creates a new hard game.
     */
    public Game() {
        this(GameType.HARD);
        actions = 0;
        store();
    }
    
    /**
     * Attempt to clear the tile at the specified coordinate
     * 
     * @param coordinate  the coordinate of the tile to clear
     */
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
        store();
    }
    
    /**
     * Start a new game with the given arrtibutes
     * 
     * @param width   the width of the game to create
     * @param height  the height of the game to create
     * @param mines   the desired number of mines
     */
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
        store();
    }
    
    /**
     * Start a new game of a specified game type.
     * Note that CUSTOM type will create a HARD hame instead.
     * 
     * @param gameType  the typoe of game to create
     */
    public void newGame(GameType gameType) {
        gameGrid = new GameGrid(gameType);
        actions = 0;
        over = false;
        store();
    }
    
    /**
     * Start a new game with the same attributes as the current game.
     */
    public void newGame() {
        if (gameGrid.getGameType() == GameType.CUSTOM) {
            newGame(gameGrid.getWidth(), gameGrid.getHeight(), gameGrid.getMines());
        } else {
            newGame(gameGrid.getGameType());
        }
        store();
    }
    
    /**
     * @return  the width of the game grid
     */
    public int gameWidth() {
        return gameGrid.getWidth();
    }
    
    /**
     * @return  the height of the game grid
     */
    public int gameHeight() {
        return gameGrid.getHeight();
    }
    
    /**
     * @return  the number of tiles that need to be cleared
     */
    public int getRemainingTiles() {
        return gameGrid.leftToClear();
    }
    
    /**
     * Gets the value for the tile at the given coordinate
     * 
     * @param coordinate  the coordinate to check
     * @return            integer value of the tile
     */
    public int getTileState(Coordinate coordinate) {
        if (gameGrid.isCleared(coordinate)) {
            return gameGrid.getValue(coordinate);
        }
        return 0;
    }

    /**
     * @return number of actions performed during this game
     */
    public int getActionCount() {
        return actions;
    }

    /**
     * Check if the tile at the given coordiante is flagged
     * 
     * @param coordinate  the coordinate to check
     * @return            true if the tile at the specified coordinate is flagged
     */
    public boolean isFlagged(Coordinate coordinate) {
        return gameGrid.isFlagged(coordinate);
    }

    /**
     * Toggles the flag of the tile at the specified coordinate if possible
     * 
     * @param coordinate  the coordinate of the tile to (de)flag
     */
    public void toggleFlag(Coordinate coordinate) {
        if (over || getRemainingTiles() == 0) {
            return;
        }
        gameGrid.toggleFlag(coordinate);
        store();
    }

    /**
     * Check if the tile at the specified coordinate is checked.
     * 
     * @param coordinate  the coordinate of tile
     * @return            true if the tile is checked
     */
    public boolean isChecked(Coordinate coordinate) {
        return gameGrid.isCleared(coordinate);
    }

    /**
     * Attempt to clear tiles around specified coordinate
     * 
     * @param coordinate  teh coordinate to clear around
     */
    public void clearSurrounding(Coordinate coordinate) {
        if (over || getRemainingTiles() == 0) {
            return;
        }
        if (isChecked(coordinate)) {
            gameGrid.clearSurrounding(coordinate);
            actions++;
        }
        store();
    }

    /**
     * Write current gameState to disk
     */
    private void store() {
        ObjectStorage.storeObject(this, "savegame.dat");
    }
    
    /**
     * @return  the type of the current game
     */
    public GameType getGameType() {
        return gameGrid.getGameType();
    }
}
