/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.shapes;

import javafx.scene.shape.Shape;

/**
 *
 * @author Marco
 */
public interface AbstractShapesFactory {

    public AdapterShape createRectangle();

    public AdapterShape createEllipse();

    public AdapterShape createLineSegment();

    public AdapterShape createAdapter(Shape shape);
}
