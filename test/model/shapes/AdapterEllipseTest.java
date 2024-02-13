/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.shapes;

import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
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
public class AdapterEllipseTest {

    static AdapterEllipse adapterInstance;

    public AdapterEllipseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        adapterInstance = new AdapterEllipse();
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
     * Test of setCenter method, of class AdapterEllipse.
     */
    @Test
    public void testSetPosition() {
        double x = 0.5;
        double y = 0.12;
        adapterInstance.setCenter(x, y);
        assertEquals(x, adapterInstance.getAdaptee().getCenterX(), 0);
        assertEquals(y, adapterInstance.getAdaptee().getCenterY(), 0);
    }

    /**
     * Test of getAdaptee method, of class AdapterEllipse.
     */
    @Test
    public void testGetAdaptee() {
        assertEquals(Ellipse.class, adapterInstance.getAdaptee().getClass());
    }

    /**
     * Test of setFillColor method, of class AdapterEllipse.
     */
    @Test
    public void testSetFillColor() {
        Color color = Color.RED;
        adapterInstance.setFillColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getFill());
    }

    /**
     * Test of setStrokeColor method, of class AdapterEllipse.
     */
    @Test
    public void testSetStrokeColor() {
        Color color = Color.AZURE;
        adapterInstance.setStrokeColor(color);
        assertEquals(color, adapterInstance.getAdaptee().getStroke());
    }

    /**
     * Test of getStrokeColor method, of class AdapterEllipse.
     */
    @Test
    public void testGetStrokeColor() {
        Color expected = Color.AQUA;
        adapterInstance.getAdaptee().setStroke(expected);
        Color result = adapterInstance.getStrokeColor();
        assertEquals(expected, result);
    }

    /**
     * Test of getFillColor method, of class AdapterEllipse.
     */
    @Test
    public void testGetFillColor() {
        Color expected = Color.YELLOWGREEN;
        adapterInstance.getAdaptee().setFill(expected);
        Color result = adapterInstance.getFillColor();
        assertEquals(expected, result);
    }

    /**
     * Test of getCenterX method, of class AdapterEllipse.
     */
    @Test
    public void testGetPositionX() {
        double expected = 34.4;
        adapterInstance.getAdaptee().setCenterX(expected);
        double result = adapterInstance.getCenterX();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of getCenterY method, of class AdapterEllipse.
     */
    @Test
    public void testGetPositionY() {
        double expected = -2.35;
        adapterInstance.getAdaptee().setCenterY(expected);
        double result = adapterInstance.getCenterY();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of getWidth method, of class AdapterEllipse.
     */
    @Test
    public void testGetWidth() {
        double expected = 11.98;
        adapterInstance.getAdaptee().setRadiusX(expected);
        double result = adapterInstance.getWidth();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of getHeight method, of class AdapterEllipse.
     */
    @Test
    public void testGetHeight() {
        double expected = 69;
        adapterInstance.getAdaptee().setRadiusY(expected);
        double result = adapterInstance.getHeight();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of setWidth method, of class AdapterEllipse.
     */
    @Test
    public void testSetWidth() {
        double expected = 4.7;
        adapterInstance.setWidth(expected);
        double result = adapterInstance.getAdaptee().getRadiusX();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of setHeight method, of class AdapterEllipse.
     */
    @Test
    public void testSetHeight() {
        double expected = 4.7;
        adapterInstance.setHeight(expected);
        double result = adapterInstance.getAdaptee().getRadiusY();
        assertEquals(expected, result, 0);
    }

    /**
     * Test of setEnd method, of class AdapterEllipse.
     */
    @Test
    public void testSetEnd() {
        double expectedX, expectedY, resultX, resultY;

        // start top left, end bottom right
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(150, 150);
        expectedX = abs(100 - 150) / 2;
        expectedY = abs(100 - 150) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from top left, end in bottom right", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from top left, end in bottom right", expectedY, resultY, 0.01);

        // start top right, end bottom left
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(50, 150);
        expectedX = abs(100 - 50) / 2;
        expectedY = abs(100 - 150) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from top right, end in bottom left", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from top right, end in bottom lett", expectedY, resultY, 0.01);

        // start bottom left, end top right
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(150, 50);
        expectedX = abs(100 - 150) / 2;
        expectedY = abs(100 - 50) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from bottom left, end in top right", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from bottom left, end in top right", expectedY, resultY, 0.01);

        // start bottom right, end top left
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(50, 50);
        expectedX = abs(100 - 50) / 2;
        expectedY = abs(100 - 50) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from bottom right, end in top left", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from bottom right, end in top left", expectedY, resultY, 0.01);

        // start left, end right
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(150, 100);
        expectedX = abs(100 - 150) / 2;
        expectedY = abs(100 - 100) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from left, end in right", expectedX, resultX, 0.01);
        assertEquals("setEnd - set x when start from left, end in right", expectedY, resultY, 0.01);

        // start right, end left
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(50, 100);
        expectedX = abs(100 - 50) / 2;
        expectedY = abs(100 - 100) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from right, end in left", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from right, end in left", expectedY, resultY, 0.01);

        // start top, end bottom
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(100, 150);
        expectedX = abs(100 - 100) / 2;
        expectedY = abs(100 - 150) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from top, end in bottom", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from top, end in bottom", expectedY, resultY, 0.01);

        // start bottom, end top
        adapterInstance.setStart(100, 100);
        adapterInstance.setEnd(100, 50);
        expectedX = abs(100 - 100) / 2;
        expectedY = abs(100 - 50) / 2;
        resultX = adapterInstance.getAdaptee().getRadiusX();
        resultY = adapterInstance.getAdaptee().getRadiusY();
        assertEquals("setEnd - set x when start from bottom, end in top", expectedX, resultX, 0.01);
        assertEquals("setEnd - set y when start from bottom, end in top", expectedY, resultY, 0.01);
    }

    /**
     * Test of setStrokeType method, of class AdapterEllipse.
     */
    @Test
    public void testSetStrokeType() {
        StrokeType strokeType = StrokeType.CENTERED;
        adapterInstance.setStrokeType(strokeType);
        assertEquals(strokeType, adapterInstance.getAdaptee().getStrokeType());
    }

    /**
     * Test of setStrokeWidth method, of class AdapterEllipse.
     */
    @Test
    public void testSetStrokeWidth() {
        double width = 10.0;
        adapterInstance.setStrokeWidth(width);
        assertEquals(width, adapterInstance.getAdaptee().getStrokeWidth(), 0.01);
    }

    /**
     * Test of makeDraggable method, of class AdapterEllipse.
     */
    @Test
    public void testMakeDraggable() {
        adapterInstance.makeDraggable();
        assertTrue(adapterInstance.getAdaptee().getOnMousePressed() instanceof EventHandler);
        assertTrue(adapterInstance.getAdaptee().getOnMouseDragged() instanceof EventHandler);
    }

    /**
     * Test of makeUndraggable method, of class AdapterEllipse.
     */
    @Test
    public void testMakeUndraggable() {
        adapterInstance.makeUndraggable();
        assertNull(adapterInstance.getAdaptee().getOnMousePressed());
        assertNull(adapterInstance.getAdaptee().getOnMouseDragged());
    }

    /**
     * Test of toString method, of class AdapterEllipse.
     */
    @Test
    public void testToString() {
        String expResult = "ellipse";
        String result = adapterInstance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of bringToFront method, of class AdapterEllipse.
     */
    @Test
    public void testBringToFront() {
        // TODO review the generated test code and remove the default call to fail.
        AdapterEllipse shapeStub = new AdapterEllipse(new Ellipse(0, 0, 400, 200));
        AdapterEllipse shapeStub2 = new AdapterEllipse(new Ellipse(100, 100, 400, 200));
        AdapterEllipse shapeStub3 = new AdapterEllipse(new Ellipse(200, 200, 400, 200));

        AnchorPane canvas = new AnchorPane();
        canvas.getChildren().add(shapeStub.getAdaptee());
        canvas.getChildren().add(shapeStub2.getAdaptee());
        canvas.getChildren().add(shapeStub3.getAdaptee());
        canvas.getChildren().add(adapterInstance.getAdaptee());

        adapterInstance.bringToFront();

        int result = canvas.getChildren().indexOf(adapterInstance.getAdaptee());
        int expectedResult = 3;
        assertEquals(expectedResult, result);
    }

    /**
     * Test of sendToBack method, of class AdapterEllipse.
     */
    @Test
    public void testSendToBack() {
        AdapterEllipse shapeStub = new AdapterEllipse(new Ellipse(0, 0, 400, 200));
        AdapterEllipse shapeStub2 = new AdapterEllipse(new Ellipse(100, 100, 400, 200));
        AdapterEllipse shapeStub3 = new AdapterEllipse(new Ellipse(200, 200, 400, 200));

        AnchorPane canvas = new AnchorPane();
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
