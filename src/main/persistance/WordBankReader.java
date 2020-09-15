package persistance;

import model.WordBank;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reader that can read word bank information from a json file
 */
public class WordBankReader implements Reader {

    // REQUIRES: well-formed file with word bank data
    // EFFECTS: returns a WordBank of the words parsed from the file
    public WordBank loadFromFile(File file) throws IOException, ParseException {
        List<String> words = new ArrayList<>();

        JSONParser parser = new JSONParser();

        FileReader reader = new FileReader(file);

        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray jsonArray = (JSONArray) jsonObject.get("words");

        for (Object wordObj : jsonArray) {
            words.add((String) wordObj);
        }

        return new WordBank(words);
    }

}
