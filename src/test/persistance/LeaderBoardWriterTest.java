package persistance;

import model.LeaderBoard;
import model.LeaderBoardEntry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeaderBoardWriterTest {
    private Writer testWriter;
    private File testLeaderBoardFile;
    private LeaderBoard testLeaderBoard;
    private List<LeaderBoardEntry> entries;
    private Reader reader;

    @BeforeEach
    public void setUp() {
        entries = new ArrayList<>();
        entries.add(new LeaderBoardEntry("Monica", 5));
        entries.add(new LeaderBoardEntry("Ross", 4));
        entries.add(new LeaderBoardEntry("Chandler", 3));
        entries.add(new LeaderBoardEntry("Rachel", 2));
        entries.add(new LeaderBoardEntry("Phoebe", 1));
        testLeaderBoard = new LeaderBoard(entries);

        testLeaderBoardFile = new File("./data/testLeaderBoard.json");
        testWriter = new LeaderBoardWriter(testLeaderBoard);
        reader = new LeaderBoardReader();
    }

    @Test
    public void testWriteLeaderBoard() {
        try {
            testWriter.save(testLeaderBoardFile);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        testLeaderBoard.clear();

        // read back to verify expected values
        try {
            testLeaderBoard = (LeaderBoard) reader.loadFromFile(testLeaderBoardFile);
        } catch (ParseException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertEquals(5, testLeaderBoard.getNumEntries());
        assertEquals("Monica", testLeaderBoard.getEntry(1).getNickname());
        assertEquals(5, testLeaderBoard.getEntry(1).getScore());
        assertEquals("Ross", testLeaderBoard.getEntry(2).getNickname());
        assertEquals(4, testLeaderBoard.getEntry(2).getScore());
        assertEquals("Chandler", testLeaderBoard.getEntry(3).getNickname());
        assertEquals(3, testLeaderBoard.getEntry(3).getScore());
        assertEquals("Rachel", testLeaderBoard.getEntry(4).getNickname());
        assertEquals(2, testLeaderBoard.getEntry(4).getScore());
        assertEquals("Phoebe", testLeaderBoard.getEntry(5).getNickname());
        assertEquals(1, testLeaderBoard.getEntry(5).getScore());
    }
}
