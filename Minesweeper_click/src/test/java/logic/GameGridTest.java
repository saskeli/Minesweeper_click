package logic;

import minesweeper.util.Coordinate;
import minesweeper.logic.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Saskeli
 */
public class GameGridTest {
    private GameGrid randomGameGrid;
    private GameGrid smallInjectedGridOne;
    private GameGrid smallInjectedGridTwo;
    private GameGrid brokenGame;
    private GameGrid randomGameWithSomeExtraMines;
    private GameGrid randomGameWithExactMineCount;
    
    public GameGridTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        randomGameGrid = new GameGrid(30, 16, 99);
        randomGameGrid.clear(new Coordinate(8, 15));
        smallInjectedGridOne = new GameGrid(new Tile[][]{{new Tile(false, true), new Tile(true, false)}, 
                                                         {new Tile(false, false), new Tile(true, false)}});
        smallInjectedGridTwo = new GameGrid(new Tile[][]{{new Tile(false, false), new Tile(false, false), new Tile(false, false)}, 
                                                         {new Tile(false, false), new Tile(false, false), new Tile(false, false)},
                                                         {new Tile(false, false), new Tile(false, false), new Tile(false, false)}});
        brokenGame = new GameGrid(5, 5, 25);
        randomGameWithSomeExtraMines = new GameGrid(5, 5, 21);
        randomGameWithExactMineCount = new GameGrid(5, 5, 16);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void mineClearOnLoss() {
        assertEquals(true, brokenGame.clear(new Coordinate(2, 3)));
        assertEquals(false, brokenGame.clear(new Coordinate(0, 0)));
        int mines = 0;
        int clearedMines = 0;
        int clearedTiles = 0;
        for (char c : brokenGame.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == ' ') {
                clearedTiles++;
            } else if (c == '¤') {
                clearedMines++;
            }
        }
        assertEquals(0, mines);
        assertEquals(24, clearedMines);
        assertEquals(1, clearedTiles);
    }
    
    @Test
    public void randomConstructorWithTooManyMines() {
        assertEquals(true, brokenGame.clear(new Coordinate(2, 3)));
        int mines = 0;
        int clearedTiles = 0;
        for (char c : brokenGame.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == ' ') {
                clearedTiles++;
            }
        }
        assertEquals(24, mines);
        assertEquals(1, clearedTiles);
    }
    
    @Test
    public void gameGridNonInjectedContructor() {
        int mines = 0;
        int hiddenTiles = 0;
        int clearedTiles = 0;
        for (char c : randomGameGrid.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == '#') {
                hiddenTiles++;
            } else if (c == ' ') {
                clearedTiles++;
            }
        }
        assertEquals(99, mines);
        assertEquals(30 * 16 - 99, hiddenTiles + clearedTiles);
        assertEquals(true, clearedTiles > 8);
    }
    
    @Test
    public void isOnListWhenOnList() {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(1, 2));
        list.add(new Coordinate(3, 5));
        Coordinate target = new Coordinate(3, 5);
        assertEquals(true, randomGameGrid.isOnList(target, list));
    }
    
    @Test
    public void isOnListWhenNotOnList() {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(1, 2));
        list.add(new Coordinate(3, 5));
        Coordinate target = new Coordinate(4, 5);
        assertEquals(false, randomGameGrid.isOnList(target, list));
    }
    
    @Test
    public void gridStringRepresentation() {
        assertEquals(" *\n#*\n", smallInjectedGridOne.toString());
    }
    
    @Test
    public void clearMines() {
        smallInjectedGridOne.clearMines();
        assertEquals(" ¤\n#¤\n", smallInjectedGridOne.toString());
    }
    
    @Test
    public void mineInsertionAroundCentralStartOne() {
        smallInjectedGridTwo.addMinesToSpecials(8, new Coordinate(1, 1));
        assertEquals("***\n*#*\n***\n", smallInjectedGridTwo.toString());
    }
    
    @Test
    public void mineInsertionAroundCentralStartTwo() {
        smallInjectedGridTwo.addMinesToSpecials(4, new Coordinate(1, 1));
        int mines = 0;
        int hiddenTiles = 0;
        for (char c : smallInjectedGridTwo.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == '#') {
                hiddenTiles++;
            }
        }
        assertEquals(4, mines);
        assertEquals(5, hiddenTiles);
    }
    
    @Test
    public void mineInsertionAroundEdgeStartOne() {
        smallInjectedGridTwo.addMinesToSpecials(7, new Coordinate(0, 1));
        assertEquals("*#*\n***\n###\n", smallInjectedGridTwo.toString());
    }
    
    @Test
    public void mineInsertionAroundEdgeStartTwo() {
        smallInjectedGridTwo.addMinesToSpecials(4, new Coordinate(0, 1));
        int mines = 0;
        int hiddenTiles = 0;
        for (char c : smallInjectedGridTwo.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == '#') {
                hiddenTiles++;
            }
        }
        assertEquals(4, mines);
        assertEquals(5, hiddenTiles);
    }
    
    @Test
    public void mineInsertionAroundCornerStartOne() {
        smallInjectedGridTwo.addMinesToSpecials(7, new Coordinate(0, 0));
        assertEquals("#*#\n**#\n###\n", smallInjectedGridTwo.toString());
    }
    
    @Test
    public void mineInsertionAroundCornerStartTwo() {
        smallInjectedGridTwo.addMinesToSpecials(2, new Coordinate(0, 0));
        int mines = 0;
        int hiddenTiles = 0;
        for (char c : smallInjectedGridTwo.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == '#') {
                hiddenTiles++;
            }
        }
        assertEquals(2, mines);
        assertEquals(7, hiddenTiles);
    }
    
    @Test
    public void getValueOfNonStarted() {
        assertEquals(-1, brokenGame.getValue(new Coordinate(2, 2)));
    }
    
    @Test
    public void getValueOfMine() {
        brokenGame.clear(new Coordinate(2, 3));
        assertEquals(9, brokenGame.getValue(new Coordinate(2, 2)));
    }
    
    @Test
    public void getValueOfNonMine() {
        brokenGame.clear(new Coordinate(2, 3));
        assertEquals(8, brokenGame.getValue(new Coordinate(2, 3)));
    }
    
    @Test
    public void hasMineTestOnMine() {
        assertEquals(true, smallInjectedGridOne.hasMine(new Coordinate(0, 1)));
    }
    
    @Test
    public void hasMineTestOnNonMine() {
        assertEquals(false, smallInjectedGridOne.hasMine(new Coordinate(0, 0)));
    }
    
    @Test
    public void hasMineTestOnInvalidLocation() {
        assertEquals(false, smallInjectedGridOne.hasMine(new Coordinate(3, 4)));
    }
    
    @Test
    public void mineCountWhenExtraMinesOnStartup() {
        assertEquals(true, randomGameWithSomeExtraMines.clear(new Coordinate(2, 2)));
        int mines = 0;
        int clearedTiles = 0;
        int unClearedTiles = 0;
        for (char c : randomGameWithSomeExtraMines.toString().toCharArray()) {
            if (c == '*') {
                mines++;
            } else if (c == '#') {
                unClearedTiles++;
            } else if (c == ' ') {
                clearedTiles++;
            }
        }
        assertEquals(21, mines);
        assertEquals(3, unClearedTiles);
        assertEquals(1, clearedTiles);
        assertEquals(5, randomGameWithSomeExtraMines.getValue(new Coordinate(2, 2)));
    }
    
    @Test
    public void mineCountWithExactMinesInConstructor() {
        randomGameWithExactMineCount.clear(new Coordinate(2, 2));
        assertEquals("*****\n*   *\n*   *\n*   *\n*****\n", 
                randomGameWithExactMineCount.toString());
    }
    
    @Test
    public void invalidCoordinateClear() {
        assertEquals(true, randomGameGrid.clear(new Coordinate(16, 30)));
    }
    
    @Test
    public void leftToClearOnUninitializedGameGrid() {
        assertEquals(-1, randomGameWithSomeExtraMines.leftToClear());
    }
    
    @Test
    public void leftToClear() {
        assertEquals(1, smallInjectedGridOne.leftToClear());
    }
    
    @Test
    public void leftToClearOnCleared() {
        smallInjectedGridOne.clear(new Coordinate(1, 0));
        assertEquals(0, smallInjectedGridOne.leftToClear());
    }
    
    @Test
    public void gridWidth() {
        assertEquals(30, randomGameGrid.getWidth());
    }
    
    @Test
    public void gridHeight() {
        assertEquals(16, randomGameGrid.getHeight());
    }
    
    @Test
    public void validCoordIsValid() {
        assertEquals(true, randomGameGrid.isValid(new Coordinate(15, 29)));
    }
    
    @Test
    public void invalidCoordIsInvalid() {
        assertEquals(false, randomGameGrid.isValid(new Coordinate(16, 30)));
    }
    
    @Test
    public void isClearedReturnsTrueWhenChecked() {
        assertEquals(true, smallInjectedGridOne.isCleared(new Coordinate(0, 0)));
    }
    
    @Test
    public void isClearedReturnsFalseWhenUnchecked() {
        assertEquals(false, smallInjectedGridOne.isCleared(new Coordinate(1, 0)));
    }
    
    @Test
    public void isClearedReturnsFalseOnUnstartedGame() {
        assertEquals(false, randomGameWithSomeExtraMines.isCleared(new Coordinate(2, 2)));
    }
    
    @Test
    public void mineCount() {
        assertEquals(99, randomGameGrid.getMines());
    }
    
    @Test
    public void unstartedIsUnFlagged() {
        assertEquals(false, randomGameWithSomeExtraMines.isFlagged(new Coordinate(1, 1)));
    }
    
    @Test
    public void cantFlagUnstarted() {
        randomGameWithSomeExtraMines.toggleFlag(new Coordinate(1, 1));
        assertEquals(false, randomGameWithSomeExtraMines.isFlagged(new Coordinate(1, 1)));
    }
    
    @Test
    public void flagToggle() {
        smallInjectedGridOne.toggleFlag(new Coordinate(0, 0));
        assertEquals(true, smallInjectedGridOne.isFlagged(new Coordinate(0, 0)));
    }
}