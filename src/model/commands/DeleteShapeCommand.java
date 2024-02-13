/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import model.shapes.AdapterShape;
import javafx.scene.layout.Pane;

/**
 *
 * @author pelus
 */
public class DeleteShapeCommand implements Command {

    private Pane drawingSurface;
    private AdapterShape shape;
    private int index; //specific position of the shape on the drawingSurface

    public DeleteShapeCommand(Pane pane, AdapterShape shape) {
        this.drawingSurface = pane;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.index = drawingSurface.getChildren().indexOf(shape.getAdaptee());
        drawingSurface.getChildren().remove(shape.getAdaptee());
    }

    @Override
    public void undo() {
        drawingSurface.getChildren().add(index, shape.getAdaptee());
    }

}
