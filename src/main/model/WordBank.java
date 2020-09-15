package model;

import java.util.*;

/**
 * Stores all of the type-able words and keeps track of the words that have been seen since the last reset
 */
public class WordBank {

    private Set<String> bank;
    private Set<String> alreadySeen;

    // EFFECTS: creates a word bank with words from given list
    public WordBank(List<String> words) {
        bank = new HashSet<>();
        alreadySeen = new HashSet<>();
        for (String word : words) {
            bank.add(word);
        }
    }

    // REQUIRES: correctly-spelled, lower-case string
    // MODIFIES: this
    // EFFECTS: if word is not the empty string, is not already in the word bank, and is a single word, adds word to
    //          bank and returns true, otherwise, does nothing and returns false
    public boolean addWord(String word) {
        if (!word.isEmpty() && isSingleWord(word)) {
            return bank.add(word);
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if word is already in the word bank, removes the word and returns true, otherwise returns false
    public boolean removeWord(String word) {
        return bank.remove(word);
    }

    // EFFECTS: returns the size of the word bank
    public int getSize() {
        return bank.size();
    }

    // EFFECTS: returns true if word in found in word bank, otherwise false
    public boolean contains(String word) {
        return bank.contains(word);
    }

    // MODIFIES: this
    // EFFECTS: gets random word that hasn't already been seen in game
    public String getNextWord() {
        String nextWord;

        do {
            if (alreadySeen.size() == bank.size()) {
                reset();
            }

            Random rand = new Random();
            int index = rand.nextInt(bank.size());
            nextWord = (String) bank.toArray()[index];
        } while (alreadySeen.contains(nextWord));

        alreadySeen.add(nextWord);

        return nextWord;
    }

    // MODIFIES: this
    // EFFECTS: resets word bank for a new game
    public void reset() {
        alreadySeen.clear();
    }

    // MODIFIES: this
    // EFFECTS: removes all words from word bank
    public void clear() {
        bank.clear();
    }

    // EFFECTS: returns list of strings of all words in work bank
    public List<String> getAllWords() {
        return new ArrayList<>(bank);
    }

    // EFFECTS: returns true if the given string has no spaces in it
    private boolean isSingleWord(String word) {
        return (!word.contains(" "));
    }
}
