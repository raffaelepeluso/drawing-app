/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.operation;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import model.commands.Invoker;
import model.shapes.AdapterShape;
import model.shapes.JavaFXShapesFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utente
 */
public class LineInsertToolTest {
    static private LineInsertTool instance;
    static private Pane pane;
    static private Invoker invoker;
    
    public LineInsertToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        invoker = new Invoker();
        pane = new Pane();
        
        pane.setOnMouseClicked((event) -> instance.useTool(event));
        pane.setOnMouseMoved((event) -> instance.previewShape(event));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new LineInsertTool(invoker);
    }
    
    @After
    public void tearDown() {
        pane.getChildren().clear();
    }

    /**
     * Test of useTool method, of class LineInsertTool.
     */
    @Test
    public void testUseTool() {
        //first click -> prewiew created and added to pane
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertFalse(pane.getChildren().isEmpty());
        assertTrue(pane.getChildren().get(0) instanceof Line);
        AdapterShape l = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(Color.BLUEVIOLET,l.getStrokeColor());
        assertEquals(Color.TRANSPARENT,l.getFillColor());
        
        //second click -> prewiew eliminated, line segment added to pane having correct dimensions
        Event.fireEvent(pane,new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 100, 200, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(1,pane.getChildren().size());
        assertTrue(pane.getChildren().get(0) instanceof Line);
        l = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(80,l.getWidth(),0);
        assertEquals(180,l.getHeight(),0);
        
        //first and second click on the same point -> line created with dimensions = 0
        Event.fireEvent(pane,new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 80, 40, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(pane,new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 80, 40, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(2,pane.getChildren().size());
        assertTrue(pane.getChildren().get(1) instanceof Line);
        l = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(1));
        assertEquals(0,l.getWidth(),0);
        assertEquals(0,l.getHeight(),0);
    }

    /**
     * Test of resetTool method, of class LineInsertTool.
     */
    @Test
    public void testResetTool() {
        //mouse click + move -> preview is shown. On reset prewiew eliminated
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 30, 30, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        AdapterShape r = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        instance.resetTool();
        assertTrue(pane.getChildren().isEmpty());
        assertEquals(0,r.getWidth(),0);
        assertEquals(0,r.getHeight(),0);
    }

    /**
     * Test of previewShape method, of class LineInsertTool.
     */
    @Test
    public void testPreviewShape() {
        //mouse click -> preview added to pane and shown. Mouse move -> preview resized accordling
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 30, 30, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        AdapterShape r = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(10,r.getWidth(),0);
        assertEquals(10,r.getHeight(),0);
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 100, 120, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(80,r.getWidth(),0);
        assertEquals(100,r.getHeight(),0);
    }
    
}
