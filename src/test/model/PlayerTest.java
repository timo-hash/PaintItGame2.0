package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player testP1;
    private String name;
    private int score;

    @BeforeEach
    public void setup () {
        testP1 = new Player("Jim", 4);
    }


    @Test
    public void testGetName(){
        assertEquals("Jim", testP1.getName());
    }

    @Test
    public void testGetScore(){
        assertEquals(4, testP1.getScore());
    }

    @Test
    public void testToJson(){
        testP1.toJson();
        assertEquals("Jim",testP1.toJson().get("name"));
        assertEquals(4,testP1.toJson().get("score"));

    }
}
