package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSquareTest {
    private int xpos;
    private int ypos;
    private PlayerSquare psTest5;
    private PlayerSquare psTest7;


    @BeforeEach
    public void setup() {
        psTest5 = new PlayerSquare(5);
        psTest7 = new PlayerSquare(7);
    }

    @Test
    public void testPlayerSquareConstructor() {
        assertEquals(2, psTest5.getCurrentXPos());
        assertEquals(2, psTest5.getCurrentYPos());
    }

    @Test
    public void testPlayerSquareConstructor2() {
        assertEquals(3, psTest7.getCurrentXPos());
        assertEquals(3, psTest7.getCurrentYPos());
    }

    @Test
    public void testMoveLeft() {
        psTest5.moveLeft();
        assertEquals(1, psTest5.getCurrentXPos());
        assertEquals(2, psTest5.getCurrentYPos());

        psTest5.moveLeft();
        assertEquals(0, psTest5.getCurrentXPos());
        assertEquals(2, psTest5.getCurrentYPos());
    }

    @Test
    public void testMoveRight() {
        psTest5.moveRight();
        assertEquals(3, psTest5.getCurrentXPos());
        assertEquals(2, psTest5.getCurrentYPos());

        psTest5.moveRight();
        assertEquals(4, psTest5.getCurrentXPos());
        assertEquals(2, psTest5.getCurrentYPos());
    }

    @Test
    public void testMoveUp() {
        psTest5.moveUp();
        assertEquals(2, psTest5.getCurrentXPos());
        assertEquals(1, psTest5.getCurrentYPos());

        psTest5.moveUp();
        assertEquals(2, psTest5.getCurrentXPos());
        assertEquals(0, psTest5.getCurrentYPos());
    }

    @Test
    public void testMoveDown() {
        psTest5.moveDown();
        assertEquals(2, psTest5.getCurrentXPos());
        assertEquals(3, psTest5.getCurrentYPos());

        psTest5.moveDown();
        assertEquals(2, psTest5.getCurrentXPos());
        assertEquals(4, psTest5.getCurrentYPos());
    }


}
