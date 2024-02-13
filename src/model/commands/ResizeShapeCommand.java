/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import model.shapes.AdapterShape;

/**
 *
 * @author Marco Calabrese
 */
public class ResizeShapeCommand implements Command {

    private AdapterShape shape;
    private double oldX, oldY, newX, newY;

    public ResizeShapeCommand(AdapterShape shape, double newX, double newY) {
        this.shape = shape;
        this.oldX = shape.getWidth();
        this.oldY = shape.getHeight();
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
        //change only width
        if (oldX != newX && oldY == newY) {
            newY = (oldY * newX) / oldX;
            shape.setWidth(newX);
            shape.setHeight(newY);
        }
        //change only height
        if (oldX == newX && oldY != newY) {
            newX = (oldX * newY) / oldY;
            shape.setHeight(newY);
            shape.setWidth(newX);
        }
    }

    @Override
    public void undo() {
        shape.setWidth(oldX);
        shape.setHeight(oldY);
    }

}
