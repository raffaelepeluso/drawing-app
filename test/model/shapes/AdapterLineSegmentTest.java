/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.shapes;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco
 */
public class AdapterLineSegmentTest {

    static AdapterLineSegment adapterInstance;

    public AdapterLineSegmentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        adapterInstance = new AdapterLineSegment();
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
     * Test of setFillColor method, of class AdapterLineSegment.
     */
    @Test
    public void testSetFillColor() {
        Color color = Color.RED;
        adapterInstance.setFillColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getFill());
    }

    /**
     * Test of setStrokeColor method, of class AdapterLineSegment.
     */
    @Test
    public void testSetStrokeColor() {
        Color color = Color.AZURE;
        adapterInstance.setStrokeColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getStroke());
    }

    /**
     * Test of getStrokeColor method, of class AdapterLineSegment.
     */
    @Test
    public void testGetStrokeColor() {
        Color expected = Color.AQUA;
        adapterInstance.getAdaptee().setStroke(expected);
        Color result = adapterInstance.getStrokeColor();
        assertEquals(expected, result);
    }

    /**
     * Test of getFillColor method, of class AdapterLineSegment.
     */
    @Test
    public void testGetFillColor() {
        Color expected = Color.YELLOWGREEN;
        adapterInstance.getAdaptee().setFill(expected);
        Color result = adapterInstance.getFillColor();
        assertEquals(expected, result);
    }

    /**
     * Test of setStart method, of class AdapterLineSegment.
     */
    @Test
    public void testSetStart() {
        double x = 0.0;
        double y = 0.0;
        AdapterLineSegment instance = new AdapterLineSegment();
        instance.setStart(x, y);

        assertEquals(x, instance.getAdaptee().getStartX(), 0.01);
        assertEquals(y, instance.getAdaptee().getStartY(), 0.01);
    }

    /**
     * Test of setEnd method, of class AdapterLineSegment.
     */
    @Test
    public void testSetEnd() {
        double x = 0.0;
        double y = 0.0;
        AdapterLineSegment instance = new AdapterLineSegment();
        instance.setEnd(x, y);

        assertEquals(x, instance.getAdaptee().getEndX(), 0.01);
        assertEquals(y, instance.getAdaptee().getEndY(), 0.01);

    }

    /**
     * Test of getAdaptee method, of class AdapterLineSegment.
     */
    @Test
    public void testGetAdaptee() {
        assertEquals(Line.class, adapterInstance.getAdaptee().getClass());
    }

    /**
     * Test of setCenter method, of class AdapterLineSegment.
     */
    @Test
    public void testSetPosition() {
        double x = 0.5;
        double y = 0.12;
        adapterInstance.setCenter(x, y);
        assertEquals(x, adapterInstance.getCenterX(), 0.01);
        assertEquals(y, adapterInstance.getCenterY(), 0.01);
    }

    /**
     * Test of getCenterX method, of class AdapterLineSegment.
     */
    @Test
    public void testGetPositionX() {
        Line line = new Line(0, 0, 100, 100);
        AdapterLineSegment instance = new AdapterLineSegment(line);
        double expResult = 50;
        double result = instance.getCenterX();
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of getCenterY method, of class AdapterLineSegment.
     */
    @Test
    public void testGetPositionY() {
        Line line = new Line(0, 0, 100, 100);
        AdapterLineSegment instance = new AdapterLineSegment(line);
        double expResult = 50;
        double result = instance.getCenterY();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getWidth method, of class AdapterLineSegment.
     */
    @Test
    public void testGetWidth() {
        Line line = new Line(0, 0, 100, 100);
        AdapterLineSegment instance = new AdapterLineSegment(line);
        double expResult = 100;
        double result = instance.getWidth();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getHeight method, of class AdapterLineSegment.
     */
    @Test
    public void testGetHeight() {
        Line line = new Line(0, 0, 100, 100);
        AdapterLineSegment instance = new AdapterLineSegment(line);
        double expResult = 100;
        double result = instance.getHeight();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of setWidth method, of class AdapterLineSegment.
     */
    @Test
    public void testSetWidth() {
        double expected = 4.7;
        adapterInstance.setWidth(expected);
        double result = adapterInstance.getWidth();
        assertEquals(expected, result, 0.01);
    }

    /**
     * Test of setHeight method, of class AdapterLineSegment.
     */
    @Test
    public void testSetHeight() {
        double expected = 4.7;
        adapterInstance.setHeight(expected);
        double result = adapterInstance.getHeight();
        assertEquals(expected, result, 0.01);
    }

    /**
     * Test of setStrokeType method, of class AdapterLineSegment.
     */
    @Test
    public void testSetStrokeType() {
        StrokeType strokeType = StrokeType.OUTSIDE;
        StrokeType strokeType2 = StrokeType.CENTERED;

        adapterInstance.setStrokeType(strokeType);
        assertEquals(strokeType, adapterInstance.getAdaptee().getStrokeType());

        adapterInstance.setStrokeType(strokeType2);
        assertEquals(strokeType2, adapterInstance.getAdaptee().getStrokeType());

    }

    /**
     * Test of setStrokeWidth method, of class AdapterLineSegment.
     */
    @Test
    public void testSetStrokeWidth() {
        double width = 5.0;
        double width2 = 15.6;

        adapterInstance.setStrokeWidth(width);
        assertEquals(width, adapterInstance.getAdaptee().getStrokeWidth(), 0.01);

        adapterInstance.setStrokeWidth(width2);
        assertEquals(width2, adapterInstance.getAdaptee().getStrokeWidth(), 0.01);

    }

    /**
     * Test of makeDraggable method, of class AdapterRectangle.
     */
    @Test
    public void testMakeDraggable() {
        adapterInstance.makeDraggable();
        assertTrue(adapterInstance.getAdaptee().getOnMousePressed() instanceof EventHandler);
        assertTrue(adapterInstance.getAdaptee().getOnMouseDragged() instanceof EventHandler);

    }

    /**
     * Test of makeUndraggable method, of class AdapterRectangle.
     */
    @Test
    public void testMakeUndraggable() {
        adapterInstance.makeUndraggable();
        assertNull(adapterInstance.getAdaptee().getOnMousePressed());
        assertNull(adapterInstance.getAdaptee().getOnMouseDragged());

    }

    /**
     * Test of toString method, of class AdapterLineSegment.
     */
    @Test
    public void testToString() {
        String expResult = "line";
        String result = adapterInstance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of bringToFront method, of class AdapterLineSegment.
     */
    @Test
    public void testBringToFront() {
        AdapterLineSegment shapeStub = new AdapterLineSegment(new Line());
        AdapterLineSegment shapeStub2 = new AdapterLineSegment(new Line());
        AdapterLineSegment shapeStub3 = new AdapterLineSegment(new Line());

        Pane canvas = new Pane();
        canvas.getChildren().add(adapterInstance.getAdaptee());
        canvas.getChildren().add(shapeStub.getAdaptee());
        canvas.getChildren().add(shapeStub2.getAdaptee());
        canvas.getChildren().add(shapeStub3.getAdaptee());

        adapterInstance.bringToFront();

        int result = canvas.getChildren().indexOf(adapterInstance.getAdaptee());
        int expectedResult = 3;
        assertEquals(expectedResult, result);
    }

    /**
     * Test of sendToBack method, of class AdapterLineSegment.
     */
    @Test
    public void testSendToBack() {
        AdapterLineSegment shapeStub = new AdapterLineSegment(new Line());
        AdapterLineSegment shapeStub2 = new AdapterLineSegment(new Line());
        AdapterLineSegment shapeStub3 = new AdapterLineSegment(new Line());

        Pane canvas = new Pane();
        canvas.getChildren().add(shapeStub.getAdaptee());
        canvas.getChildren().add(shapeStub2.getAdaptee());
        canvas.getChildren().add(shapeStub3.getAdaptee());
        canvas.getChildren().add(adapterInstance.getAdaptee());

        adapterInstance.sendToBack();

        int result = canvas.getChildren().indexOf(adapterInstance.getAdaptee());
        int expectedResult = 0;
        assertEquals(expectedResult, result);
    }

}
