/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.shapes;

import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Marco
 */
public class AdapterLineSegment implements AdapterShape {

    private Line adaptee;
    private double centerX, centerY, startX, startY;

    ;

    public AdapterLineSegment() {
        adaptee = new Line();
        adaptee.setFill(Color.WHITE);
        adaptee.setStroke(Color.BLACK);
    }

    public AdapterLineSegment(Line shape) {
        this.adaptee = shape;
        this.centerX = (adaptee.getStartX() + adaptee.getEndX()) / 2;
        this.centerY = (adaptee.getStartY() + adaptee.getEndY()) / 2;
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
        adaptee.setStartX(x);
        adaptee.setStartY(y);
    }

    @Override
    public void setEnd(double x, double y) {
        adaptee.setEndX(x);
        adaptee.setEndY(y);

        centerX = (adaptee.getStartX() + adaptee.getEndX()) / 2;
        centerY = (adaptee.getStartY() + adaptee.getEndY()) / 2;

    }

    @Override
    public Line getAdaptee() {
        return adaptee;
    }

    @Override
    public void setCenter(double x, double y) {
        double offsetX = (x - centerX);
        double offsetY = (y - centerY);

        this.setStart(adaptee.getStartX() + offsetX, adaptee.getStartY() + offsetY);
        this.setEnd(adaptee.getEndX() + offsetX, adaptee.getEndY() + offsetY);

        centerX = x;
        centerY = y;
    }

    @Override
    public double getCenterX() {
        return centerX;
    }

    @Override
    public double getCenterY() {
        return centerY;
    }

    @Override
    public double getWidth() {
        return abs(this.adaptee.getStartX() - this.adaptee.getEndX());
    }

    @Override
    public double getHeight() {
        return abs(this.adaptee.getStartY() - this.adaptee.getEndY());
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
                startX = event.getSceneX() - adaptee.getTranslateX();
                startY = event.getSceneY() - adaptee.getTranslateY();
            }
        });

        this.adaptee.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                adaptee.setTranslateX(event.getSceneX() - startX);
                adaptee.setTranslateY(event.getSceneY() - startY);
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
        return "line";
    }

    @Override
    public void bringToFront() {
        adaptee.toFront();
    }

    @Override
    public void sendToBack() {
        adaptee.toBack();
    }

    @Override
    public void setWidth(double width) {

        double delta = abs(width - getWidth()) / 2;
        if ((getWidth() - width) > 0) {// new size is smaller
            if (adaptee.getStartX() < adaptee.getEndX()) {// start is on the left
                adaptee.setStartX(adaptee.getStartX() + delta);
                adaptee.setEndX(adaptee.getEndX() - delta);
            } else {// start is on the right
                adaptee.setStartX(adaptee.getStartX() - delta);
                adaptee.setEndX(adaptee.getEndX() + delta);
            }
        } else if ((getWidth() - width) < 0) {// new size is bigger
            if (adaptee.getStartX() < adaptee.getEndX()) {// start is on the left
                adaptee.setStartX(adaptee.getStartX() - delta);
                adaptee.setEndX(adaptee.getEndX() + delta);
            } else {// start is on the right
                adaptee.setStartX(adaptee.getStartX() + delta);
                adaptee.setEndX(adaptee.getEndX() - delta);
            }
        }
        centerX = (adaptee.getStartX() + adaptee.getEndX()) / 2;

    }

    @Override
    public void setHeight(double height) {
        double delta = abs(height - this.getHeight()) / 2;

        if ((getHeight() - height) > 0) {// new size is smaller
            if (adaptee.getStartY() < adaptee.getEndY()) {// start is on the top
                adaptee.setStartY(adaptee.getStartY() + delta);
                adaptee.setEndY(adaptee.getEndY() - delta);
            } else {// start is on the bottom
                adaptee.setStartY(adaptee.getStartY() - delta);
                adaptee.setEndY(adaptee.getEndY() + delta);
            }
        } else if ((getHeight() - height) < 0) {// new size is bigger
            if (adaptee.getStartY() < adaptee.getEndY()) {// start is on the top
                adaptee.setStartY(adaptee.getStartY() - delta);
                adaptee.setEndY(adaptee.getEndY() + delta);
            } else {// start is on the bottom
                adaptee.setStartY(adaptee.getStartY() + delta);
                adaptee.setEndY(adaptee.getEndY() - delta);
            }
        }
        centerY = (adaptee.getStartY() + adaptee.getEndY()) / 2;

    }

}
