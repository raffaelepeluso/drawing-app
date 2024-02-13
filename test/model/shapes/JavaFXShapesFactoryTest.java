/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.shapes;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
public class JavaFXShapesFactoryTest {

    static private JavaFXShapesFactory factory;

    public JavaFXShapesFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        factory = JavaFXShapesFactory.getFactory();
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
     * Test of getFactory method, of class JavaFXShapesFactory.
     */
    @Test
    public void testGetFactory() {
        JavaFXShapesFactory result = JavaFXShapesFactory.getFactory();
        assertTrue(result instanceof JavaFXShapesFactory);

    }

    /**
     * Test of createRectangle method, of class JavaFXShapesFactory.
     */
    @Test
    public void testCreateRectangle() {
        AdapterShape expResult = new AdapterRectangle();
        AdapterShape result = factory.createRectangle();
        assertEquals(((Rectangle) expResult.getAdaptee()).getWidth(), ((Rectangle) result.getAdaptee()).getWidth(), 0);
        assertEquals(((Rectangle) expResult.getAdaptee()).getHeight(), ((Rectangle) result.getAdaptee()).getHeight(), 0);
        assertEquals(((Rectangle) expResult.getAdaptee()).getFill(), ((Rectangle) result.getAdaptee()).getFill());
        assertEquals(((Rectangle) expResult.getAdaptee()).getStroke(), ((Rectangle) result.getAdaptee()).getStroke());

    }

    /**
     * Test of createEllipse method, of class JavaFXShapesFactory.
     */
    @Test
    public void testCreateEllipse() {
        AdapterShape expResult = new AdapterEllipse();
        AdapterShape result = factory.createEllipse();
        assertEquals(((Ellipse) expResult.getAdaptee()).getCenterX(), ((Ellipse) result.getAdaptee()).getCenterX(), 0);
        assertEquals(((Ellipse) expResult.getAdaptee()).getCenterY(), ((Ellipse) result.getAdaptee()).getCenterY(), 0);
        assertEquals(((Ellipse) expResult.getAdaptee()).getFill(), ((Ellipse) result.getAdaptee()).getFill());
        assertEquals(((Ellipse) expResult.getAdaptee()).getStroke(), ((Ellipse) result.getAdaptee()).getStroke());

    }

    /**
     * Test of createLineSegment method, of class JavaFXShapesFactory.
     */
    @Test
    public void testCreateLineSegment() {
        AdapterShape expResult = new AdapterLineSegment();
        AdapterShape result = factory.createLineSegment();
        assertEquals(((Line) expResult.getAdaptee()).getStartX(), ((Line) result.getAdaptee()).getStartX(), 0);
        assertEquals(((Line) expResult.getAdaptee()).getEndX(), ((Line) result.getAdaptee()).getEndX(), 0);
        assertEquals(((Line) expResult.getAdaptee()).getStartY(), ((Line) result.getAdaptee()).getStartY(), 0);
        assertEquals(((Line) expResult.getAdaptee()).getEndY(), ((Line) result.getAdaptee()).getEndY(), 0);

    }

}
