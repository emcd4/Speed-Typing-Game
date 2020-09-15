package persistance;

import model.LeaderBoard;
import model.LeaderBoardEntry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Reader that can read leader board information from a JSON file
 */
public class LeaderBoardReader implements Reader {

    // REQUIRES: well-formed file with leader board data
    // EFFECTS: returns a LeaderBoard of the leader board information parsed from file
    public LeaderBoard loadFromFile(File file) throws IOException, ParseException {
        List<LeaderBoardEntry> entries = new ArrayList<>();

        JSONParser parser = new JSONParser();

        FileReader reader = new FileReader(file);

        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray jsonArrayEntries = (JSONArray) jsonObject.get("leaderBoard");

        for (Object obj : jsonArrayEntries) {
            JSONObject leaderBoardEntryObject = (JSONObject) obj;
            String nickName = (String) leaderBoardEntryObject.get("nickName");
            int score = (int) (long) leaderBoardEntryObject.get("score");
            entries.add(new LeaderBoardEntry(nickName, score));
        }

        return new LeaderBoard(entries);
    }

}
