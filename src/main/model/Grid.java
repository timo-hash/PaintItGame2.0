package model;

/*
 * Represents the screen in terms of a grid
 */

import gui.exceptions.InvalidSizeException;

import java.util.Random;

public class Grid {

    private int gridSize;  // works better if it's an odd number
    private boolean[][] gameScreen;
    private int difficulty = 1;
    private int randomX;
    private int randomY;


    public Grid(int size) {
        try {
            setGridSize(size);
        } catch (InvalidSizeException e) {
            e.printStackTrace();
            System.err.println("Invalid size. Set to default size of 5");
        }
        makeGrid();
        fillRandomSquare();
    }

    // getter
    // EFFECTS: return the field gridSize
    public int getGridSize() {
        return gridSize;
    }

    // setter
    // MODIFIES: this
    // EFFECTS: if given dimension is < 3, throw InvalidSizeException and set grid size to 5,
    //          else set to specified grid dimension
    public void setGridSize(int gridSize) throws InvalidSizeException {
        if (gridSize < 3) {
            this.gridSize = 5;
            throw new InvalidSizeException();
        } else {
            this.gridSize = gridSize;
        }
    }



    // EFFECTS: create a 2D square array of booleans, and set center value to true
    public void makeGrid() {
        gameScreen = new boolean[gridSize][gridSize];

        for (int row = 0; row < gameScreen.length; row++) {
            for (int col = 0; col < gameScreen.length; col++) {
                gameScreen[row][col] = false;
                gameScreen[gridSize / 2][gridSize / 2] = true;
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: generate a random x and y coordinate on the grid
    public void generateRandomCoordinate() {
        Random rand = new Random();
        int upperbound = getGridSize();
        randomX = rand.nextInt(upperbound);
        randomY = rand.nextInt(upperbound);
    }

    // MODIFIES: this
    // EFFECTS: given a random x and y coordinate, change the grid unit to false. If the grid unit is already false,
     //         generate another coordinate
    public void fillRandomSquare() {
        int numOfRandomSquares = (gridArea() * difficulty) / 20;
        int i = 0;
        while (i < numOfRandomSquares) {
            generateRandomCoordinate();
            if (gridUnitValue(randomX, randomY) == false) {
                fillSquare(randomX, randomY);
                i++;
            }
        }
    }

    // EFFECTS: return the number of units on the grid
    public int getNumberOfTrue() {
        int numberOfTrues = 0;
        for (int row = 0; row < gameScreen.length; row++) {
            for (int col = 0; col < gameScreen.length; col++) {
                if (gameScreen[row][col] == true) {
                    numberOfTrues++;
                }
            }
        }
        return numberOfTrues;
    }

    // EFFECTS: return the number of units on the grid
    public int gridArea() {
        return gridSize * gridSize;
    }

    // EFFECTS: return the boolean value of a specified unit on the grid
    public boolean gridUnitValue(int x, int y) {
        return gameScreen[y][x];
    }

    //MODIFIES: this
    //EFFECTS: set a particular element of the array to true
    public void fillSquare(int x, int y) {
        gameScreen[y][x] = true;
    }


    public boolean[][] getGameScreen() {
        return gameScreen;
    }


}
