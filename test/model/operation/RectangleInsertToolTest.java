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
import javafx.scene.shape.Rectangle;
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
public class RectangleInsertToolTest {
    static private RectangleInsertTool instance;
    static private Pane pane;
    static private Invoker invoker;
    public RectangleInsertToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        pane = new Pane();
        invoker = new Invoker();
        pane.setOnMouseClicked((event) -> instance.useTool(event));
        pane.setOnMouseMoved((event) -> instance.previewShape(event));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new RectangleInsertTool(invoker);
    }
    
    @After
    public void tearDown() {
        pane.getChildren().clear();
        
    }

    /**
     * Test of useTool method, of class RectangleInsertTool.
     */
    @Test
    public void testUseTool() {
        //at first click a preview is created
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(instance.isStarted());
        assertFalse(pane.getChildren().isEmpty());
        assertTrue(pane.getChildren().get(0) instanceof Rectangle);
        AdapterShape r = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(Color.BLUEVIOLET,r.getStrokeColor());
        assertEquals(Color.TRANSPARENT,r.getFillColor());
        
        //second click -> preview deleted, rectangle created
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 40, 60, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(1,pane.getChildren().size());
        assertTrue(pane.getChildren().get(0) instanceof Rectangle);
        r = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(20,r.getWidth(),0);
        assertEquals(40,r.getHeight(),0);
        
    }

    /**
     * Test of previewShape method, of class RectangleInsertTool.
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


    /**
     * Test of resetTool method, of class RectangleInsertTool.
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
    
}
