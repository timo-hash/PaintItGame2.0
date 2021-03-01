package model;


public class Grid {

    private int gridSize;  // works better if it's an odd number
    private boolean[][] gameScreen;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
    }

    // getter
    // EFFECTS: return the field gridSize
    public int getGridSize() {
        return gridSize;
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

//    // MODIFIES: this
//    // EFFECTS: set size of grid
//    public void setGridSize(int dimension) {
//        this.gridSize = dimension;
//    }

}
