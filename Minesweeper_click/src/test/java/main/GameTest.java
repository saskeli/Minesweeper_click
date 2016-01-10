package main;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import minesweeper.logic.GameGrid;
import minesweeper.logic.Tile;
import minesweeper.main.Game;
import minesweeper.util.Coordinate;
import minesweeper.util.GameType;
import objectinjection.Grid;
import static objectinjection.Grid.gameGrid;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saskeli
 */
public class GameTest {

    private Game testGame;
    private Game tinyGame;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testGame = new Game(Grid.gameGrid());
        tinyGame = new Game(new GameGrid(
                new Tile[][]{
                    {new Tile(false, false), new Tile(true, false)},
                    {new Tile(false, false), new Tile(true, false)}}));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void customConstructor() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        Game game = new Game(7, 9, 10);
        assertEquals(0, game.getActionCount());
        assertEquals(7, game.gameWidth());
        assertEquals(9, game.gameHeight());
        assertEquals(-1, game.getRemainingTiles());
        assertEquals(GameType.CUSTOM, game.getGameType());
        assertEquals(false, game.isChecked(new Coordinate(3, 3)));
        assertEquals(0, game.getTileState(new Coordinate(3, 3)));
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void emptyConstructor() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        Game game = new Game();
        assertNotEquals(ft, Files.getLastModifiedTime(p));
        assertEquals(0, game.getActionCount());
        assertEquals(30, game.gameWidth());
        assertEquals(16, game.gameHeight());
        assertEquals(-1, game.getRemainingTiles());
        assertEquals(GameType.HARD, game.getGameType());
        assertEquals(false, game.isChecked(new Coordinate(3, 3)));
        assertEquals(0, game.getTileState(new Coordinate(3, 3)));
    }

    @Test
    public void gameTypeConstructor() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        Game game = new Game(GameType.NORMAL);
        assertEquals(0, game.getActionCount());
        assertEquals(16, game.gameWidth());
        assertEquals(16, game.gameHeight());
        assertEquals(-1, game.getRemainingTiles());
        assertEquals(GameType.NORMAL, game.getGameType());
        assertEquals(false, game.isChecked(new Coordinate(3, 3)));
        assertEquals(0, game.getTileState(new Coordinate(3, 3)));
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void injectionConstructor() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame = new Game(gameGrid());
        assertEquals(0, testGame.getActionCount());
        assertEquals(16, testGame.gameWidth());
        assertEquals(16, testGame.gameHeight());
        assertEquals(216, testGame.getRemainingTiles());
        assertEquals(GameType.CUSTOM, testGame.getGameType());
        assertEquals(false, testGame.isChecked(new Coordinate(3, 3)));
        assertEquals(0, testGame.getTileState(new Coordinate(3, 3)));
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void clearDefault() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        Coordinate c = new Coordinate(6, 15);
        testGame.clear(c);
        assertEquals(true, testGame.isChecked(c));
        assertEquals(170, testGame.getRemainingTiles());
        assertEquals(0, testGame.getTileState(c));
        assertEquals(0, testGame.getTileState(new Coordinate(0, 0)));
        assertEquals(false, testGame.isFlagged(new Coordinate(0, 0)));
        assertEquals(1, testGame.getActionCount());
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void cantCleariIfGameOver() {
        Coordinate c = new Coordinate(6, 15);
        testGame.clear(new Coordinate(6, 12));
        assertEquals(false, testGame.isChecked(c));
        testGame.clear(c);
        assertEquals(false, testGame.isChecked(c));
    }

    @Test
    public void cantClearIfCleared() {
        Coordinate c = new Coordinate(6, 15);
        testGame.clear(c);
        testGame.clear(c);
        assertEquals(true, testGame.isChecked(c));
    }

    @Test
    public void cantClearIfGameWon() {
        tinyGame.clear(new Coordinate(0, 0));
        tinyGame.clear(new Coordinate(1, 0));
        Coordinate c = new Coordinate(0, 1);
        tinyGame.clear(c);
        assertEquals(false, tinyGame.isChecked(c));
    }

    @Test
    public void newCustomGameWithTooSmallParameters() {
        testGame.newGame(0, 0, 0);
        assertEquals(16, testGame.gameHeight());
        assertEquals(30, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
    }

    @Test
    public void newCustomGameWithTooBigParameters() {
        testGame.newGame(100, 100, 9999999);
        assertEquals(16, testGame.gameHeight());
        assertEquals(30, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
    }

    @Test
    public void newCustomGameWithSmallParameters() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame.newGame(1, 1, 1);
        assertEquals(1, testGame.gameHeight());
        assertEquals(1, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void newCustomGameWithLargeParameters() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame.newGame(45, 32, 99);
        assertEquals(32, testGame.gameHeight());
        assertEquals(45, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void newCustomGameWithOneMine() {
        testGame.newGame(2, 2, 1);
        assertEquals(2, testGame.gameHeight());
        assertEquals(2, testGame.gameWidth());
        Coordinate c = new Coordinate(0, 0);
        testGame.clear(c);
        assertEquals(1, testGame.getTileState(c));
    }

    @Test
    public void newGameByGameType() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame.newGame(GameType.EASY);
        assertEquals(9, testGame.gameHeight());
        assertEquals(9, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void newCustomGameWithoutParams() {
        Coordinate c = new Coordinate(6, 15);
        testGame.clear(c);
        testGame.newGame();
        assertEquals(16, testGame.gameHeight());
        assertEquals(16, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
        assertEquals(false, testGame.isChecked(c));
    }

    @Test
    public void newGameWithoutParams() throws IOException {
        Coordinate c = new Coordinate(0, 0);
        testGame.newGame(GameType.EASY);
        testGame.clear(c);
        testGame.newGame();
        assertEquals(false, testGame.isChecked(c));
        assertEquals(9, testGame.gameHeight());
        assertEquals(9, testGame.gameWidth());
        assertEquals(0, testGame.getActionCount());
    }

    @Test
    public void toggleFlag() throws IOException, InterruptedException {
        tinyGame.clear(new Coordinate(0, 0));
        Coordinate c = new Coordinate(1, 0);
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        tinyGame.toggleFlag(c);
        assertEquals(true, tinyGame.isFlagged(c));
        assertNotEquals(ft, Files.getLastModifiedTime(p));
        tinyGame.toggleFlag(c);
        tinyGame.clear(c);
        Coordinate c2 = new Coordinate(0, 1);
        tinyGame.toggleFlag(c2);
        assertEquals(false, tinyGame.isFlagged(c2));

    }

    @Test
    public void toggleFlagOnGameOver() {
        tinyGame.clear(new Coordinate(0, 0));
        tinyGame.clear(new Coordinate(0, 1));
        Coordinate c = new Coordinate(1, 0);
        tinyGame.toggleFlag(c);
        assertEquals(false, tinyGame.isFlagged(c));
    }

    @Test
    public void clearSurroungingDefault() throws IOException, InterruptedException {
        testGame.clear(new Coordinate(6, 15));
        testGame.toggleFlag(new Coordinate(9, 8));
        testGame.clearSurrounding(new Coordinate(10, 9));
        assertEquals(false, testGame.isChecked(new Coordinate(10, 9)));
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame.clearSurrounding(new Coordinate(9, 9));
        assertEquals(true, testGame.isChecked(new Coordinate(10, 9)));
        assertEquals(2, testGame.getActionCount());
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }

    @Test
    public void clearSurroungingGameOver() throws IOException, InterruptedException {
        Path p = Paths.get(".", "data", "savegame.dat");
        FileTime ft = Files.getLastModifiedTime(p);
        Thread.sleep(10);
        testGame.clear(new Coordinate(6, 15));
        assertEquals(true, testGame.isChecked(new Coordinate(6, 15)));
        testGame.toggleFlag(new Coordinate(9, 8));
        testGame.toggleFlag(new Coordinate(2, 13));
        testGame.clearSurrounding(new Coordinate(3, 14));
        assertEquals(true, testGame.isChecked(new Coordinate(3, 13)));
        testGame.clearSurrounding(new Coordinate(9, 9));
        assertEquals(false, testGame.isChecked(new Coordinate(10, 9)));
        assertNotEquals(ft, Files.getLastModifiedTime(p));
    }
}
