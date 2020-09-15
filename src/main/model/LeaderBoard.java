package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a leader board with all leader board entries
 */
public class LeaderBoard {
    private List<LeaderBoardEntry> entries;

    // EFFECTS: creates leader board
    public LeaderBoard(List<LeaderBoardEntry> entries) {
        this.entries = entries;
    }

    // MODIFIES: this
    // EFFECTS: insert score into leader board at correct position
    public void addEntry(LeaderBoardEntry lbe) {
        int pos = 0;
        for (LeaderBoardEntry entry : entries) {
            if (lbe.getScore() > entry.getScore()) {
                entries.add(pos, lbe);
                return;
            }
            pos++;
        }
        entries.add(pos, lbe);
    }

    // EFFECTS: returns the leader board entry of given ranking
    public LeaderBoardEntry getEntry(int rank) {
        return entries.get(rank - 1);
    }

    // EFFECTS: returns the number of entries in leader board
    public int getNumEntries() {
        return entries.size();
    }

    // MODIFIES: this
    // EFFECTS: clears the leader board
    public void clear() {
        entries.clear();
    }

    // EFFECTS: returns the leader board as a list of strings
    public List<String> getLeaderBoardAsListString() {
        List<String> leaderBoardEntries = new ArrayList<>();
        for (int i = 1; i <= getNumEntries(); i++) {
            String str = i + ". " + getEntry(i).getEntryAsString();
            leaderBoardEntries.add(str);
        }

        return leaderBoardEntries;
    }
}
