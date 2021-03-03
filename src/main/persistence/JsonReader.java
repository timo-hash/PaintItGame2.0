package persistence;

import model.Leaderboard;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads LeaderBoard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses LeaderBoard from JSON object and returns it
    public Leaderboard parseLeaderBoard(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Leaderboard lb = new Leaderboard(name);
        addLeaderboards(lb, jsonObject);
        return lb;
    }

    // MODIFIES: lb
    // EFFECTS: parses players from JSON object and adds them to LeaderBoard
    public void addLeaderboards(Leaderboard lb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Leaderboard");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(lb, nextPlayer);
        }
    }

    // MODIFIES: lb
    // EFFECTS: parses player from JSON object and adds it to LeaderBoard
    private void addPlayer(Leaderboard lb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = jsonObject.getInt("score");
        Player player = new Player(name, score);
        lb.addPlayers(player);
    }
}
