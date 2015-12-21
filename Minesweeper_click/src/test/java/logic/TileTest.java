package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweeper.logic.*;

public class TileTest {
    
    public TileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructorWorks() {
        Tile t = new Tile(true, false);
        assertEquals(true, t.isMine());
        assertEquals(false, t.isChecked());
    }
    
    @Test
    public void canChangeMineSetting() {
        Tile t = new Tile(true, false);
        assertEquals(true, t.isMine());
        t.setMine(false);
        assertEquals(false, t.isMine());
    }
    
    @Test
    public void canChangeCheckedSetting() {
        Tile t = new Tile(true, false);
        assertEquals(false, t.isChecked());
        t.setChecked(true);
        assertEquals(true, t.isChecked());
    }
    
    @Test
    public void toStringTestHiddenMine() {
        Tile t = new Tile(true, false);
        assertEquals("*", t.toString());
    }
    
    @Test
    public void toStringTestClearedMine() {
        Tile t = new Tile(true, true);
        assertEquals("¤", t.toString());
    }
    
    @Test
    public void toStringTestHiddenNonMine() {
        Tile t = new Tile(false, false);
        assertEquals("#", t.toString());
    }
    
    @Test
    public void toStringTestClearedNonMine() {
        Tile t = new Tile(false, true);
        assertEquals(" ", t.toString());
    }
}
