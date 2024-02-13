/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.shapes.*;
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
public class AddShapeCommandTest {
    static Pane paneStub;
    static AdapterShape shapeStub, shapeStub2;
    static AddShapeCommand instance;
    static AddShapeCommand instance2;
    
    public AddShapeCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        paneStub = new Pane();
        shapeStub = new AdapterRectangle();
        shapeStub2 = new AdapterRectangle(new Rectangle(400, 220));
        instance = new AddShapeCommand(paneStub, shapeStub);
        instance2 = new AddShapeCommand(paneStub, shapeStub2);
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
     * Test of execute method, of class AddShapeCommand.
     */
    @Test
    public void testExecute() {
        instance.execute();
        instance2.execute();
        assertTrue(paneStub.getChildren().contains(shapeStub.getAdaptee()));
        assertTrue(paneStub.getChildren().contains(shapeStub2.getAdaptee()));
    }

    /**
     * Test of undo method, of class AddShapeCommand.
     */
    @Test
    public void testUndo() {
        instance.undo();
        instance2.undo();
        assertTrue(!paneStub.getChildren().contains(shapeStub.getAdaptee()));
        assertTrue(!paneStub.getChildren().contains(shapeStub2.getAdaptee()));
    }
    
}
