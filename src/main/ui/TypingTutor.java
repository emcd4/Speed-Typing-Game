package ui;


import model.*;
import persistance.*;

import java.io.File;
import java.io.IOException;

/**
 * Represents typing tutor application with a score board, leader board, and word bank
 */
public class TypingTutor {

    private static final File WORD_BANK_FILE = new File("./data/wordBank.json");
    private static final File LEADER_BOARD_FILE = new File("./data/leaderBoard.json");

    private ScoreBoard scoreBoard;
    private LeaderBoard leaderBoard;
    private WordBank wordBank;

    // EFFECTS: constructs a new typing tutor with loaded leader board, loaded word bank and a new score board
    public TypingTutor() {
        leaderBoard = loadLeaderBoard();
        wordBank = loadWordBank();
        scoreBoard = new ScoreBoard();
    }

    // MODIFIES: this
    // EFFECTS: resets for a new game
    public void startGame() {
        wordBank.reset();
        scoreBoard.reset();
    }

    // MODIFIES: this
    // EFFECTS: returns a word bank with information loaded from WORD_BANK_FILE
    private WordBank loadWordBank() {
        WordBank wordBank = null;
        WordBankReader wordBankReader = new WordBankReader();

        try {
            wordBank = wordBankReader.loadFromFile(WORD_BANK_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordBank;
    }

    // MODIFIES: this
    // EFFECTS: return leader board with information loaded from LEADER_BOARD_FILE
    private LeaderBoard loadLeaderBoard() {
        LeaderBoard leaderBoard = null;
        LeaderBoardReader leaderBoardReader = new LeaderBoardReader();

        try {
            leaderBoard = leaderBoardReader.loadFromFile(LEADER_BOARD_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leaderBoard;
    }

    // MODIFIES: WORD_BANK_FILE
    // EFFECTS: saves this word bank to file
    public void saveWordBank() {
        WordBankWriter wordBankWriter = new WordBankWriter(wordBank);
        try {
            wordBankWriter.save(WORD_BANK_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: LEADER_BOARD_FILE
    // EFFECTS: saves this leader board to file
    public void saveLeaderBoard() {
        LeaderBoardWriter leaderBoardWriter = new LeaderBoardWriter(leaderBoard);
        try {
            leaderBoardWriter.save(LEADER_BOARD_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: returns leader board
    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    // EFFECTS: returns word bank
    public WordBank getWordBank() {
        return wordBank;
    }

    // EFFECTS: returns score board
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    // MODIFIES: this
    // EFFECTS: returns the next word to be typed in the game
    public String getNextWord() {
        return wordBank.getNextWord();
    }

    // EFFECTS: returns the score from score board
    public int getScore() {
        return scoreBoard.getScore();
    }

    // MODIFIES: this
    // EFFECTS: increments the scoreboard's score
    public void incrementScore() {
        scoreBoard.incrementScore();
    }

    // MODIFIES: this
    // EFFECTS: adds leader board entry to leader board with given name and score board's current score
    public void updateLeaderBoard(String name) {
        leaderBoard.addEntry(new LeaderBoardEntry(name, scoreBoard.getScore()));
    }
}
