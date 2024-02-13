/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import javafx.scene.layout.Pane;
import model.shapes.AdapterShape;

/**
 *
 * @author marku
 */
public class BringToFrontCommand implements Command {

    private AdapterShape shape;
    private Pane drawingSurface;
    private int index;

    public BringToFrontCommand(Pane drawingSurface, AdapterShape shape) {
        this.shape = shape;
        this.drawingSurface = drawingSurface;
    }

    @Override
    public void execute() {
        this.index = drawingSurface.getChildren().indexOf(this.shape.getAdaptee());//the index will be stored when command is executed, not at command creation
        shape.bringToFront(); 
    }

    @Override
    public void undo() {
        drawingSurface.getChildren().remove(shape.getAdaptee());
        drawingSurface.getChildren().add(index, shape.getAdaptee());
    }

}
