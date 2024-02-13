/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Marco
 */
public interface AdapterShape {

    public Shape getAdaptee();

    public void setStrokeColor(Color color);

    public void setFillColor(Color color);

    public Color getStrokeColor();

    public Color getFillColor();

    public void setCenter(double x, double y);

    public double getCenterX();

    public double getCenterY();

    public double getWidth();

    public double getHeight();

    public void setWidth(double width);

    public void setHeight(double height);

    public void setStart(double x, double y);

    public void setEnd(double x, double y);

    public void setStrokeType(StrokeType strokeType);

    public void setStrokeWidth(double width);

    public void makeDraggable();

    public void makeUndraggable();
    
    public double getTranslateX();
    
    public double getTranslateY();
    
    public void setTranslateX(double translateX);
    
    public void setTranslateY(double translateY);

    public void bringToFront();

    public void sendToBack();
}
