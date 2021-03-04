package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    Player p1;
    Player p2;
    Player p3;

    @BeforeEach
    public void setup() {
        p1 = new Player("a", 1);
        p2 = new Player("b", 2);
        p3 = new Player("c", 3);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            Leaderboard lb = new Leaderboard("My leaderboard");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard("My Leaderboard");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeaderboard.json");
            lb = reader.read();
            assertEquals("My Leaderboard", lb.getName());
            assertEquals(0, lb.numOfPlayers());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeaderboard() {
        try {
            Leaderboard lb = new Leaderboard("My Leaderboard");
            lb.addPlayers(new Player("Amy", 5));
            lb.addPlayers(new Player("Jim", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeaderboard.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeaderboard.json");
            lb = reader.read();
            assertEquals("My Leaderboard", lb.getName());
            List<Player> players = lb.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Amy", 5, players.get(0));
            checkPlayer("Jim", 3, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testOpen() {
        try {
            JsonWriter tJWriter = new JsonWriter("./data/testOpen.json");
            tJWriter.open();
            assertFalse(tJWriter.errorState());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testClose() {
        JsonWriter tJWriter = new JsonWriter("./data/testClose.json");
        try {
            tJWriter.open();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        assertFalse(tJWriter.errorState());
        tJWriter.close();
        assertFalse(tJWriter.errorState());


    }

    @Test
    public void testSaveToFile() {
        Leaderboard lb = new Leaderboard("Test LB");
        lb.addPlayers(p1);
        JsonWriter tJWriter = new JsonWriter("./data/testSaveToFile.json");

        try {
            tJWriter.open();
            assertFalse(tJWriter.errorState());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        JSONObject json = lb.toJson();
        tJWriter.saveToFile(json.toString(3));
        assertEquals("Test LB", json.get("name"));
        assertEquals(1, json.getJSONArray("Leaderboard").length());
        assertEquals("a", json.getJSONArray("Leaderboard").getJSONObject(0)
        .getString("name"));

        assertFalse(tJWriter.errorState());
        tJWriter.close();
    }

    @Test
    public void testWrite1Player() {
        Leaderboard lb = new Leaderboard("Test LB");
        lb.addPlayers(p1);
        JsonWriter tJWriter = new JsonWriter("./data/testWrite1Player.json");
        try {
            tJWriter.open();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }
        tJWriter.write(lb);
        assertFalse(tJWriter.errorState());
        tJWriter.close();

        JSONObject json = lb.toJson();
        tJWriter.saveToFile(json.toString(3));
        assertEquals("Test LB", json.get("name"));
        assertEquals(1, json.getJSONArray("Leaderboard").length());
        assertEquals("a", json.getJSONArray("Leaderboard").getJSONObject(0)
                .getString("name"));

    }


    @Test
    public void testWrite3Player() {
        Leaderboard lb = new Leaderboard("Test LB");
        lb.addPlayers(p1);
        lb.addPlayers(p2);
        lb.addPlayers(p3);
        JsonWriter tJWriter = new JsonWriter("./data/testWrite3Players.json");
        try {
            tJWriter.open();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }
        tJWriter.write(lb);
        assertFalse(tJWriter.errorState());
        tJWriter.close();

        JSONObject json = lb.toJson();
        tJWriter.saveToFile(json.toString(3));
        assertEquals("Test LB", json.get("name"));
        assertEquals(3, json.getJSONArray("Leaderboard").length());
        assertEquals("a", json.getJSONArray("Leaderboard").getJSONObject(0)
                .getString("name"));
        assertEquals("b", json.getJSONArray("Leaderboard").getJSONObject(1)
                .getString("name"));
        assertEquals("c", json.getJSONArray("Leaderboard").getJSONObject(2)
                .getString("name"));

    }


}
