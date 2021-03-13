package model;

/*
 * Represents the square the player controls
 */

public class PlayerSquare {

    private int xpos;
    private int ypos;


    public PlayerSquare(int gridSize) {
        xpos = gridSize / 2;
        ypos = xpos;
    }

    //getters
    public int getCurrentXPos() {

        return xpos;
    }
    
    public int getCurrentYPos() {

        return ypos;
    }


    //MODIFIES: this
    //EFFECTS: move player square 1 unit to the left
    public void moveLeft() {
        xpos = xpos - 1;
    }

    //MODIFIES: this
    //EFFECTS: move player square 1 unit to the right
    public void moveRight() {
        xpos = xpos + 1;
    }

    //MODIFIES: this
    //EFFECTS: move player square 1 unit to the up
    public void moveUp() {
        ypos = ypos - 1;
    }

    //MODIFIES: this
    //EFFECTS: move player square 1 unit to the down
    public void moveDown() {
        ypos = ypos + 1;
    }



}
