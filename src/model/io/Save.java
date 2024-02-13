/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.io;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Marco Calabrese
 */
public class Save {

    public static void saveDrawing(Pane pane) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save drawing");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("XML file", "*.xml"));
        File file = fileChooser.showSaveDialog(pane.getScene().getWindow());

        try ( XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.setPersistenceDelegate(Color.class, new DefaultPersistenceDelegate(
                    new String[]{"red", "green", "blue", "opacity"}
            ));

            encoder.writeObject(pane.getChildren().toArray(new Node[0]));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

}
