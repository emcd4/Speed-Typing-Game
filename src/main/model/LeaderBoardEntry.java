package model;

/**
 * Represents a leader board entry with a nickname and a score
 */
public class LeaderBoardEntry {
    private String nickname;
    private int score;

    // EFFECTS: creates a new leader board entry with a nickname and a score
    public LeaderBoardEntry(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    // EFFECTS: returns the nickname
    public String getNickname() {
        return nickname;
    }

    // EFFECTS: returns the score
    public int getScore() {
        return score;
    }

    // EFFECTS: returns the entry as a string in form nickname: 0
    public String getEntryAsString() {
        return nickname + ": " + score;
    }

}