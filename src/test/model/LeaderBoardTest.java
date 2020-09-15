package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class LeaderBoardTest {

    private LeaderBoard testBoard;
    private List<LeaderBoardEntry> entries;

    @BeforeEach
    public void setUp() {
        entries = new ArrayList<>();
        entries.add(new LeaderBoardEntry("Harry", 8));
        entries.add(new LeaderBoardEntry("Parry", 5));
        entries.add(new LeaderBoardEntry("Larry", 4));
        entries.add(new LeaderBoardEntry("Barry", 1));
        entries.add(new LeaderBoardEntry("Carry", 1));
        testBoard = new LeaderBoard(entries);
    }

    @Test
    public void testConstructor() {
        assertEquals(5, testBoard.getNumEntries());
    }

    @Test
    public void testAddEntryAddTop() {
        LeaderBoardEntry entryGarry = new LeaderBoardEntry("Garry", 9);

        testBoard.addEntry(entryGarry);
        LeaderBoardEntry entry = testBoard.getEntry(1);
        assertEquals(entryGarry.getNickname(), entry.getNickname());
        assertEquals(entryGarry.getScore(), entry.getScore());
        assertEquals(6, testBoard.getNumEntries());
    }

    @Test
    public void testAddEntryAddMiddle() {
        LeaderBoardEntry entryGarry = new LeaderBoardEntry("Garry", 6);

        testBoard.addEntry(entryGarry);
        LeaderBoardEntry entry = testBoard.getEntry(2);
        assertEquals(entryGarry.getNickname(), entry.getNickname());
        assertEquals(entryGarry.getScore(), entry.getScore());
        assertEquals(6, testBoard.getNumEntries());
    }

    @Test
    public void testAddEntryAddSameAsLowestScore() {
        LeaderBoardEntry entryGary = new LeaderBoardEntry("Garry", 1);

        testBoard.addEntry(entryGary);

        assertEquals("Harry", testBoard.getEntry(1).getNickname());
        assertEquals("Parry", testBoard.getEntry(2).getNickname());
        assertEquals("Larry", testBoard.getEntry(3).getNickname());
        assertEquals("Barry", testBoard.getEntry(4).getNickname());
        assertEquals("Carry", testBoard.getEntry(5).getNickname());
        assertEquals("Garry", testBoard.getEntry(6).getNickname());
        assertEquals(6, testBoard.getNumEntries());
    }

    @Test
    public void testAddEntryAddLowerThanLowestScore() {
        LeaderBoardEntry entryGary = new LeaderBoardEntry("Garry", 0);

        testBoard.addEntry(entryGary);

        assertEquals("Harry", testBoard.getEntry(1).getNickname());
        assertEquals("Parry", testBoard.getEntry(2).getNickname());
        assertEquals("Larry", testBoard.getEntry(3).getNickname());
        assertEquals("Barry", testBoard.getEntry(4).getNickname());
        assertEquals("Carry", testBoard.getEntry(5).getNickname());
        assertEquals("Garry", testBoard.getEntry(6).getNickname());
        assertEquals(6, testBoard.getNumEntries());
    }

    @Test
    public void testClear() {
        testBoard.clear();
        assertEquals(0, testBoard.getNumEntries());
    }

    @Test
    public void testGetLeaderBoardAsListString() {
        List<String> expected = new ArrayList<>();
        expected.add("1. Harry: 8");
        expected.add("2. Parry: 5");
        expected.add("3. Larry: 4");
        expected.add("4. Barry: 1");
        expected.add("5. Carry: 1");

        List<String> given = testBoard.getLeaderBoardAsListString();

        for (int i = 0; i < testBoard.getNumEntries(); i++) {
            assertEquals(expected.get(i), given.get(i));
        }
    }

}
