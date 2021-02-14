package ui;

import model.Grid;
import model.Leaderboard;
import model.Player;
import model.PlayerSquare;

import java.util.Scanner;

public class GameApp {

    private Scanner input;
    private PlayerSquare ps;
    private Player player;
    private Grid game;
    private Leaderboard leaderboard;

    boolean gameStillGoing = true;
    int trueCounter = 1;


    // EFFECTS: runs the game application
    public GameApp() {
        leaderboard = new Leaderboard();
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

            if (trueCounter == game.gridArea()) {
                gameStillGoing = false;
            }
        }

        gameOver();
        recordNameAndScore();
        leaderboard.sortPlayers();
        printLeaderboard();

        while (!(command.equals("q") || command.equals("r"))) {
            displayEndGameControl();
            command = input.next();
            command = command.toLowerCase();
        }
        processEndGameOptions(command);
    }

    // MODIFIES: this
    // EFFECTS: initializes grid, playerSquare and input
    private void init() {
        game = new Grid();
        ps = new PlayerSquare();
        input = new Scanner(System.in);

        displayControl();
        System.out.println();
        game.makeGrid(game.getGridSize());
        game.printGrid();
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
            game.printGrid();
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
            game.printGrid();
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
            game.printGrid();
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
            game.printGrid();
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


    // MODIFIES: this
    // EFFECTS: processes end game user input
    public void processEndGameOptions(String command) {
        if (command.equals("q")) {
            System.out.println("\nPlay again next time!");
        } else {
            restart();
        }
    }

    // EFFECTS: displays endgame options for the player to use
    public void displayEndGameControl() {
        System.out.println("\nTo quit, press q");
        System.out.println("To try again, press r");
    }
}
