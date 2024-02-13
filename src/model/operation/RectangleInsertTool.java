/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.operation;

import model.shapes.JavaFXShapesFactory;
import model.shapes.AdapterShape;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.commands.AddShapeCommand;
import model.commands.Command;
import model.commands.Invoker;

/**
 *
 * @author Valerio
 */
public class RectangleInsertTool implements ResettableTool, InsertionTool {

    private SimpleObjectProperty<Color> fillProperty;
    private SimpleObjectProperty<Color> strokeProperty;

    private double upperLeftX, upperLeftY;
    private boolean start;
    private Invoker invoker;
    private AdapterShape preview;

    public RectangleInsertTool(Invoker invoker) {
        this.fillProperty = new SimpleObjectProperty<Color>();
        this.strokeProperty = new SimpleObjectProperty<Color>();
        this.invoker = invoker;
        this.start = true;
        JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
        //this adapter shape is used to preview the shape before it will be placed on the canvas
        preview = factory.createRectangle();
        preview.setFillColor(Color.TRANSPARENT);
        preview.setStrokeColor(Color.BLUEVIOLET);

    }

    @Override
    public SimpleObjectProperty<Color> getFillProperty() {
        return this.fillProperty;
    }

    @Override
    public SimpleObjectProperty<Color> getStrokeProperty() {
        return this.strokeProperty;
    }

    @Override
    public void useTool(MouseEvent event) {
        if (start) {
            /*
            First click on the canvas, the position will 
            be stored and the preview will be shown.
            */
            upperLeftX = event.getX();
            upperLeftY = event.getY();
            start = false;

            Pane a = (Pane) event.getSource();
            a.getChildren().add(preview.getAdaptee());

        } else {
            /*
            Second click on the canvas, the preview will be cleared 
            and the final shape will be added to the canvas through
            a command.
            Colors are taken through bound properties. After this
            click the shape geometry is computed in the "setEnd" 
            method.
            */
            double newX = event.getX();
            double newY = event.getY();

            JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
            AdapterShape rectangle = factory.createRectangle();

            rectangle.setStart(upperLeftX, upperLeftY);
            rectangle.setEnd(newX, newY);

            rectangle.setFillColor(this.fillProperty.getValue());
            rectangle.setStrokeColor(this.strokeProperty.getValue());

            Pane a = (Pane) event.getSource();
            Command command = new AddShapeCommand(a, rectangle);
            invoker.executeCommand(command);
            resetTool();
        }

    }

    @Override
    public void previewShape(MouseEvent event) {
        // used to update the preview when the mouse moves
        double newX = event.getX();
        double newY = event.getY();

        preview.setStart(upperLeftX, upperLeftY);
        preview.setEnd(newX, newY);

    }

    @Override
    public boolean isStarted() {
        return !start;
    }

    @Override
    public void resetTool() {
        this.start = true;
        clearPreview();
    }

    private void clearPreview() {
        preview.setHeight(0);
        preview.setWidth(0);
        preview.setCenter(0, 0);
        Pane a = (Pane) preview.getAdaptee().getParent();
        if (a != null) {
            a.getChildren().remove(preview.getAdaptee());
        }
    }

}
