package logic;

import minesweeper.logic.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {
    
    public CoordinateTest() {
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
    public void getSurroundingCoordinates() {
        Coordinate c = new Coordinate(8, 15);
        assertEquals(8, c.getAdjacentCoordinates(new Coordinate(15, 29)).size());
    }
    
    @Test
    public void getSurroundingCoordinatesOnEdge() {
        Coordinate c = new Coordinate(0, 15);
        assertEquals(5, c.getAdjacentCoordinates(new Coordinate(15, 29)).size());
    }
    
    @Test
    public void getSurroundingCoordinatesInUpperLeftCorner() {
        Coordinate c = new Coordinate(0, 0);
        assertEquals(3, c.getAdjacentCoordinates(new Coordinate(15, 29)).size());
    }
    
    @Test
    public void getSurroundingCoordinatesInLowerRightCorner() {
        Coordinate c = new Coordinate(15, 29);
        assertEquals(3, c.getAdjacentCoordinates(new Coordinate(15, 29)).size());
    }
    
}
