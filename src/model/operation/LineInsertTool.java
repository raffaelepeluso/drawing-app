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
 * @author Marco
 */
public class LineInsertTool implements ResettableTool, InsertionTool {

    private SimpleObjectProperty<Color> fillProperty;
    private SimpleObjectProperty<Color> strokeProperty;

    private double startX, startY;
    private double endX, endY;
    private boolean start;
    private Invoker invoker;
    private AdapterShape preview;

    public LineInsertTool(Invoker invoker) {
        this.fillProperty = new SimpleObjectProperty<Color>();
        this.strokeProperty = new SimpleObjectProperty<Color>();
        this.start = true;
        this.invoker = invoker;
        JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
        //this adapter shape is used to preview the shape before it will be placed on the canvas
        preview = factory.createLineSegment();
        preview.setFillColor(Color.TRANSPARENT);
        preview.setStrokeColor(Color.BLUEVIOLET);
    }

    @Override
    public SimpleObjectProperty<Color> getStrokeProperty() {
        return strokeProperty;
    }

    @Override
    public SimpleObjectProperty<Color> getFillProperty() {
        return fillProperty;
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
            endX = event.getX();
            endY = event.getY();

            JavaFXShapesFactory factory = JavaFXShapesFactory.getFactory();
            AdapterShape line = factory.createLineSegment();

            line.setStart(startX, startY);
            line.setEnd(endX, endY);

            line.setStrokeColor(this.strokeProperty.getValue());

            Pane a = (Pane) event.getSource();
            Command command = new AddShapeCommand(a, line);
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
        endX = event.getX();
        endY = event.getY();
        preview.setStart(startX, startY);
        preview.setEnd(endX, endY);
    }

}
