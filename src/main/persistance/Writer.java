package persistance;

import java.io.File;
import java.io.IOException;

/**
 * Writes information to a file
 */
public interface Writer {

    // MODIFIES: file
    // EFFECTS: saves data to given file
    void save(File file) throws IOException;

}
