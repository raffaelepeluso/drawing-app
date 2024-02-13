/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.shapes;

import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
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
public class AdapterRectangleTest {

    static AdapterRectangle adapterInstance;

    public AdapterRectangleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        adapterInstance = new AdapterRectangle();
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
     * Test of setCenter method, of class AdapterRectangle.
     */
    @Test
    public void testSetPosition() {
        double x = -0.5;
        double y = 0.12;
        adapterInstance.setCenter(x, y);
        Rectangle adaptee = adapterInstance.getAdaptee();
        double resultX = adaptee.getX() + adaptee.getWidth() / 2;
        double resultY = adaptee.getY() + adaptee.getHeight() / 2;
        assertEquals(x, resultX, 0.01);
        assertEquals(y, resultY, 0.01);
    }

    /**
     * Test of getAdaptee method, of class AdapterRectangle.
     */
    @Test
    public void testGetAdaptee() {
        assertEquals(Rectangle.class, adapterInstance.getAdaptee().getClass());
    }

    /**
     * Test of setFillColor method, of class AdapterRectangle.
     */
    @Test
    public void testSetFillColor() {
        Color color = Color.HOTPINK;
        adapterInstance.setFillColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getFill());
    }

    /**
     * Test of setStrokeColor method, of class AdapterRectangle.
     */
    @Test
    public void testSetStrokeColor() {
        Color color = Color.BROWN;
        adapterInstance.setStrokeColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getStroke());
    }

    /**
     * Test of getStrokeColor method, of class AdapterRectangle.
     */
    @Test
    public void testGetStrokeColor() {
        Color expected = Color.FUCHSIA;
        adapterInstance.getAdaptee().setStroke(expected);
        Color result = adapterInstance.getStrokeColor();
        assertEquals(expected, result);
    }

    /**
     * Test of getFillColor method, of class AdapterRectangle.
     */
    @Test
    public void testGetFillColor() {
        Color expected = Color.OLIVE;
        adapterInstance.getAdaptee().setFill(expected);
        Color result = adapterInstance.getFillColor();
        assertEquals(expected, result);
    }

    /**
     * Test of getCenterX method, of class AdapterRectangle.
     */
    @Test
    public void testGetPositionX() {
        Rectangle shape = new Rectangle(0, 0, 200, 100);
        AdapterRectangle instance = new AdapterRectangle(shape);
        assertEquals(instance.getCenterX(), 100, 0.01);
    }

    /**
     * Test of getCenterY method, of class AdapterRectangle.
     */
    @Test
    public void testGetPositionY() {
        Rectangle shape = new Rectangle(0, 0, 200, 100);
        AdapterRectangle instance = new AdapterRectangle(shape);
        assertEquals(instance.getCenterY(), 50, 0.01);
    }

    /**
     * Test of getWidth method, of class AdapterRectangle.
     */
    @Test
    public void testGetWidth() {
        double expected = 27.4;
        adapterInstance.getAdaptee().setWidth(expected);
        double result = adapterInstance.getWidth();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of getHeight method, of class AdapterRectangle.
     */
    @Test
    public void testGetHeight() {
        double expected = 9;
        adapterInstance.getAdaptee().setHeight(expected);
        double result = adapterInstance.getHeight();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of setWidth method, of class AdapterRectangle.
     */
    @Test
    public void testSetWidth() {
        double expected = 43.2;
        adapterInstance.setWidth(expected);
        double result = adapterInstance.getAdaptee().getWidth();
        assertEquals(expected, result, 0.01);
    }

    /**
     * Test of setHeight method, of class AdapterRectangle.
     */
    @Test
    public void testSetHeight() {
        double expected = 122;
        adapterInstance.setHeight(expected);
        double result = adapterInstance.getAdaptee().getHeight();
        assertEquals(expected, result, 0.01);
    }

    /**
     * Test of setStart method, of class AdapterRectangle.
     */
    @Test
    public void testSetStart() {
        double expectedX = 10.0;
        double expectedY = 15.0;
        adapterInstance.setStart(expectedX, expectedY);
        double resultX = adapterInstance.getAdaptee().getX();
        double resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);
    }

    /**
     * Test of setEnd method, of class AdapterRectangle.
     */
    @Test
    public void testSetEnd() {
        double expectedX, expectedY, endX, endY, resultX, resultY, expectedWidth, expectedHeight, resultWidth, resultHeight;
        double startX = 100;
        double startY = 100;
        // start top left, end bottom right
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 150;
        endY = 150;
        adapterInstance.setEnd(endX, endY);
        expectedX = 100;
        expectedY = 100;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start top right, end bottom left
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 50;
        endY = 150;
        adapterInstance.setEnd(endX, endY);
        expectedX = 50;
        expectedY = 100;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start bottom left, end top right
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 150;
        endY = 50;
        adapterInstance.setEnd(endX, endY);
        expectedX = 100;
        expectedY = 50;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start bottom right, end top left
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 50;
        endY = 50;
        adapterInstance.setEnd(endX, endY);
        expectedX = 50;
        expectedY = 50;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start left, end right
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 150;
        endY = 100;
        adapterInstance.setEnd(endX, endY);
        expectedX = 100;
        expectedY = 100;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start right, end left
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 50;
        endY = 100;
        adapterInstance.setEnd(endX, endY);
        expectedX = 50;
        expectedY = 100;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start top, end bottom
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 100;
        endY = 150;
        adapterInstance.setEnd(endX, endY);
        expectedX = 100;
        expectedY = 100;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);

        // start bottom, end top
        adapterInstance.getAdaptee().setX(startX);
        adapterInstance.getAdaptee().setY(startY);
        endX = 100;
        endY = 50;
        adapterInstance.setEnd(endX, endY);
        expectedX = 100;
        expectedY = 50;
        resultX = adapterInstance.getAdaptee().getX();
        resultY = adapterInstance.getAdaptee().getY();
        assertEquals(expectedX, resultX, 0.01);
        assertEquals(expectedY, resultY, 0.01);

        expectedWidth = abs(startX - endX);
        expectedHeight = abs(startY - endY);
        resultWidth = adapterInstance.getAdaptee().getWidth();
        resultHeight = adapterInstance.getAdaptee().getHeight();
        assertEquals(expectedWidth, resultWidth, 0.01);
        assertEquals(expectedHeight, resultHeight, 0.01);
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
     * Test of setStrokeType method, of class AdapterRectangle.
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
     * Test of setStrokeWidth method, of class AdapterRectangle.
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
     * Test of toString method, of class AdapterRectangle.
     */
    @Test
    public void testToString() {
        String expResult = "rectangle";
        String result = adapterInstance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of bringToFront method, of class AdapterRectangle.
     */
    @Test
    public void testBringToFront() {
        AdapterRectangle shapeStub = new AdapterRectangle(new Rectangle(0, 0, 400, 200));
        AdapterRectangle shapeStub2 = new AdapterRectangle(new Rectangle(100, 100, 400, 200));
        AdapterRectangle shapeStub3 = new AdapterRectangle(new Rectangle(200, 200, 400, 200));

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
     * Test of sendToBack method, of class AdapterRectangle.
     */
    @Test
    public void testSendToBack() {
        AdapterRectangle shapeStub = new AdapterRectangle(new Rectangle(0, 0, 400, 200));
        AdapterRectangle shapeStub2 = new AdapterRectangle(new Rectangle(100, 100, 400, 200));
        AdapterRectangle shapeStub3 = new AdapterRectangle(new Rectangle(200, 200, 400, 200));

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
