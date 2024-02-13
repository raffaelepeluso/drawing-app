/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.operation;

import model.shapes.AdapterShape;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import model.shapes.JavaFXShapesFactory;

/**
 *
 * @author Valerio
 */
public class SelectionTool implements ResettableTool {

    private AdapterShape selectedShape;
    private SimpleBooleanProperty isSelected;

    public SelectionTool() {
        selectedShape = null;
        isSelected = new SimpleBooleanProperty(false);
    }

    @Override
    public void useTool(MouseEvent event) {
        //select the target shape, if target is not a shape it means deselection
        if (event.getTarget() instanceof Shape) {
            AdapterShape shape = JavaFXShapesFactory.getFactory().createAdapter((Shape) event.getTarget());
            if (!this.isSelected()) {
                //setting visuals on the selected shape
                selectedShape = shape;
                selectedShape.setStrokeType(StrokeType.OUTSIDE);
                selectedShape.setStrokeWidth(4.0);
                selectedShape.makeDraggable();
                this.isSelected.setValue(true);
            } else {
                //a shape is already selected, so the visuals must be resetted to default
                this.selectedShape.setStrokeType(StrokeType.CENTERED);
                this.selectedShape.setStrokeWidth(1.0);
                this.selectedShape.makeUndraggable();
                selectedShape = shape;
                this.selectedShape.setStrokeType(StrokeType.OUTSIDE);
                this.selectedShape.setStrokeWidth(4.0);
                this.selectedShape.makeDraggable();
            }
        } else {
            this.resetTool();
        }
    }

    @Override
    public void resetTool() {
        //reset the tool, if a shape is selected visuals must be resetted to default
        if (this.isSelected.getValue()) {
            this.selectedShape.setStrokeType(StrokeType.CENTERED);
            this.selectedShape.setStrokeWidth(1.0);
            this.selectedShape.makeUndraggable();
        }
        this.isSelected.setValue(false);
        this.selectedShape = null;
    }

    public AdapterShape getSelectedShape() {
        //return the selected shape, null if nothing is selected
        if (this.isSelected.getValue()) {
            return selectedShape;
        } else {
            return null;
        }

    }

    public boolean isSelected() {
        return this.isSelected.getValue();
    }

    public SimpleBooleanProperty isSelectedProperty() {
        return this.isSelected;
    }
}
