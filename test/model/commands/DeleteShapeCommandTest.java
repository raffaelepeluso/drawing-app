/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.layout.Pane;
import model.shapes.AdapterEllipse;
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
 * @author pelus
 */
public class DeleteShapeCommandTest {
    
    static Pane pane;
    static AdapterShape shape1, shape2, shape3;
    static DeleteShapeCommand command1, command2, command3;
    
    public DeleteShapeCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        pane = new Pane();
        shape1 = new AdapterEllipse();
        shape2 = new AdapterRectangle();
        shape3 = new AdapterLineSegment();
        pane.getChildren().add(shape1.getAdaptee());
        pane.getChildren().add(shape2.getAdaptee());
        pane.getChildren().add(shape3.getAdaptee());
        command1 = new DeleteShapeCommand(pane, shape1);
        command2 = new DeleteShapeCommand(pane, shape2);
        command3 = new DeleteShapeCommand(pane, shape3);
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
     * Test of execute method, of class DeleteShapeCommand.
     */
    @Test
    public void testExecute() {
        command2.execute();
        command1.execute();
        command3.execute();
        assertFalse(pane.getChildren().contains(shape1.getAdaptee()));
        assertFalse(pane.getChildren().contains(shape2.getAdaptee()));
        assertFalse(pane.getChildren().contains(shape3.getAdaptee()));
    }

    /**
     * Test of undo method, of class DeleteShapeCommand.
     */
    @Test
    public void testUndo() {
        command3.undo();
        command1.undo();
        command2.undo();
        assertEquals(2, pane.getChildren().indexOf(shape3.getAdaptee()));
        assertEquals(0, pane.getChildren().indexOf(shape1.getAdaptee()));
        assertEquals(1, pane.getChildren().indexOf(shape2.getAdaptee()));
    }
    
}
