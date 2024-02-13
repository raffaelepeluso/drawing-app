/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this tempRectanglelate
 */
package model.commands;

import static java.lang.Math.abs;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.shapes.AdapterLineSegment;
import model.shapes.AdapterRectangle;
import model.shapes.AdapterShape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Calabrese
 */
public class ResizeShapeCommandTest {

    static ResizeShapeCommand instanceRectangle, instanceLine;
    static AdapterShape shapeStubRectangle, shapeStubLine;
    static double height, width, newWidth, newHeight;
    static double startX, startY, endX, endY;
    static boolean newHeightOrNot = true;

    public ResizeShapeCommandTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        startX = 500;
        startY = 500;
        endX = 900;
        endY = 900;
        shapeStubLine = new AdapterLineSegment(new Line(startX, startY, endX, endY));
        width = abs(startX - endX);
        height = abs(startY - endY);
        if (newHeightOrNot){//new height
            newWidth = width;
            newHeight = 250.0;
        }else{//new width
            newWidth = 250.0;
            newHeight = height;
        }
        shapeStubRectangle = new AdapterRectangle(new Rectangle(width, height));
        instanceRectangle = new ResizeShapeCommand(shapeStubRectangle, newWidth, newHeight);
        instanceLine = new ResizeShapeCommand(shapeStubLine, newWidth, newHeight);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class ResizeShapeCommand.
     */
    @Test
    public void testExecute() {
        instanceRectangle.execute();
        instanceLine.execute();
        Rectangle tempRectangle = (Rectangle) shapeStubRectangle.getAdaptee();
        Line tempLine = (Line) shapeStubLine.getAdaptee();
        double expectDim;
        
        if (newHeightOrNot){//new height
            expectDim = width * newHeight / height;
            assertEquals("Rectangle Expected Height",newHeight, tempRectangle.getHeight(), 0.01);
            assertEquals("Rectangle Expected Width",expectDim, tempRectangle.getWidth(), 0.01);

            assertEquals("Line Expected Height",newHeight, shapeStubLine.getHeight(), 0.01);
            assertEquals("Line Expected Width",expectDim, shapeStubLine.getWidth(), 0.01);
        }else{//new width
            expectDim = height * newWidth / width;
            assertEquals("Rectangle Expected Height",expectDim, tempRectangle.getHeight(), 0.01);
            assertEquals("Rectangle Expected Width",newWidth, tempRectangle.getWidth(), 0.01);

            assertEquals("Line Expected Height",expectDim, shapeStubLine.getHeight(), 0.01);
            assertEquals("Line Expected Width",newWidth, shapeStubLine.getWidth(), 0.01);
        }
        
    }

    /**
     * Test of undo method, of class ResizeShapeCommand.
     */
    @Test
    public void testUndo() {
        instanceRectangle.undo();
        Rectangle tempRectangle = (Rectangle) shapeStubRectangle.getAdaptee();
        assertEquals(height, tempRectangle.getHeight(), 0.01);
        assertEquals(width, tempRectangle.getWidth(), 0.01);
    }

}
