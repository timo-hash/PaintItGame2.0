package persistence;

import model.Leaderboard;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    private String testSource;

    private Leaderboard testLB;
    private Leaderboard testLBEmpty;
    private Player p1;
    private Player p2;
    private Player p3;

    @BeforeEach
    public void setup() {

        testLB = new Leaderboard("Game Leaderboard");
        testLBEmpty = new Leaderboard("Empty Leaderboard");
        p1 = new Player("aa", 1);
        p2 = new Player("bb", 2);
        p3 = new Player("cc", 3);


    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("noSuchFile");
        try {
            Leaderboard lb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLeaderboard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeaderboard.json");
        try {
            Leaderboard lb = reader.read();
            assertEquals("My Leaderboard", lb.getName());
            assertEquals(0, lb.numOfPlayers());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLeaderboard() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLeaderboard.json");
        try {
            Leaderboard lb = reader.read();
            assertEquals("My Leaderboard", lb.getName());
            List<Player> players = lb.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Amy", 5, players.get(0));
            checkPlayer("Jim", 3, players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




    @Test
    public void testAddPlayer() {
        JsonReader reader = new JsonReader("test read add player");
        testLB.addPlayers(p1);

        JSONArray testArray = new JSONArray();
        testArray = testLB.playersToJson();

        JSONObject testJObject = testLB.toJson();
        reader.addPlayer(testLBEmpty, (JSONObject) testArray.get(0));

        assertEquals(1, testLBEmpty.numOfPlayers());
        assertEquals("aa", testLBEmpty.getIthPlayer(0).getName());
        assertEquals(1, testLBEmpty.getIthPlayer(0).getScore());
    }

    @Test
    public void testAddLeaderboards() {
        JsonReader reader = new JsonReader("test read add leaderboard");
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);

        JSONObject testJObjectLeaderboard = testLB.toJson();

        reader.addLeaderboards(testLBEmpty, testJObjectLeaderboard);

        assertEquals(3, testLBEmpty.numOfPlayers());
        assertEquals("aa", testLBEmpty.getIthPlayer(0).getName());
        assertEquals("bb", testLBEmpty.getIthPlayer(1).getName());
        assertEquals("cc", testLBEmpty.getIthPlayer(2).getName());
        assertEquals(1, testLBEmpty.getIthPlayer(0).getScore());
        assertEquals("Empty Leaderboard", testLBEmpty.getName());
    }

    @Test
    public void testParseLeaderBoard() {
        JsonReader reader = new JsonReader("test read parse leaderboard");
        testLB.addPlayers(p1);
        testLB.addPlayers(p2);
        testLB.addPlayers(p3);

        JSONObject testJObjectLeaderboard = testLB.toJson();
        Leaderboard newLB = reader.parseLeaderBoard(testJObjectLeaderboard);

        assertEquals(3, newLB.numOfPlayers());
        assertEquals("aa", newLB.getIthPlayer(0).getName());
        assertEquals("bb", newLB.getIthPlayer(1).getName());
        assertEquals("cc", newLB.getIthPlayer(2).getName());
        assertEquals(1, newLB.getIthPlayer(0).getScore());
        assertEquals("Game Leaderboard", newLB.getName());
    }

    @Test
    public void testReadFileFailed() {
        JsonReader reader = new JsonReader("test read parse leaderboard");
        try {
            reader.readFile("no such file");
            fail("Couldn't read from file");
        } catch (IOException e) {
            // good
        }

    }

    @Test
    public void testReadFileSuccess() {
        JsonReader reader = new JsonReader("test read parse leaderboard");
        try {
            reader.readFile("./data/testRead3players.txt");
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testReadFileWith3Players() {
        JsonReader reader = new JsonReader("./data/testRead3players.txt");
        try {
            Leaderboard testLB = reader.read();
            assertEquals("Test LB", testLB.getName());
            List<Player> players = testLB.getPlayers();
            assertEquals(3, players.size());
            checkPlayer("a", 1, players.get(0));
            checkPlayer("b", 2, players.get(1));
            checkPlayer("c", 3, players.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReadObjects() {
        JsonReader reader = new JsonReader("./data/testRead3players.txt");
        try {
            reader.read();

            String jsonData = reader.readFile("./data/testRead3players.txt");
            JSONObject testJObject = new JSONObject(jsonData);

            assertEquals("Test LB", testJObject.getString("name"));
            assertEquals(2, testJObject.length());
            assertEquals(1, testJObject.getJSONArray("Leaderboard").getJSONObject(0).get("score"));
            assertEquals(2, testJObject.getJSONArray("Leaderboard").getJSONObject(1).get("score"));
            assertEquals(3, testJObject.getJSONArray("Leaderboard").getJSONObject(2).get("score"));
            assertEquals("a", testJObject.getJSONArray("Leaderboard").getJSONObject(0).get("name"));
            assertEquals("b", testJObject.getJSONArray("Leaderboard").getJSONObject(1).get("name"));
            assertEquals("c", testJObject.getJSONArray("Leaderboard").getJSONObject(2).get("name"));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    public void testReadMethod() {
        JsonReader reader = new JsonReader("./data/testRead3players.txt");
        try {
            assertEquals("Test LB", reader.read().getName());
            assertEquals("a", reader.read().getIthPlayer(0).getName());
            assertEquals("b", reader.read().getIthPlayer(1).getName());
            assertEquals("c", reader.read().getIthPlayer(2).getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

//
//    // EFFECTS: reads LeaderBoard from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public Leaderboard read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseLeaderBoard(jsonObject);
//    }
}
