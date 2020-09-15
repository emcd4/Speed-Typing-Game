package persistance;

import model.LeaderBoard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writer that can write leader board information to json file
 */
public class LeaderBoardWriter implements Writer {

    private LeaderBoard leaderBoard;

    // EFFECTS: constructs new leader board writer with given leader board
    public LeaderBoardWriter(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    // MODIFIES: file
    // EFFECTS: saves all information in this leader board to given file
    @Override
    public void save(File file) throws IOException {
        JSONArray leaderBoardEntriesObject = new JSONArray();
        for (int i = 1; i <= leaderBoard.getNumEntries(); i++) {
            leaderBoardEntriesObject.add(getDetails(i));
        }
        JSONObject leaderBoardObject = new JSONObject();
        leaderBoardObject.put("leaderBoard", leaderBoardEntriesObject);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(leaderBoardObject.toJSONString());
        fileWriter.close();
    }

    // EFFECTS: returns JSONObject with the details of leader board entry at given rank
    private JSONObject getDetails(int rank) {
        JSONObject leaderBoardEntryDetail = new JSONObject();
        leaderBoardEntryDetail.put("nickName", leaderBoard.getEntry(rank).getNickname());
        leaderBoardEntryDetail.put("score", leaderBoard.getEntry(rank).getScore());
        return leaderBoardEntryDetail;
    }

}
