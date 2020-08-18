package datalink;

import java.io.File;
import java.io.IOException;

public class DataLinkerFactory {

    public static DataLinker getSingle(String filename) {
        File file = new File(filename);
        if (file.isDirectory()) {
            return null;
        }
        if (!file.exists()) {
            boolean createSucceeded = false;
            try {
                createSucceeded = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return createSucceeded ? new SingleDataLinker(file) : null;
        }
        return new SingleDataLinker(file);
    }

    public static DataLinker getMulti(String filename) {
        File file = new File(filename);
        if (file.isDirectory()) {
            return null;
        }
        if (!file.exists()) {
            boolean createSucceeded = false;
            try {
                createSucceeded = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return createSucceeded ? new MultiDataLinker(file) : null;
        }
        return new MultiDataLinker(file);
    }

}
