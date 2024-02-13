/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.operation;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import model.commands.AddShapeCommand;
import model.commands.Command;
import model.commands.Invoker;
import model.shapes.AdapterLineSegment;
import model.shapes.AdapterShape;
import model.shapes.JavaFXShapesFactory;

/**
 *
 * @author pelus
 */
public class PasteTool implements Tool {

    private Invoker invoker;

    public PasteTool(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void useTool(MouseEvent event) {
        ShapeClipboard clipboard = ShapeClipboard.getInstance();
        JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
        AdapterShape shape = clipboard.getShape();
        AdapterShape newShape;
        if (shape.toString().compareTo("ellipse") == 0) {
            newShape = factory.createEllipse();
        } else if (shape.toString().compareTo("rectangle") == 0) {
            newShape = factory.createRectangle();
        } else { //line segment needs orientation
            newShape = factory.createLineSegment();
            Line adaptee = ((AdapterLineSegment) shape).getAdaptee();
            newShape.setStart(adaptee.getStartX(), adaptee.getStartY());
            newShape.setEnd(adaptee.getEndX(), adaptee.getEndY());
        }
        newShape.setHeight(shape.getHeight());
        newShape.setWidth(shape.getWidth());
        newShape.setFillColor(shape.getFillColor());
        newShape.setStrokeColor(shape.getStrokeColor());
        newShape.setCenter(event.getX(), event.getY());

        Pane pane = (Pane) event.getSource();
        Command command = new AddShapeCommand(pane, newShape);
        invoker.executeCommand(command);
    }

}
