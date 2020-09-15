package persistance;

import model.WordBank;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Writer that can write word bank information to json file
 */
public class WordBankWriter implements Writer {

    private WordBank wordBank;

    // EFFECTS: creates new WordBankWriter with given wordBank
    public WordBankWriter(WordBank wordBank) {
        this.wordBank = wordBank;
    }

    // MODIFIES: file
    // EFFECTS: saves all information in this word bank to given file
    @Override
    public void save(File file) throws IOException {
        JSONObject wordsToSaveObject = new JSONObject();
        List<String> wordsToSave = new ArrayList<>();
        for (String word : wordBank.getAllWords()) {
            wordsToSave.add(word);
        }
        wordsToSaveObject.put("words", wordsToSave);

        FileWriter fileWriter = new FileWriter(file);
        String str = wordsToSaveObject.toJSONString();
        fileWriter.write(str);
        fileWriter.close();
    }
}
