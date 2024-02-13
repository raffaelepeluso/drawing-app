/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.shapes;

import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Marco
 */
public class AdapterRectangle implements AdapterShape {

    private Rectangle adaptee;
    private double centerX, centerY, startDragX, startDragY;

    public AdapterRectangle() {
        adaptee = new Rectangle();
        adaptee.setFill(Color.WHITE);
        adaptee.setStroke(Color.BLACK);
    }

    public AdapterRectangle(Rectangle shape) {
        this.adaptee = shape;
        this.centerX = adaptee.getX() + adaptee.getWidth() / 2;
        this.centerY = adaptee.getY() + adaptee.getHeight() / 2;
    }

    @Override
    public void setCenter(double x, double y) {
        this.centerX = x;
        this.centerY = y;
        adaptee.setX(x - adaptee.getWidth() / 2);
        adaptee.setY(y - adaptee.getHeight() / 2);
    }

    @Override
    public Rectangle getAdaptee() {
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
        adaptee.setX(x);
        adaptee.setY(y);
    }

    @Override
    public void setEnd(double x, double y) {
        /*
        Computation of the upper left point of the rectangle after the second click.
        */
        double upperLeftX = adaptee.getX();
        double upperLeftY = adaptee.getY();
        double sizeX = abs(upperLeftX - x);
        double sizeY = abs(upperLeftY - y);
        adaptee.setWidth(sizeX);
        adaptee.setHeight(sizeY);

        if (upperLeftX <= x) {// if the second click is on the right of the first
            if (upperLeftY <= y) {// if the second click is on the bottom of the first
                return;
            } else if (upperLeftY > y) {// if the second click is on the top of the first
                this.setStart(upperLeftX, y);
            }
        } else if (upperLeftX > x) {// if the second click is on the left of the first
            if (upperLeftY > y) {// if the second click is on the top of the first
                this.setStart(x, y);
            } else if (upperLeftY <= y) {// if the second click is on the bottom of the first
                this.setStart(x, upperLeftY);
            }
        }
    }

    @Override
    public double getCenterX() {
        return this.centerX;
    }

    @Override
    public double getCenterY() {
        return this.centerY;
    }

    @Override
    public double getWidth() {
        return this.adaptee.getWidth();
    }

    @Override
    public double getHeight() {
        return this.adaptee.getHeight();
    }

    @Override
    public void setWidth(double width) {
        this.adaptee.setWidth(width);
    }

    @Override
    public void setHeight(double height) {
        this.adaptee.setHeight(height);
    }

    @Override
    public void makeDraggable() {
        this.adaptee.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startDragX = event.getSceneX();
                startDragY = event.getSceneY();
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
    public void setStrokeType(StrokeType strokeType) {
        this.adaptee.setStrokeType(strokeType);
    }

    @Override
    public void setStrokeWidth(double width) {
        this.adaptee.setStrokeWidth(width);
    }

    @Override
    public String toString() {
        return "rectangle";
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
