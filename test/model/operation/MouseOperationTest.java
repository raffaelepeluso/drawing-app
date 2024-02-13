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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
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
public class MouseOperationTest {

    private static MouseOperation instance;
    private static Tool insertLine, selectShape, rectangleInsert;
    private static Pane a;

    public MouseOperationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        a = new Pane();

        a.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                instance.useTool(event);
            }
        });
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        insertLine = new LineInsertTool(new Invoker());
        selectShape = new SelectionTool();
        rectangleInsert = new RectangleInsertTool(new Invoker());
        instance = new MouseOperation(selectShape);

    }

    @After
    public void tearDown() {
        a.getChildren().clear();
    }

    /**
     * Test of setTool method, of class MouseOperation.
     */
    @Test
    public void testSetTool() {
        Rectangle r = new Rectangle(200, 110);
        
        //Selection tool setted -> use tool select target shape
        MouseEvent event = new MouseEvent(a, r, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null);
        selectShape.useTool(event);
        assertEquals(StrokeType.OUTSIDE, r.getStrokeType());
        assertEquals(4.0, r.getStrokeWidth(), 0);

        //veifying set tool reset SelectionTool
        instance.setTool(insertLine);
        assertEquals(StrokeType.CENTERED, r.getStrokeType());
        assertEquals(1.0, r.getStrokeWidth(), 0);

        //verifying setTool reset LineInsertTool
        event = new MouseEvent(a, r, MouseEvent.MOUSE_CLICKED, 40, 30, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null);
        insertLine.useTool(event);
        instance.setTool(rectangleInsert);
        assertTrue(a.getChildren().isEmpty());
    }

    /**
     * Test of useTool method, of class MouseOperation.
     */
    @Test
    public void testUseTool() {
        instance.setTool(rectangleInsert);

        //tool setted -> rectangleInsert, verifying useTool is using rectangleInsert
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 200, 300, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertFalse(a.getChildren().isEmpty());
        assertTrue(a.getChildren().get(0) instanceof Rectangle);

        Rectangle rectangle = (Rectangle) a.getChildren().get(0);

        //tool setted ->selectShape, verifying useTool is using selectShape
        instance.setTool(selectShape);
        Event.fireEvent(rectangle, new MouseEvent(a, rectangle, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertEquals(StrokeType.OUTSIDE, rectangle.getStrokeType());
        assertEquals(4.0, rectangle.getStrokeWidth(), 0);

        //too setted -> insertLine, verifying useTool is using insertLine
        instance.setTool(insertLine);
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 100, 200, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(a.getChildren().get(1) instanceof Line);
    }

    /**
     * Test of previewShape method, of class MouseOperation.
     */
    @Test
    public void testPreviewShape() {
        instance.setTool(insertLine);
        
        //LineInsertTool setted -> is shown the correct preview
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(a.getChildren().get(0) instanceof Line);
        AdapterShape l = JavaFXShapesFactory.getFactory().createAdapter((Shape)a.getChildren().get(0));
        assertEquals(Color.BLUEVIOLET,l.getStrokeColor());
        assertEquals(Color.TRANSPARENT,l.getFillColor());
        
        //RectangleInsertTool setted -> in accordance with the movement of the mouse
        instance.setTool(rectangleInsert);
        Event.fireEvent(a, new MouseEvent(a, a, MouseEvent.MOUSE_CLICKED, 300, 400, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        assertTrue(a.getChildren().get(0) instanceof Rectangle);
        AdapterShape r = JavaFXShapesFactory.getFactory().createAdapter((Shape)a.getChildren().get(0));
        assertEquals(Color.BLUEVIOLET,l.getStrokeColor());
        assertEquals(Color.TRANSPARENT,l.getFillColor());
    }

}
