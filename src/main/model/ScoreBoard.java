package model;

/**
 * Represents a score board with a score
 */
public class ScoreBoard {
    private int score;

    // EFFECTS: creates a new scoreboard with initial score of zero
    public ScoreBoard() {
        score = 0;
    }

    // MODIFIES: this
    // EFFECTS: increments the score by 1
    public void incrementScore() {
        score++;
    }

    // EFFECTS: returns the score
    public int getScore() {
        return score;
    }

    // MODIFIES: this
    // EFFECTS: sets the score to zero
    public void reset() {
        score = 0;
    }

}
