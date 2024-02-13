/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.shapes.AdapterRectangle;
import model.shapes.AdapterShape;
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
public class SendToBackCommandTest {

    static AdapterShape shapeStub, shapeStub2, shapeStub3;
    static SendToBackCommand commandInstance, commandInstance2, commandInstance3;
    static Pane canvas;

    public SendToBackCommandTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        shapeStub = new AdapterRectangle(new Rectangle(0, 0, 400, 200));
        shapeStub2 = new AdapterRectangle(new Rectangle(100, 100, 400, 200));
        shapeStub3 = new AdapterRectangle(new Rectangle(200, 200, 400, 200));

        canvas = new Pane();
        commandInstance = new SendToBackCommand(canvas, shapeStub);
        commandInstance2 = new SendToBackCommand(canvas, shapeStub2);
        commandInstance3 = new SendToBackCommand(canvas, shapeStub3);

        canvas.getChildren().add(shapeStub.getAdaptee());
        canvas.getChildren().add(shapeStub2.getAdaptee());
        canvas.getChildren().add(shapeStub3.getAdaptee());

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
     * Test of execute method, of class SendToBackCommand.
     */
    @Test
    public void testExecute() {
        commandInstance.execute();
        assertEquals(0, canvas.getChildren().indexOf(shapeStub.getAdaptee()));

        commandInstance2.execute();
        assertEquals(0, canvas.getChildren().indexOf(shapeStub2.getAdaptee()));

        commandInstance3.execute();
        assertEquals(0, canvas.getChildren().indexOf(shapeStub3.getAdaptee()));
    }

    /**
     * Test of undo method, of class SendToBackCommand.
     */
    @Test
    public void testUndo() {
        commandInstance3.undo();
        assertEquals(2, canvas.getChildren().indexOf(shapeStub3.getAdaptee()));

        commandInstance2.undo();
        assertEquals(1, canvas.getChildren().indexOf(shapeStub2.getAdaptee()));

        commandInstance.undo();
        assertEquals(0, canvas.getChildren().indexOf(shapeStub.getAdaptee()));
    }

}
