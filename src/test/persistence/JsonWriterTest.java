package persistence;

import model.Leaderboard;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
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
}
