package minesweeper.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains methods for writing and reading serializable objects.
 * 
 * @author Saskeli
 */
public final class ObjectStorage {

    private ObjectStorage() { } //Private constructor to prevent object initialization.
    
    /**
     * Writes a serializable object to disk.
     * 
     * @param storable  the object to be stored
     * @param fileName  the object will be stored in ./data/fileName
     * @return          true if successful, otherwise false
     */
    public static boolean storeObject(Serializable storable, String fileName) {
        Path p = Paths.get(".", "data");
        if (Files.notExists(p)) {
            try {
                Files.createDirectory(p);
            } catch (Exception e) {
                return false;
            }
        }
        p = Paths.get(".", "data", fileName);
        try (FileOutputStream fw = new FileOutputStream(p.toFile());
            ObjectOutputStream ow = new ObjectOutputStream(fw)) {
            ow.writeObject(storable);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Reads a serializable object from disk.
     * 
     * @param fileName  the object will be read from ./data/fileName
     * @return          a serializable object if successful, otherwise null
     */
    public static Serializable retrieveObject(String fileName) {
        Path p = Paths.get(".", "data", fileName);
        if (!Files.isReadable(p)) {
            return null;
        }
        try (FileInputStream fr = new FileInputStream(p.toFile());
                ObjectInputStream or = new ObjectInputStream(fr)) {
            return (Serializable) or.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
