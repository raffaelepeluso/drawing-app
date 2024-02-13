/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.shapes;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Marco
 */
public class JavaFXShapesFactory implements AbstractShapesFactory {

    private static JavaFXShapesFactory factory;

    private JavaFXShapesFactory() {
    }

    public static JavaFXShapesFactory getFactory() {
        if (JavaFXShapesFactory.factory == null) {
            return new JavaFXShapesFactory();
        } else {
            return JavaFXShapesFactory.factory;
        }
    }

    @Override
    public AdapterShape createRectangle() {
        return new AdapterRectangle();
    }

    @Override
    public AdapterShape createEllipse() {
        return new AdapterEllipse();
    }

    @Override
    public AdapterShape createLineSegment() {
        return new AdapterLineSegment();
    }

    @Override
    public AdapterShape createAdapter(Shape shape) {
        if (shape instanceof Rectangle) {
            return new AdapterRectangle((Rectangle) shape);
        }
        if (shape instanceof Ellipse) {
            return new AdapterEllipse((Ellipse) shape);
        }
        if (shape instanceof Line) {
            return new AdapterLineSegment((Line) shape);
        }
        return null;
    }
}
