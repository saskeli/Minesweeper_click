package score;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweeper.score.Score;
import minesweeper.util.GameType;

/**
 *
 * @author Saskeli
 */
public class ScoreTest {
    private Score s;
    
    public ScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        s = new Score("test", 15, GameType.EASY);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructor() {
        assertEquals("test", s.getName());
        assertEquals(15, s.getActions());
        assertEquals(GameType.EASY, s.getGameType());
    }
    
    @Test
    public void toStringTest() {
        assertEquals("EASY:\n actions: 15\n test", s.toString());
    }
}
