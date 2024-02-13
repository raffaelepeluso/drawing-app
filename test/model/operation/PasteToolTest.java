/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.operation;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.commands.Invoker;
import model.shapes.AdapterRectangle;
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
public class PasteToolTest {
    
    private static Pane pane;
    private static PasteTool tool;
    private static AdapterRectangle shape;
    private static ShapeClipboard clipboard;
    
    public PasteToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        pane = new Pane();
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tool.useTool(event);
            }
        });
        tool = new PasteTool(new Invoker());
        clipboard = ShapeClipboard.getInstance();
        shape = new AdapterRectangle();
        shape.setHeight(100);
        shape.setWidth(50);
        shape.setFillColor(Color.CORAL);
        shape.setStrokeColor(Color.BEIGE);
        clipboard.addShape(shape);
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
     * Test of useTool method, of class PasteTool.
     */
    @Test
    public void testUseTool() {
        Event.fireEvent(pane, new MouseEvent(pane, pane, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, false, null));
        Rectangle expected = (Rectangle) pane.getChildren().get(0);
        assertEquals(expected.getHeight(), shape.getHeight(), 0);
        assertEquals(expected.getWidth(), shape.getWidth(), 0);
        assertEquals(expected.getFill(), shape.getFillColor());
        assertEquals(expected.getStroke(), shape.getStrokeColor());
    }
    
}
