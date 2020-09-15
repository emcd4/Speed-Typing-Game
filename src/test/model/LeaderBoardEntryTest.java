package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LeaderBoardEntryTest {

    LeaderBoardEntry testEntry;

    @BeforeEach
    public void setUp() {
        testEntry = new LeaderBoardEntry("Jim", 100);
    }

    @Test
    public void testConstructor() {
        assertEquals("Jim", testEntry.getNickname());
        assertEquals("Jim: 100", testEntry.getEntryAsString());
        assertEquals(100, testEntry.getScore());
    }
}
