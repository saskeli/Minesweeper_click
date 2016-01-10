package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import minesweeper.util.ObjectStorage;
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
public class ObjectStorageTest {
    
    public ObjectStorageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        Path p = Paths.get(".", "data", "test.data");
        try {
            Files.delete(p.toFile());
        } catch (Exception e) {
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void storeAndRetrieve() {
        assertEquals(null, ObjectStorage.retrieveObject("test.data"));
        ArrayList<String> l = new ArrayList<>();
        l.add("test");
        l.add("nother");
        ObjectStorage.storeObject(l, "test.data");
        ArrayList<String> l2 = (ArrayList<String>) ObjectStorage.retrieveObject("test.data");
        assertEquals(l, l2);
    }
}
