package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {


    Leaderboard testLB;
    Grid gridTest;
    Player p1;
    Player p2;
    Player p3;

    @BeforeEach
    public void setup() {

        testLB = new Leaderboard("Game Leaderboard");
        p1 = new Player("aa", 1);
        p2 = new Player("bb", 2);
        p3 = new Player("cc", 3);

    }

    //test addPlayers
    @Test
    public void testAddOnePlayer() {
        testLB.addPlayers(p1);
        assertTrue(testLB.containPlayers(p1));
        assertFalse(testLB.containPlayers(p2));
        assertFalse(testLB.containPlayers(p3));
        assertEquals(1, testLB.numOfPlayers());
    }

    @Test
    public void testAddTwoPlayers() {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        assertTrue(testLB.containPlayers(p1));
        assertTrue(testLB.containPlayers(p2));
        assertFalse(testLB.containPlayers(p3));
        assertEquals(2, testLB.numOfPlayers());
    }

    @Test
    public void testAddThreePlayers() {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        assertTrue(testLB.containPlayers(p1));
        assertTrue(testLB.containPlayers(p2));
        assertTrue(testLB.containPlayers(p3));
        assertEquals(3, testLB.numOfPlayers());
    }

    // test numOfPlayers
    @Test
    public void testNumOfPlayers1 () {
        testLB.addPlayers(p1);
        assertEquals(1, testLB.numOfPlayers());
    }

    @Test
    public void testNumOfPlayers3 () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        assertEquals(3, testLB.numOfPlayers());
    }
}
