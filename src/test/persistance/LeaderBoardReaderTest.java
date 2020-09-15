package persistance;

import model.LeaderBoard;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class LeaderBoardReaderTest {

    private LeaderBoard leaderBoard;
    private LeaderBoardReader reader;

    @Test
    public void testParseLeaderBoardFile1() {
        reader = new LeaderBoardReader();

        try {
            leaderBoard = reader.loadFromFile(new File("./data/testLeaderBoardFile1.json"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(5, leaderBoard.getNumEntries());
        assertEquals("Harry", leaderBoard.getEntry(1).getNickname());
        assertEquals(8, leaderBoard.getEntry(1).getScore());
        assertEquals("Parry", leaderBoard.getEntry(2).getNickname());
        assertEquals(5, leaderBoard.getEntry(2).getScore());
        assertEquals("Larry", leaderBoard.getEntry(3).getNickname());
        assertEquals(4, leaderBoard.getEntry(3).getScore());
        assertEquals("Barry", leaderBoard.getEntry(4).getNickname());
        assertEquals(0, leaderBoard.getEntry(4).getScore());
        assertEquals("Carry", leaderBoard.getEntry(5).getNickname());
        assertEquals(0, leaderBoard.getEntry(5).getScore());
    }

}
