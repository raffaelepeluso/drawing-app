/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.paint.Color;
import model.shapes.AdapterLineSegment;
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
public class ChangeStrokeColorCommandTest {
    
    static AdapterShape shape;
    static Command command;
    static Color color = Color.CHOCOLATE;
    
    public ChangeStrokeColorCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        shape = new AdapterLineSegment();
        command = new ChangeStrokeColorCommand(shape, color);
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
     * Test of execute method, of class ChangeStrokeColorCommand.
     */
    @Test
    public void testExecute() {
        command.execute();
        assertEquals(color, shape.getStrokeColor());
    }

    /**
     * Test of undo method, of class ChangeStrokeColorCommand.
     */
    @Test
    public void testUndo() {
        command.undo();   
        assertEquals(Color.BLACK, shape.getStrokeColor());
    }
    
}
