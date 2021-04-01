package ui;

import gui.exceptions.InvalidSizeException;
import model.Grid;
import model.Leaderboard;
import model.Player;
import model.PlayerSquare;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * Represents the game application
 */

public class GameApp {

    private Scanner input;
    private PlayerSquare ps;
    private Player player;
    private Grid game;
    private Leaderboard leaderboard;

    private static final String JSON_STORE = "./data/leaderboard.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    boolean gameStillGoing = true;
    int trueCounter = 1;


    // EFFECTS: runs the game application
    public GameApp() {
        leaderboard = new Leaderboard("Game Leaderboard");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadLeaderboard();
        startGame();
    }



    // format is similar to TellerApp
    public void startGame() {
        String command = null;

        init();
        while (gameStillGoing) {
            command = input.next();
            command = command.toLowerCase();
            processControl(command);

            checkNoMoreMoves();
        }

        gameOver();
        recordNameAndScore();

        while (!(command.equals("q") || command.equals("r"))) {
            displayEndGameControl();
            command = input.next();
            command = command.toLowerCase();

            saveLoadPrintOptions(command);
        }
        processEndGameOptions(command);
    }


    // MODIFIES: this
    // EFFECTS: check if the grid is filled up and no more moves are possible
    private void checkNoMoreMoves() {
        if (trueCounter == game.gridArea()) {
            gameStillGoing = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes grid, playerSquare and input
    private void init() {
        game = new Grid();
        try {
            game.setGridSize(5);
        } catch (InvalidSizeException e) {
            System.err.println("Invalid size. Set to default size of 5");
        }
        ps = new PlayerSquare(game.getGridSize());
        input = new Scanner(System.in);

        displayControl();
        System.out.println();
        game.makeGrid();
        printGrid();
    }


    // MODIFIES: this
    // EFFECTS: processes user input control
    private void processControl(String command) {
        if (command.equals("a")) {
            moveLeft();
        } else if (command.equals("d")) {
            moveRight();
        } else if (command.equals("w")) {
            moveUp();
        } else if (command.equals("s")) {
            moveDown();
        } else {
            System.out.println("Selection not valid. Please select again.");
        }
    }



    // MODIFIES: this
    // EFFECTS: moves the x to the left by 1 unit
    private void moveLeft() {
        if (ps.getCurrentXPos() - 1 < 0) {
            gameStillGoing = false;
        } else if (game.gridUnitValue((ps.getCurrentXPos() - 1), ps.getCurrentYPos())) {
            gameStillGoing = false;
        } else {
            ps.moveLeft();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
            printGrid();
            trueCounter = trueCounter + 1;
        }

    }

    // MODIFIES: this
    // EFFECTS: moves the x to the right by 1 unit
    private void moveRight() {
        if (ps.getCurrentXPos() + 1 > (game.getGridSize() - 1)) {
            gameStillGoing = false;
        } else if (game.gridUnitValue((ps.getCurrentXPos() + 1), ps.getCurrentYPos())) {
            gameStillGoing = false;
        } else {
            ps.moveRight();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
            printGrid();
            trueCounter = trueCounter + 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the x up by 1 unit
    private void moveUp() {
        if (ps.getCurrentYPos() - 1 < 0) {
            gameStillGoing = false;
        } else if (game.gridUnitValue(ps.getCurrentXPos(), (ps.getCurrentYPos() - 1))) {
            gameStillGoing = false;
        } else {
            ps.moveUp();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
            printGrid();
            trueCounter = trueCounter + 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the x down by 1 unit
    private void moveDown() {
        if (ps.getCurrentYPos() + 1 > (game.getGridSize() - 1)) {
            gameStillGoing = false;
        } else if (game.gridUnitValue(ps.getCurrentXPos(), (ps.getCurrentYPos() + 1))) {
            gameStillGoing = false;
        } else {
            ps.moveDown();
            game.fillSquare(ps.getCurrentXPos(), ps.getCurrentYPos());
            printGrid();
            trueCounter = trueCounter + 1;
        }
    }


    // EFFECTS: displays movement options for the player to us
    private void displayControl() {
        System.out.println("\nControls:");
        System.out.println("\tpress a to move left");
        System.out.println("\tpress d to move right");
        System.out.println("\tpress w to move up");
        System.out.println("\tpress s to move down");
    }

    //EFFECTS: print the grid on console with false represented as o, and true as x
    public void printGrid() {
        for (boolean[] row : game.getGameScreen()) {
            for (boolean b: row) {
                if (b) {
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
            }

            System.out.println();
        }
    }

    // EFFECTS: print game over and player score on console
    public void gameOver() {
        System.out.println();
        System.out.println("Game Over");
        System.out.println("You scored " + trueCounter + " out of " + game.gridArea() + "!!");
        System.out.println();

    }

    // MODIFIES: Leaderboard
    // EFFECTS: record name and score of player to Leaderboard
    public void recordNameAndScore() {
        System.out.println("Enter your name below:");
        Scanner name = new Scanner(System.in);
        String playerName = name.next();
        player = new Player(playerName, trueCounter);

        leaderboard.addPlayers(player);
        leaderboard.sortPlayers();
    }


    // EFFECTS: print leaderboard containing name and score on console
    public void printLeaderboard() {
        System.out.println("\nLeaderboard");
        for (int i = 0; i < leaderboard.numOfPlayers(); i++) {
            System.out.println(leaderboard.getIthPlayer(i).getName() + " scored "
                    + leaderboard.getIthPlayer(i).getScore()
                    + " points out of " + game.gridArea());

        }
    }


    // MODIFIES: this
    // EFFECTS: re-initiate the game and reset local variables
    public void restart() {
        gameStillGoing = true;
        trueCounter = 1;
        startGame();
    }

    // MODIFIES: this, jsonWriter, jsonReader
    // EFFECTS: process save, load, and print user inputs
    private void saveLoadPrintOptions(String command) {
        if (command.equals("s")) {
            saveLeaderboard();
        } else if (command.equals("p")) {
            printLeaderboard();
        }
    }


    // MODIFIES: this
    // EFFECTS: processes end game user input
    public void processEndGameOptions(String command) {
        if (command.equals("q")) {
            System.out.println("\nPlay again next time!");
        } else if (command.equals("r")) {
            restart();
        } else {
            System.out.println("Selection not valid. Please select again.");
        }
    }

    // EFFECTS: displays endgame options for the player to use
    public void displayEndGameControl() {
        System.out.println("\nTo quit, press q");
        System.out.println("To try again, press r");
        System.out.println("To save your score to the leaderboard, press s");
        System.out.println("To print out score from leaderboard, press p");
    }



    // EFFECTS: saves the leaderboard to file
    private void saveLeaderboard() {
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
            System.out.println("Loaded " + leaderboard.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public PlayerSquare getPlayerSquare() {
        return ps;
    }


}
