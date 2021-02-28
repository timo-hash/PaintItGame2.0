package model;


public class Grid {

    private int gridSize = 5;  // works better if it's an odd number
    private boolean[][] gameScreen;

    // getter
    // EFFECTS: return the width of the square grid
    public int getGridSize() {
        return gameScreen.length;
    }


    // EFFECTS: create a 2D square array of booleans, and set center value to true
    public void makeGrid(int gridSize) {
        gameScreen = new boolean[gridSize][gridSize];

        for (int row = 0; row < gameScreen.length; row++) {
            for (int col = 0; col < gameScreen.length; col++) {
                gameScreen[row][col] = false;
                gameScreen[gridSize / 2][gridSize / 2] = true;
            }
        }

    }

    //EFFECTS: print the grid on console with false represented as o, and true as x
    public void printGrid() {
        for (boolean[] row : gameScreen) {
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

    // EFFECTS: return the number of units on the grid
    public int gridArea() {
        return getGridSize() * getGridSize();
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
}
