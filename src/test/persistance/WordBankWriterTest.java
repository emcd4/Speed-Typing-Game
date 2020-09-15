package persistance;

import model.WordBank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class WordBankWriterTest {
    private Writer testWriter;
    private File testWordBankFile;
    private WordBank testWordBank;
    private List<String> words;
    private Reader reader;

    @BeforeEach
    public void setUp() {
        words = new ArrayList<>();
        words.add("cat");
        words.add("dog");
        words.add("orange");
        testWordBank = new WordBank(words);

        testWordBankFile = new File("./data/testWordBank.json");
        testWriter = new WordBankWriter(testWordBank);
        reader = new WordBankReader();
    }

    @Test
    public void testWriteWordBank() {
        try {
            testWriter.save(testWordBankFile);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        testWordBank.clear();

        // read back to verify expected values
        try {
            testWordBank = (WordBank) reader.loadFromFile(testWordBankFile);
            assertEquals(3, testWordBank.getSize());
            assertTrue(testWordBank.contains("cat"));
            assertTrue(testWordBank.contains("dog"));
            assertTrue(testWordBank.contains("orange"));
        } catch (ParseException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
