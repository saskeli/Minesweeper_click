package minesweeper.main;

import minesweeper.logic.*;

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
    
    public void clear(int row, int column) {
        boolean cleared = gameGrid.clear(new Coordinate(row, column));
        actions++;
        over = !cleared;
    }
    
    public void newGame(int width, int height, int mines) {
        if (width < 1) {
            width = 30;
        }
        if (height < 1) {
            height = 16;
        }
        if (mines < 1) {
            mines = 99;
        }
        gameGrid = new GameGrid(width, height, mines);
        actions = 0;
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
    
    public int getTileState(int row, int column) {
        if (gameGrid.isCleared(new Coordinate(row, column))) {
            return gameGrid.getValue(new Coordinate(row, column));
        }
        return -1;
    }

    public int getActionCount() {
        return actions;
    }
}
