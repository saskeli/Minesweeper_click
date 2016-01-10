package util;

import minesweeper.util.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {
    private Coordinate c;
    
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
        c = new Coordinate(4, 6);
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
    
    @Test
    public void coordinateHashCodeWorksWhenEquals() {
        Coordinate d = new Coordinate(4, 6);
        assertEquals(24345, d.hashCode());
    }
    
    @Test
    public void coodinateHashCodeWorksWhenNotEqual() {
        Coordinate d = new Coordinate(6, 4);
        assertEquals(false, c.hashCode() == d.hashCode());
    }
    
    @Test
    public void doesNotEqualNull() {
        assertEquals(false, c.equals(null));
    }
    
    @Test
    public void doesNotEqualNoncoordinate() {
        assertEquals(false, c.equals("bla"));
    }
}
