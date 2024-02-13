/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import model.shapes.AdapterShape;
import javafx.scene.paint.Color;

/**
 *
 * @author pelus
 */
public class ChangeFillColorCommand implements Command {

    private AdapterShape shape;
    private Color newColor;
    private Color oldColor;

    public ChangeFillColorCommand(AdapterShape shape, Color color) {
        this.shape = shape;
        this.newColor = color;
        this.oldColor = shape.getFillColor();
    }

    @Override
    public void execute() {
        shape.setFillColor(newColor);
    }

    @Override
    public void undo() {
        shape.setFillColor(oldColor);
    }

}
