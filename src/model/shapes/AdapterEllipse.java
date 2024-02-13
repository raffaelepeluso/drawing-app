/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.shapes;

import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author pelus
 */
public class AdapterEllipse implements AdapterShape {

    private Ellipse adaptee;
    private double startX, startY, startDragX, startDragY;

    public AdapterEllipse() {
        adaptee = new Ellipse();
        adaptee.setFill(Color.WHITE);
        adaptee.setStroke(Color.BLACK);
    }

    public AdapterEllipse(Ellipse shape) {
        this.adaptee = shape;
    }

    @Override
    public void setCenter(double x, double y) {
        adaptee.setCenterX(x);
        adaptee.setCenterY(y);
    }

    @Override
    public Ellipse getAdaptee() {
        return adaptee;
    }

    @Override
    public void setFillColor(Color color) {
        adaptee.setFill(color);
    }

    @Override
    public void setStrokeColor(Color color) {
        adaptee.setStroke(color);
    }

    @Override
    public Color getStrokeColor() {
        return (Color) adaptee.getStroke();
    }

    @Override
    public Color getFillColor() {
        return (Color) adaptee.getFill();
    }

    @Override
    public void setStart(double x, double y) {
        startX = x;
        startY = y;
    }

    @Override
    public void setEnd(double x, double y) {
        /*
        When the second click is caught, the dimensions of the ellipse
        are computed and the ellipse is centered in the middle point between 
        the two clicks.
        */
        double radiusX = abs(startX - x) / 2;
        double radiusY = abs(startY - y) / 2;
        setWidth(radiusX);
        setHeight(radiusY);

        setCenter((startX + x) / 2, (startY + y) / 2);
    }

    @Override
    public double getCenterX() {
        return this.adaptee.getCenterX();
    }

    @Override
    public double getCenterY() {
        return this.adaptee.getCenterY();
    }

    @Override
    public double getWidth() {
        return this.adaptee.getRadiusX();
    }

    @Override
    public double getHeight() {
        return this.adaptee.getRadiusY();
    }

    @Override
    public void setWidth(double width) {
        this.adaptee.setRadiusX(width);
    }

    @Override
    public void setHeight(double height) {
        this.adaptee.setRadiusY(height);
    }

    @Override
    public void setStrokeType(StrokeType strokeType) {
        this.adaptee.setStrokeType(strokeType);
    }

    @Override
    public void setStrokeWidth(double width) {
        this.adaptee.setStrokeWidth(width);
    }

    @Override
    public void makeDraggable() {
        this.adaptee.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startDragX = event.getSceneX() - adaptee.getTranslateX();
                startDragY = event.getSceneY() - adaptee.getTranslateY();
            }
        });

        this.adaptee.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                adaptee.setTranslateX(event.getSceneX() - startDragX);
                adaptee.setTranslateY(event.getSceneY() - startDragY);
            }
        });
    }

    @Override
    public void makeUndraggable() {
        this.adaptee.setOnMousePressed(null);
        this.adaptee.setOnMouseDragged(null);
    }

    @Override
    public double getTranslateX() {
        return this.adaptee.getTranslateX();
    }

    @Override
    public double getTranslateY() {
        return this.adaptee.getTranslateY();
    }

    @Override
    public void setTranslateX(double translateX) {
        this.adaptee.setTranslateX(translateX);
    }

    @Override
    public void setTranslateY(double translateY) {
        this.adaptee.setTranslateY(translateY);
    }

    @Override
    public String toString() {
        return "ellipse";
    }

    @Override
    public void bringToFront() {
        adaptee.toFront();
    }

    @Override
    public void sendToBack() {
        adaptee.toBack();
    }

}
