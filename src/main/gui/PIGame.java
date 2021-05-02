package gui;

import gui.exceptions.InvalidSizeException;
import model.Grid;
import model.Leaderboard;
import model.Player;
import model.PlayerSquare;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/*
 * Represents a paint it game.
 */
public class PIGame {
    private PlayerSquare ps;
    private Player player;
    private Grid game;
    private Leaderboard leaderboard;

    private static final String JSON_STORE = "./data/GUILeaderboard.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    boolean gameStillGoing = true;
    boolean continuePlaying = true;
    private ArrayList<String> leaderboardList;
    private int dimension = 7;


    // EFFECTS: runs the game application
    public PIGame() {
        leaderboard = new Leaderboard("Paint It Leaderboard");
        game = new Grid(this.dimension);
        ps = new PlayerSquare(game.getGridSize());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadLeaderboard();
    }

    //getter
    public boolean getGameStillGoing() {
        return gameStillGoing;
    }

    public PlayerSquare getPlayerSquare() {
        return ps;
    }

    public Grid getGrid() {
        return game;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public ArrayList<String> getLeaderboardList() {
        return this.leaderboardList;
    }

    public boolean getContinuePlaying() {
        return this.continuePlaying;
    }

    public void setContinuePlaying(boolean value) {
        this.continuePlaying = value;
    }



    // MODIFIES: this
    // EFFECTS: check if the grid is filled up and no more moves are possible
    public boolean checkNoMoreMoves() {
        if ((game.gridArea() - game.getNumberOfTrue()) == 0) {
            gameStillGoing = false;
            return false;
        }
        return true;
    }



    // MODIFIES: this
    // EFFECTS: moves the x to the left by 1 unit
    public void moveLeft() {
        if (ps.getCurrentXPos() - 1 < 0) {
            gameStillGoing = false;
        } else if (game.gridUnitValue((ps.getCurrentXPos() - 1), ps.getCurrentYPos())) {
            gameStillGoing = false;
        } else {
            ps.moveLeft();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
        }

    }

    // MODIFIES: this
    // EFFECTS: moves the x to the right by 1 unit
    public void moveRight() {
        if (ps.getCurrentXPos() + 1 > (game.getGridSize() - 1)) {
            gameStillGoing = false;
        } else if (game.gridUnitValue((ps.getCurrentXPos() + 1), ps.getCurrentYPos())) {
            gameStillGoing = false;
        } else {
            ps.moveRight();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the x up by 1 unit
    public void moveUp() {
        if (ps.getCurrentYPos() - 1 < 0) {
            gameStillGoing = false;
        } else if (game.gridUnitValue(ps.getCurrentXPos(), (ps.getCurrentYPos() - 1))) {
            gameStillGoing = false;
        } else {
            ps.moveUp();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the x down by 1 unit
    public void moveDown() {
        if (ps.getCurrentYPos() + 1 > (game.getGridSize() - 1)) {
            gameStillGoing = false;
        } else if (game.gridUnitValue(ps.getCurrentXPos(), (ps.getCurrentYPos() + 1))) {
            gameStillGoing = false;
        } else {
            ps.moveDown();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
        }
    }


    // EFFECTS: print game over and player score on console
    public String gameOver() {
        return "You scored " + game.getNumberOfTrue() + " out of " + game.gridArea() + "!!";
    }

    // MODIFIES: Leaderboard
    // EFFECTS: record name and score of player to Leaderboard; sort scores in descending order
    public void recordNameAndScore(String playerName) {
        player = new Player(playerName, game.getNumberOfTrue());

        leaderboard.addPlayers(player);
        leaderboard.sortPlayers();
    }



    // EFFECTS: convert elements in the leaderboard array to String
    public void saveLeaderboardToStringArray() {
        leaderboardList = new ArrayList<>();
        for (int i = 0; i < leaderboard.numOfPlayers(); i++) {
            String leaderboardScoreMessage = leaderboard.getIthPlayer(i).getName() + " scored "
                    + leaderboard.getIthPlayer(i).getScore()
                    + " points out of " + game.gridArea();

            leaderboardList.add(leaderboardScoreMessage);

        }
    }


    // MODIFIES: this
    // EFFECTS: re-initiate the game and reset local variables
    public void restart() {
        gameStillGoing = true;
        game = new Grid(this.dimension);
        ps = new PlayerSquare(game.getGridSize());
    }



    // EFFECTS: saves the leaderboard to file
    public void saveLeaderboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(leaderboard);
            jsonWriter.close();
            System.out.println("Saved " + leaderboard.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads leaderboard from file
    private void loadLeaderboard() {
        try {
            leaderboard = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
