package model;

import gui.exceptions.InvalidSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
//
//    public Grid testGrid3;
//    public Grid testGrid5;
//    public Grid testSetGrid;
//
//    @BeforeEach
//    public void setup () {
//        testSetGrid = new Grid();
//        testGrid3 = new Grid();
//        try {
//            testGrid3.setGridSize(3);
//        } catch (InvalidSizeException e) {
//            System.err.println("Invalid size. Set to default size of 5");
//        }
//
//        testGrid5 = new Grid();
//        try {
//            testGrid5.setGridSize(5);
//        } catch (InvalidSizeException e) {
//            System.err.println("Invalid size. Set to default size of 5");
//        }
//
//        testGrid3.makeGrid();
//        testGrid5.makeGrid();
//    }
//
//    //test makeGrid
//    @Test
//    public void testMakeGrid5() {
//        assertFalse(testGrid5.gridUnitValue(0,0));
//        assertFalse(testGrid5.gridUnitValue(0,1));
//        assertFalse(testGrid5.gridUnitValue(0,2));
//        assertFalse(testGrid5.gridUnitValue(0,3));
//        assertFalse(testGrid5.gridUnitValue(0,4));
//
//        assertFalse(testGrid5.gridUnitValue(1,0));
//        assertFalse(testGrid5.gridUnitValue(1,1));
//        assertFalse(testGrid5.gridUnitValue(1,2));
//        assertFalse(testGrid5.gridUnitValue(1,3));
//        assertFalse(testGrid5.gridUnitValue(1,4));
//
//        assertFalse(testGrid5.gridUnitValue(2,0));
//        assertFalse(testGrid5.gridUnitValue(2,1));
//        assertTrue(testGrid5.gridUnitValue(2,2));
//        assertFalse(testGrid5.gridUnitValue(2,3));
//        assertFalse(testGrid5.gridUnitValue(2,4));
//
//        assertFalse(testGrid5.gridUnitValue(3,0));
//        assertFalse(testGrid5.gridUnitValue(3,1));
//        assertFalse(testGrid5.gridUnitValue(3,2));
//        assertFalse(testGrid5.gridUnitValue(3,3));
//        assertFalse(testGrid5.gridUnitValue(3,4));
//
//        assertFalse(testGrid5.gridUnitValue(4,0));
//        assertFalse(testGrid5.gridUnitValue(4,1));
//        assertFalse(testGrid5.gridUnitValue(4,2));
//        assertFalse(testGrid5.gridUnitValue(4,3));
//        assertFalse(testGrid5.gridUnitValue(4,4));
//    }
//
//
//    //test gridUnitValue
//    @Test
//    public void testGridUnitValue() {
//        assertFalse(testGrid3.gridUnitValue(0,0));
//        assertFalse(testGrid3.gridUnitValue(0,1));
//        assertFalse(testGrid3.gridUnitValue(0,2));
//        assertFalse(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertFalse(testGrid3.gridUnitValue(1,2));
//        assertFalse(testGrid3.gridUnitValue(2,0));
//        assertFalse(testGrid3.gridUnitValue(2,1));
//        assertFalse(testGrid3.gridUnitValue(2,2));
//    }
//
//
//
//
//    // Test fillSquare
//    @Test
//    public void testFillSquare1() {
//        testGrid3.fillSquare(0,0);
//        assertTrue(testGrid3.gridUnitValue(0,0));
//        assertFalse(testGrid3.gridUnitValue(0,1));
//        assertFalse(testGrid3.gridUnitValue(0,2));
//        assertFalse(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertFalse(testGrid3.gridUnitValue(1,2));
//        assertFalse(testGrid3.gridUnitValue(2,0));
//        assertFalse(testGrid3.gridUnitValue(2,1));
//        assertFalse(testGrid3.gridUnitValue(2,2));
//    }
//
//    @Test
//    public void testFillSquare2() {
//        testGrid3.fillSquare(0,0);
//        testGrid3.fillSquare(1,2);
//        assertTrue(testGrid3.gridUnitValue(0,0));
//        assertFalse(testGrid3.gridUnitValue(0,1));
//        assertFalse(testGrid3.gridUnitValue(0,2));
//        assertFalse(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertTrue(testGrid3.gridUnitValue(1,2));
//        assertFalse(testGrid3.gridUnitValue(2,0));
//        assertFalse(testGrid3.gridUnitValue(2,1));
//        assertFalse(testGrid3.gridUnitValue(2,2));
//    }
//
//    @Test
//    public void testFillSquare3() {
//        testGrid3.fillSquare(0,0);
//        testGrid3.fillSquare(1,1);
//        testGrid3.fillSquare(2,0);
//        assertTrue(testGrid3.gridUnitValue(0,0));
//        assertFalse(testGrid3.gridUnitValue(0,1));
//        assertFalse(testGrid3.gridUnitValue(0,2));
//        assertFalse(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertFalse(testGrid3.gridUnitValue(1,2));
//        assertTrue(testGrid3.gridUnitValue(2,0));
//        assertFalse(testGrid3.gridUnitValue(2,1));
//        assertFalse(testGrid3.gridUnitValue(2,2));
//    }
//
//    @Test
//    public void testGetGridSize3() {
//        assertEquals(3, testGrid3.getGridSize());
//    }
//
//    @Test
//    public void testGetGridSize5() {
//        assertEquals(5, testGrid5.getGridSize());
//    }
//
//
//    @Test
//    public void testGridArea3() {
//        assertEquals(9, testGrid3.gridArea());
//    }
//
//    @Test
//    public void testGridArea5() {
//        assertEquals(25, testGrid5.gridArea());
//    }
//
//    @Test
//    public void testGetGridScreenChangingValuesOfGrid() {
//        testGrid3.getGameScreen();
//        testGrid3.makeGrid();
//
//        assertFalse(testGrid3.gridUnitValue(0,0));
//        assertFalse(testGrid3.gridUnitValue(0,1));
//        assertFalse(testGrid3.gridUnitValue(0,2));
//        assertFalse(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertFalse(testGrid3.gridUnitValue(1,2));
//        assertFalse(testGrid3.gridUnitValue(2,0));
//        assertFalse(testGrid3.gridUnitValue(2,1));
//        assertFalse(testGrid3.gridUnitValue(2,2));
//
//        for (int row = 0; row < testGrid3.getGridSize(); row++) {
//            for (int col = 0; col < testGrid3.getGridSize(); col++) {
//                testGrid3.getGameScreen()[row][col] = true;
//            }
//        }
//
//        assertTrue(testGrid3.gridUnitValue(0,0));
//        assertTrue(testGrid3.gridUnitValue(0,1));
//        assertTrue(testGrid3.gridUnitValue(0,2));
//        assertTrue(testGrid3.gridUnitValue(1,0));
//        assertTrue(testGrid3.gridUnitValue(1,1));
//        assertTrue(testGrid3.gridUnitValue(1,2));
//        assertTrue(testGrid3.gridUnitValue(2,0));
//        assertTrue(testGrid3.gridUnitValue(2,1));
//        assertTrue(testGrid3.gridUnitValue(2,2));
//
//
//    }
//
//    @Test
//    public void testGetGridScreenLength() {
//        assertEquals(3, testGrid3.getGameScreen().length);
//        assertEquals(5, testGrid5.getGameScreen().length);
//    }
//
//    @Test void testSetGridSizeTooSmall() {
//        try {
//            testSetGrid.setGridSize(2);
//            fail("Should not be here");
//        } catch (InvalidSizeException e) {
//            // good catch
//        }
//
//        assertEquals(5, testSetGrid.getGridSize());
//    }
//
//    @Test void testSetGridSizeNegativeDimension() {
//        try {
//            testSetGrid.setGridSize(-5);
//            fail("Should not be here");
//        } catch (InvalidSizeException e) {
//            // good catch
//        }
//
//        assertEquals(5, testSetGrid.getGridSize());
//    }
//
//    @Test void testSetGridSizeValid3() {
//        try {
//            testSetGrid.setGridSize(3);
//            assertEquals(3, testSetGrid.getGridSize());
//        } catch (InvalidSizeException e) {
//            fail("Should not throw InvalidSizeException");
//        }
//    }
//
//    @Test void testSetGridSizeValid7() {
//        try {
//            testSetGrid.setGridSize(7);
//            assertEquals(7, testSetGrid.getGridSize());
//        } catch (InvalidSizeException e) {
//            fail("Should not throw InvalidSizeException");
//        }
//    }



}