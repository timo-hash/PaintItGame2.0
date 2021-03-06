package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {


    private Leaderboard testLB;
    private Player p1;
    private Player p2;
    private Player p3;

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

    // test containPlayers
    @Test
    public void testContainPlayers0 () {
        assertFalse(testLB.containPlayers(p1));
        assertFalse(testLB.containPlayers(p2));
    }

    @Test
    public void testContainPlayers1 () {
        testLB.addPlayers(p1);
        assertTrue(testLB.containPlayers(p1));
        assertFalse(testLB.containPlayers(p2));
    }

    //test getIthPlayer
    @Test
    public void testGet1stPlayer () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        assertEquals("aa", testLB.getIthPlayer(0).getName());
    }

    @Test
    public void testGet3rdPlayer () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        assertEquals("cc", testLB.getIthPlayer(2).getName());
    }


    //test sortPlayer
    @Test
    public void testSortPlayerInOrder () {
        testLB.addPlayers(p3);
        testLB.addPlayers(p2);
        testLB.addPlayers(p1);
        testLB.sortPlayers();

        assertEquals("cc", testLB.getIthPlayer(0).getName());
        assertEquals("bb", testLB.getIthPlayer(1).getName());
        assertEquals("aa", testLB.getIthPlayer(2).getName());
    }

    @Test
    public void testSortPlayerInReverseOrder () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        testLB.sortPlayers();

        assertEquals("cc", testLB.getIthPlayer(0).getName());
        assertEquals("bb", testLB.getIthPlayer(1).getName());
        assertEquals("aa", testLB.getIthPlayer(2).getName());
    }

    @Test
    public void testSortPlayerNotInOrder () {
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        testLB.addPlayers(p1);
        testLB.sortPlayers();

        assertEquals("cc", testLB.getIthPlayer(0).getName());
        assertEquals("bb", testLB.getIthPlayer(1).getName());
        assertEquals("aa", testLB.getIthPlayer(2).getName());
    }

    @Test
    public void testGetName () {
        Leaderboard testLB2 = new Leaderboard("test2");

        assertEquals("test2", testLB2.getName());
    }

    @Test
    public void testGetScore () {
        testLB.addPlayers(p1);

        assertEquals(1, testLB.getIthPlayer(0).getScore());
    }

    @Test
    public void testGetPlayers () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);

        ArrayList<Player> expected = new ArrayList<Player>();
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);

        assertEquals(expected, testLB.getPlayers());
    }

    @Test
    public void testEmptyLeaderboard () {
        assertTrue(testLB.isEmpty());
    }

    @Test
    public void testNonEmptyLeaderboard () {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);

        assertFalse(testLB.isEmpty());
    }

    @Test public void testToJson() {
        JSONObject jObject = testLB.toJson();
        assertEquals("Game Leaderboard", jObject.get("name"));
        assertEquals(0, jObject.getJSONArray("Leaderboard").length());
    }

    @Test public void testToJsonOnePlayer() {
        testLB.addPlayers(p1);
        JSONObject jObject = testLB.toJson();
        assertEquals("Game Leaderboard", jObject.get("name"));
        assertEquals(1, jObject.getJSONArray("Leaderboard").length());
        assertEquals("aa", jObject.getJSONArray("Leaderboard").getJSONObject(0)
                .getString("name"));
        assertEquals(1, jObject.getJSONArray("Leaderboard").getJSONObject(0)
                .getInt("score"));
    }

    @Test public void testPlayersToJson() {
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);
        JSONArray jList = testLB.playersToJson();
        assertEquals(3, jList.length());
        assertEquals("aa", jList.getJSONObject(0).get("name"));
        assertEquals("bb", jList.getJSONObject(1).get("name"));
        assertEquals("cc", jList.getJSONObject(2).get("name"));
    }
}
