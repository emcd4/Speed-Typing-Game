package persistance;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

/**
 * Reads information from a file
 */
public interface Reader {

    // EFFECTS: loads information from file and returns it as object
    Object loadFromFile(File file) throws IOException, ParseException;

}
