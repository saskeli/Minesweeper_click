/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.main;

import minesweeper.logic.*;

/**
 *
 * @author Saskeli
 */
public class Game {
    private GameGrid gameGrid;
    private boolean over;

    public Game(int widht, int height, int mines) {
        this.gameGrid = new GameGrid(widht, height, mines);
        this.over = false;
    }
    
    public Game(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.over = false;
    }

    public Game() {
        this(30, 16, 99);
    }
    
    public void clear(int row, int column) {
        boolean cleared = gameGrid.clear(row, column);
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
        if (gameGrid.isCleared(row, column)) {
            return gameGrid.getValue(row, column);
        }
        return -1;
    }
}
