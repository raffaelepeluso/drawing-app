/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.operation;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.shapes.AdapterEllipse;
import model.shapes.AdapterShape;
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
public class SelectionToolTest {

    static private AnchorPane a;
    static private SelectionTool instance;
    static private Rectangle rectangle;
    static private Ellipse ellipse;

    public SelectionToolTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        a = new AnchorPane();

        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                instance.useTool(event);
            }
        });

        rectangle = new Rectangle(210, 100);
        ellipse = new Ellipse(100, 50);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new SelectionTool();

        a.getChildren().add(rectangle);
        a.getChildren().add(ellipse);
    }

    @After
    public void tearDown() {
        a.getChildren().clear();
    }

    /**
     * Test of useTool method, of class SelectionTool.
     */
    @Test
    public void testUseTool() {

        //click on rectangle -> rectangle selected
        Event.fireEvent(rectangle, new MouseEvent(a, rectangle, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(instance.isSelected());
        assertSame(rectangle, instance.getSelectedShape().getAdaptee());
        assertEquals(StrokeType.OUTSIDE, rectangle.getStrokeType());
        assertEquals(4.0, rectangle.getStrokeWidth(), 0);

        //click on anchor pane -> deselection
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertFalse(instance.isSelected());
        assertNull(instance.getSelectedShape());
        assertEquals(StrokeType.CENTERED, ellipse.getStrokeType());
        assertEquals(1.0, ellipse.getStrokeWidth(), 0);

        //click on the same rectangle -> rectangle selected
        Event.fireEvent(rectangle, new MouseEvent(a, rectangle, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(instance.isSelected());
        assertSame(rectangle, instance.getSelectedShape().getAdaptee());
        assertEquals(StrokeType.OUTSIDE, rectangle.getStrokeType());
        assertEquals(4.0, rectangle.getStrokeWidth(), 0);

        //click on the ellipse -> ellipse selected, rectangle deselected
        Event.fireEvent(ellipse, new MouseEvent(a, ellipse, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(instance.isSelected());
        assertSame(ellipse, instance.getSelectedShape().getAdaptee());
        assertEquals(StrokeType.OUTSIDE, ellipse.getStrokeType());
        assertEquals(4.0, ellipse.getStrokeWidth(), 0);
        assertEquals(StrokeType.CENTERED, rectangle.getStrokeType());
        assertEquals(1.0, rectangle.getStrokeWidth(), 0);

    }

    /**
     * Test of resetTool method, of class SelectionTool.
     */
    @Test
    public void testResetTool() {
        //shape selected
        Event.fireEvent(rectangle, new MouseEvent(a, rectangle, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(instance.isSelected());

        //reset selection
        instance.resetTool();
        assertFalse(instance.isSelected());
        assertNull(instance.getSelectedShape());
        assertEquals(StrokeType.CENTERED, rectangle.getStrokeType());
        assertEquals(1.0, rectangle.getStrokeWidth(), 0);
    }

    /**
     * Test of getSelectedShape method, of class SelectionTool.
     */
    @Test
    public void testGetSelectedShape() {
        AdapterShape shape = new AdapterEllipse(ellipse);

        //get selected shape
        Event.fireEvent(ellipse, new MouseEvent(a, ellipse, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(shape.getAdaptee(), instance.getSelectedShape().getAdaptee());

        //no shape selected -> metod returns null
        instance.resetTool();
        assertFalse(instance.isSelected());
        assertNull(instance.getSelectedShape());

    }

}
