/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.io;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Marco Calabrese
 */
public class Load {

    public static void loadDrawing(Pane pane) {
        FileChooser fileChoose = new FileChooser();
        fileChoose.setTitle("Load File");
        fileChoose.getExtensionFilters().add(new ExtensionFilter("XML file", "*.xml"));

        File file = fileChoose.showOpenDialog(pane.getScene().getWindow());
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
            pane.getChildren().setAll((Node[]) decoder.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
