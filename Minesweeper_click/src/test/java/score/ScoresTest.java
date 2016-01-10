package score;

import java.nio.file.Path;
import java.nio.file.Paths;
import minesweeper.score.Scores;
import minesweeper.util.GameType;
import org.fest.util.Files;
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
public class ScoresTest {
    
    public ScoresTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Path p = Paths.get(".", "data", "highscores.dat");
        try {
            Files.delete(p.toFile());
        } catch (Exception e) {
        }
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
    public void scoresTest() {
        Scores s = new Scores();
        assertEquals("", s.toString());
        assertEquals(true, s.isNewRecord(GameType.CUSTOM, 5));
        s.setRecord(GameType.CUSTOM, 5, "test");
        assertEquals("CUSTOM:\n actions: 5\n test", s.toString());
        assertEquals(false, s.isNewRecord(GameType.CUSTOM, 5));
        assertEquals(true, s.isNewRecord(GameType.CUSTOM, 4));
        s.setRecord(GameType.EASY, 15, "test");
        Scores s2 = new Scores();
        assertEquals("EASY:\n actions: 15\n test\n\nCUSTOM:\n actions: 5\n test", s2.toString());
        s2.clear();
        assertEquals("", s2.toString());
    }
}
