/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import model.shapes.AdapterShape;

/**
 *
 * @author Marco
 */
public class MoveShapeCommand implements Command {

    private AdapterShape shape;
    private double newX, newY;
    private double oldX, oldY;

    public MoveShapeCommand(AdapterShape shape, double newX, double newY) {
        this.shape = shape;
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
        oldX = shape.getCenterX();
        oldY = shape.getCenterY();
        shape.setCenter(newX, newY);
    }

    @Override
    public void undo() {
        shape.setCenter(oldX, oldY);
    }

}
