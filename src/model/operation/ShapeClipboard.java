/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.operation;

import model.shapes.AdapterShape;

/**
 *
 * @author pelus
 */
public class ShapeClipboard {

    private static ShapeClipboard instance;
    private AdapterShape shape;

    private ShapeClipboard() {
    }

    public static ShapeClipboard getInstance() {
        if (instance == null) {
            instance = new ShapeClipboard();
        }
        return instance;
    }

    public void addShape(AdapterShape shape) {
        this.shape = shape;
    }

    public AdapterShape getShape() {
        return shape;
    }

}
