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
import javafx.scene.shape.Ellipse;
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
public class EllipseInsertToolTest {
    static private EllipseInsertTool instance;
    static private Pane pane;
    static private Invoker invoker;
    
    public EllipseInsertToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        pane = new Pane();
        pane.setOnMouseMoved((event) -> instance.previewShape(event));
        pane.setOnMouseClicked((event) -> instance.useTool(event));
        invoker = new Invoker();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new EllipseInsertTool(invoker);
    }
    
    @After
    public void tearDown() {
        pane.getChildren().clear();
    }

    /**
     * Test of useTool method, of class EllipseInsertTool.
     */
    @Test
    public void testUseTool() {
        //first click -> the prewiew is shown
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertFalse(pane.getChildren().isEmpty());
        assertTrue(pane.getChildren().get(0) instanceof Ellipse);
        AdapterShape e = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(Color.BLUEVIOLET,e.getStrokeColor());
        assertEquals(Color.TRANSPARENT,e.getFillColor());
        
        //second click -> ellipse created with correct size
        Event.fireEvent(pane,new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 100, 200, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(1,pane.getChildren().size());
        assertTrue(pane.getChildren().get(0) instanceof Ellipse);
        e = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(40,e.getWidth(),0);
        assertEquals(90,e.getHeight(),0);
        
    }

    /**
     * Test of resetTool method, of class EllipseInsertTool.
     */
    @Test
    public void testResetTool() {
        //first click + mouse moved -> prewiew shown and resized. On reset tool prewiew eliminated
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 30, 30, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        AdapterShape r = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        instance.resetTool();
        assertTrue(pane.getChildren().isEmpty());
        assertEquals(0,r.getWidth(),0);
        assertEquals(0,r.getHeight(),0);
    }

    /**
     * Test of previewShape method, of class EllipseInsertTool.
     */
    @Test
    public void testPreviewShape() {
        //fist click + mouse moved -> prewiew added to pane and resized in accordance with the movement of the mouse 
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 20, 20, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 30, 30, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        AdapterShape e = JavaFXShapesFactory.getFactory().createAdapter((Shape)pane.getChildren().get(0));
        assertEquals(5,e.getWidth(),0);
        assertEquals(5,e.getHeight(),0);
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_MOVED, 100, 120, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(40,e.getWidth(),0);
        assertEquals(50,e.getHeight(),0);
    }
    
}
