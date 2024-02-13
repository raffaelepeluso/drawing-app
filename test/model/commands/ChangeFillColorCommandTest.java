/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.paint.Color;
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
 * @author pelus
 */
public class ChangeFillColorCommandTest {
    
    static AdapterShape shape;
    static Command command;
    static Color color = Color.BISQUE;
    
    public ChangeFillColorCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        shape = new AdapterRectangle();
        command = new ChangeFillColorCommand(shape, color);
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
     * Test of execute method, of class ChangeFillColorCommand.
     */
    @Test
    public void testExecute() {
        command.execute();
        assertEquals(color, shape.getFillColor());
    }

    /**
     * Test of undo method, of class ChangeFillColorCommand.
     */
    @Test
    public void testUndo() {
        command.undo();   
        assertEquals(Color.WHITE, shape.getFillColor());
    }
    
}
