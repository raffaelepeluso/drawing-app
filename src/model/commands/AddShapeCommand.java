/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import model.shapes.AdapterShape;
import javafx.scene.layout.Pane;

/**
 *
 * @author Marco Calabrese
 */
public class AddShapeCommand implements Command {

    private AdapterShape shape;
    private Pane drawingSurface;

    public AddShapeCommand(Pane pane, AdapterShape shape) {
        this.drawingSurface = pane;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.drawingSurface.getChildren().add(this.shape.getAdaptee());
    }

    @Override
    public void undo() {
        this.drawingSurface.getChildren().remove(this.shape.getAdaptee());
    }

}
