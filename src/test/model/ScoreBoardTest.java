package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ScoreBoardTest {

    ScoreBoard testBoard;

    @BeforeEach
    public void setUp() {
        testBoard = new ScoreBoard();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testBoard.getScore());
    }

    @Test
    public void testIncrementScore() {
        testBoard.incrementScore();
        assertEquals(1, testBoard.getScore());

        for (int i=0; i<5; i++) {
            testBoard.incrementScore();
        }
        assertEquals(6, testBoard.getScore());
    }

    @Test
    public void testReset() {
        testBoard.reset();
        assertEquals(0, testBoard.getScore());
    }

}
