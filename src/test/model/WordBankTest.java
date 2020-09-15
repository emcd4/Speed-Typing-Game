package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class WordBankTest {

    private WordBank testWordBank;
    private List<String> words;
    private int initSize;

    @BeforeEach
    public void setUp() {
        words = new ArrayList<>();
        words.add("feather");
        words.add("smattering");
        words.add("foreign");
        testWordBank = new WordBank(words);
        initSize = testWordBank.getSize();
    }

    @Test
    public void testConstructor() {
        assertEquals(3, initSize);
        assertTrue(testWordBank.contains("feather"));
        assertTrue(testWordBank.contains("smattering"));
        assertTrue(testWordBank.contains("foreign"));
    }

    @Test
    public void testAddWord() {
        assertFalse(testWordBank.contains("spite"));

        testWordBank.addWord("spite");
        assertTrue(testWordBank.contains("spite"));
        assertEquals(initSize + 1, testWordBank.getSize());

        assertFalse(testWordBank.contains("create"));

        testWordBank.addWord("create");
        assertTrue(testWordBank.contains("spite"));
        assertTrue(testWordBank.contains("create"));
        assertEquals(initSize + 2, testWordBank.getSize());
    }

    @Test
    public void testAddWordRepeat() {
        assertFalse(testWordBank.contains("emerald"));

        assertTrue(testWordBank.addWord("emerald"));
        assertTrue(testWordBank.contains("emerald"));
        assertEquals(initSize + 1, testWordBank.getSize());

        assertFalse(testWordBank.addWord("emerald"));
        assertTrue(testWordBank.contains("emerald"));
        assertEquals(initSize + 1, testWordBank.getSize());
    }

    @Test
    public void testAddWordTwoWordInOneString() {
        assertFalse(testWordBank.addWord("fairy dust"));
        assertFalse(testWordBank.contains("fairy dust"));
        assertEquals(initSize, testWordBank.getSize());
    }

    @Test
    public void testAddWordEmptyString() {
        assertFalse(testWordBank.addWord(""));
        assertFalse(testWordBank.contains(""));
        assertEquals(initSize, testWordBank.getSize());
    }

    @Test
    public void testRemoveWord() {
        testWordBank.addWord("crayons");

        assertTrue(testWordBank.contains("crayons"));
        assertEquals(initSize + 1, testWordBank.getSize());

        testWordBank.removeWord("crayons");

        assertFalse(testWordBank.contains("crayons"));
        assertEquals(initSize, testWordBank.getSize());
    }

    @Test
    public void testGetNextWord() {
        String nextWord;
        Set alreadySeen = new HashSet();

        for (int i=0; i<testWordBank.getSize(); i++) {
            nextWord = testWordBank.getNextWord();
            assertFalse(alreadySeen.contains(nextWord));
            assertTrue(alreadySeen.add(nextWord));
        }

        for (int i=0; i<testWordBank.getSize(); i++) {
            nextWord = testWordBank.getNextWord();
            assertTrue(alreadySeen.contains(nextWord));
        }
    }

    @Test
    public void testClearWordBank() {
        testWordBank.clear();
        assertEquals(0, testWordBank.getSize());
    }

    @Test
    public void testGetAllWords() {
        List<String> allWords = testWordBank.getAllWords();
        assertEquals(allWords.size(), testWordBank.getSize());
    }
}