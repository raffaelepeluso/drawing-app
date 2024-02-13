/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.operation;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Marco
 */
public interface InsertionTool extends Tool {

    public void previewShape(MouseEvent event);

    public boolean isStarted();

    public SimpleObjectProperty<Color> getFillProperty();

    public SimpleObjectProperty<Color> getStrokeProperty();

}
