/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.shape.Rectangle;
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
 * @author Marco
 */
public class MoveShapeCommandTest {
    static AdapterShape shapeStub, shapeStub2;
    static MoveShapeCommand commandInstance, commandInstance2;
    
    public MoveShapeCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        shapeStub = new AdapterRectangle(new Rectangle(0,0,400, 200));
        shapeStub2 = new AdapterRectangle(new Rectangle(0,0,400, 200));
        commandInstance = new MoveShapeCommand(shapeStub,200,200);
        commandInstance2 = new MoveShapeCommand(shapeStub2,-200,-200);
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
     * Test of execute method, of class MoveShapeCommand.
     */
    @Test
    public void testExecute() {
        commandInstance.execute();
        commandInstance2.execute();
        assertEquals(0,((Rectangle)shapeStub.getAdaptee()).getX(),0.01);
        assertEquals(100,((Rectangle)shapeStub.getAdaptee()).getY(),0.01);
        assertEquals(-400,((Rectangle)shapeStub2.getAdaptee()).getX(),0.01);
        assertEquals(-300,((Rectangle)shapeStub2.getAdaptee()).getY(),0.01);
        
    }

    /**
     * Test of undo method, of class MoveShapeCommand.
     */
    @Test
    public void testUndo() {
        commandInstance.undo();
        commandInstance2.undo();
        assertEquals(0,((Rectangle)shapeStub.getAdaptee()).getX(),0.01);
        assertEquals(0,((Rectangle)shapeStub.getAdaptee()).getY(),0.01);
        assertEquals(0,((Rectangle)shapeStub2.getAdaptee()).getX(),0.01);
        assertEquals(0,((Rectangle)shapeStub2.getAdaptee()).getY(),0.01);
    }
    
}
