package persistance;

import model.WordBank;

import java.io.File;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class WordBankReaderTest {

    private static final File testWordBankFile1 = new File("./data/testWordBankFile1.json");
    private WordBank bank;
    private Reader reader;

    @Test
    public void testParseWordBankFile1() throws IOException, ParseException {
        reader = new WordBankReader();
        bank = (WordBank) reader.loadFromFile(testWordBankFile1);
        assertEquals(3, bank.getSize());
        assertTrue(bank.contains("peacock"));
        assertTrue(bank.contains("spanish"));
        assertTrue(bank.contains("grayscale"));
    }

}
