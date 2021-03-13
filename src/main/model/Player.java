package model;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a information of the player
 */

public class Player implements Writable {
    private String name;
    private int score;

    // EFFECTS: constructs a Player with a name and score
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", score);
        return json;
    }
}
