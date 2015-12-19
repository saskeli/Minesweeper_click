/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saska
 */
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
        c = new Coordinate(1, 2);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void constructorWorks() {
        assertEquals(1, c.getColumn());
        assertEquals(2, c.getRow());
    }
    
    @Test
    public void equlsWorks() {
        Coordinate other = new Coordinate(1, 2);
        assertEquals(true, c.equals(other));
    }
    
    @Test
    public void equlsWorksWithDifferentRow() {
        Coordinate other = new Coordinate(1, 3);
        assertEquals(false, c.equals(other));
    }
    
    @Test
    public void equalsWorksWithDifferent() {
        Coordinate other = new Coordinate(2, 1);
        assertEquals(false, c.equals(other));
    }
    
    @Test
    public void equalsWithNull() {
        assertEquals(false, c.equals(null));
    }
    
    @Test
    public void equalsWithOtherObject() {
        assertEquals(false, c.equals(new ArrayList<String>()));
    }
    
    @Test
    public void hashCodeWorks() {
        Coordinate other = new Coordinate(1, 2);
        assertEquals(c.hashCode(), other.hashCode());
    }
    
    @Test
    public void hashCodeWorksWithDifferent() {
        Coordinate other = new Coordinate(2, 1);
        assertEquals(false, c.hashCode() == other.hashCode());
    }
}
