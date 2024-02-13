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
public class ChangeStrokeColorCommand implements Command {

    private AdapterShape shape;
    private Color newColor;
    private Color oldColor;

    public ChangeStrokeColorCommand(AdapterShape shape, Color color) {
        this.shape = shape;
        this.newColor = color;
        this.oldColor = shape.getStrokeColor();
    }

    @Override
    public void execute() {
        shape.setStrokeColor(newColor);

    }

    @Override
    public void undo() {
        shape.setStrokeColor(oldColor);
    }

}
