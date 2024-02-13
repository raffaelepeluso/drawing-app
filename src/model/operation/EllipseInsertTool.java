/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author pelus
 */
public class EllipseInsertTool implements ResettableTool, InsertionTool {

    private SimpleObjectProperty<Color> fillProperty;
    private SimpleObjectProperty<Color> strokeProperty;

    private boolean start;
    private Invoker invoker;
    private double startX, startY;
    private AdapterShape preview;

    public EllipseInsertTool(Invoker invoker) {
        this.fillProperty = new SimpleObjectProperty<>();
        this.strokeProperty = new SimpleObjectProperty<>();
        this.invoker = invoker;
        this.start = true;
        JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
        //this adapter shape is used to preview the shape before it will be placed on the canvas
        preview = factory.createEllipse();
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
            startX = event.getX();
            startY = event.getY();
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
            double endX = event.getX();
            double endY = event.getY();

            JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
            AdapterShape ellipse = factory.createEllipse();

            ellipse.setStart(startX, startY);
            ellipse.setEnd(endX, endY);

            ellipse.setFillColor(this.fillProperty.getValue());
            ellipse.setStrokeColor(this.strokeProperty.getValue());

            Pane a = (Pane) event.getSource();
            Command command = new AddShapeCommand(a, ellipse);
            invoker.executeCommand(command);
            resetTool();
        }

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

    @Override
    public void previewShape(MouseEvent event) {
        // used to update the preview when the mouse moves
        double endX = event.getX();
        double endY = event.getY();

        preview.setStart(startX, startY);
        preview.setEnd(endX, endY);

    }
}
