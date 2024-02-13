/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package drawingapp;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.commands.BringToFrontCommand;

import model.commands.ChangeFillColorCommand;
import model.commands.ChangeStrokeColorCommand;
import model.commands.Command;
import model.commands.DeleteShapeCommand;
import model.operation.MouseOperation;
import model.operation.RectangleInsertTool;
import model.operation.EllipseInsertTool;
import model.operation.LineInsertTool;
import model.io.Load;
import model.io.Save;
import model.commands.Invoker;
import model.commands.MoveShapeCommand;
import model.commands.ResizeShapeCommand;
import model.commands.SendToBackCommand;
import model.operation.InsertionTool;
import model.operation.ShapeClipboard;
import model.operation.PasteTool;
import model.operation.SelectionTool;
import model.operation.Tool;
import model.shapes.AdapterShape;

/**
 *
 * @author pelus
 */
public class FXMLViewController implements Initializable {

    private Invoker invoker = new Invoker();
    private InsertionTool rectangleInsertTool = new RectangleInsertTool(invoker);
    private InsertionTool ellipseInsertTool = new EllipseInsertTool(invoker);
    private InsertionTool lineInsertTool = new LineInsertTool(invoker);
    private SelectionTool selectionTool = new SelectionTool();
    private Tool pasteTool = new PasteTool(invoker);
    private MouseOperation operation = new MouseOperation(selectionTool);

    @FXML
    private Pane drawingSurface;
    @FXML
    private ColorPicker outColor;
    @FXML
    private ColorPicker fillColor;
    @FXML
    private HBox commandButtons;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField yField;
    @FXML
    private TextField xField;
    @FXML
    private GridPane operationButtons;
    @FXML
    private Button pasteButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rectangleInsertTool.getFillProperty().bind(fillColor.valueProperty());
        rectangleInsertTool.getStrokeProperty().bind(outColor.valueProperty());
        ellipseInsertTool.getFillProperty().bind(fillColor.valueProperty());
        ellipseInsertTool.getStrokeProperty().bind(outColor.valueProperty());
        lineInsertTool.getStrokeProperty().bind(outColor.valueProperty());

        commandButtons.disableProperty().bind(selectionTool.isSelectedProperty().not());
        operationButtons.disableProperty().bind(selectionTool.isSelectedProperty().not());

        heightField.disableProperty().bind(selectionTool.isSelectedProperty().not());
        widthField.disableProperty().bind(selectionTool.isSelectedProperty().not());
        yField.disableProperty().bind(selectionTool.isSelectedProperty().not());
        xField.disableProperty().bind(selectionTool.isSelectedProperty().not());

        UnaryOperator<Change> format = (change) -> {
            return change.getControlNewText().matches("\\d*(\\.\\d*)?") ? change : null;
        };
        xField.setTextFormatter(new TextFormatter(format));
        yField.setTextFormatter(new TextFormatter(format));
        heightField.setTextFormatter(new TextFormatter(format));
        widthField.setTextFormatter(new TextFormatter(format));
    }

    private void clearTextFields() {
        heightField.clear();
        widthField.clear();
        yField.clear();
        xField.clear();
    }

    private void updateShapeProperties() {
        heightField.setText(String.valueOf(selectionTool.getSelectedShape().getHeight()));
        widthField.setText(Double.toString(selectionTool.getSelectedShape().getWidth()));
        yField.setText(Double.toString(selectionTool.getSelectedShape().getCenterY()));
        xField.setText(Double.toString(selectionTool.getSelectedShape().getCenterX()));
        outColor.setValue(selectionTool.getSelectedShape().getStrokeColor());
        fillColor.setValue(selectionTool.getSelectedShape().getFillColor());
    }

    @FXML
    private void handleClickDrawingSurface(MouseEvent event) {
        operation.useTool(event);
        if (selectionTool.isSelected()) {
            updateShapeProperties();
        } else {
            clearTextFields();
        }
    }

    @FXML
    private void handleRectangleAdd(ActionEvent event) {
        operation.setTool(rectangleInsertTool);
    }

    @FXML
    private void handleEllipseAdd(ActionEvent event) {
        operation.setTool(ellipseInsertTool);

    }

    @FXML
    private void handleLineAdd(ActionEvent event) {
        operation.setTool(lineInsertTool);
    }

    @FXML
    private void handleLoadOperation(ActionEvent event) {
        selectionTool.resetTool();
        clearTextFields();
        Load.loadDrawing(drawingSurface);
    }

    @FXML
    private void handleSaveOperation(ActionEvent event) {
        selectionTool.resetTool();
        clearTextFields();
        Save.saveDrawing(drawingSurface);
    }

    @FXML
    private void handleSelection(ActionEvent event) {
        operation.setTool(selectionTool);
    }

    @FXML
    private void handleUndoButton(ActionEvent event) {
        selectionTool.resetTool();
        clearTextFields();
        invoker.undoCommand();
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        Command command = new DeleteShapeCommand(drawingSurface, selectionTool.getSelectedShape());
        selectionTool.resetTool();
        clearTextFields();
        invoker.executeCommand(command);
    }

    @FXML
    private void handleChangeFillColor(ActionEvent event) {
        if (selectionTool.isSelected()) {
            Command command = new ChangeFillColorCommand(selectionTool.getSelectedShape(), fillColor.getValue());
            invoker.executeCommand(command);
        }
    }

    @FXML
    private void handleChangeOutColor(ActionEvent event) {
        if (selectionTool.isSelected()) {
            Command command = new ChangeStrokeColorCommand(selectionTool.getSelectedShape(), outColor.getValue());
            invoker.executeCommand(command);
        }
    }

    @FXML
    private void handleChangePosition(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String x = xField.getText();
            String y = yField.getText();
            if (!x.isEmpty() && !y.isEmpty()) {
                Command command = new MoveShapeCommand(selectionTool.getSelectedShape(), Double.parseDouble(x), Double.parseDouble(y));
                invoker.executeCommand(command);
            }
        }
    }

    @FXML
    private void handleChangeHeight(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String newHeight = heightField.getText();
            if (!newHeight.isEmpty()) {
                Command command = new ResizeShapeCommand(selectionTool.getSelectedShape(), selectionTool.getSelectedShape().getWidth(), Double.parseDouble(newHeight));
                invoker.executeCommand(command);
                widthField.setText(Double.toString(selectionTool.getSelectedShape().getWidth()));
            }
        }
    }

    @FXML
    private void handleChangeWidth(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String newWidth = widthField.getText();
            if (!newWidth.isEmpty()) {
                Command command = new ResizeShapeCommand(selectionTool.getSelectedShape(), Double.parseDouble(newWidth), selectionTool.getSelectedShape().getHeight());
                invoker.executeCommand(command);
                heightField.setText(Double.toString(selectionTool.getSelectedShape().getHeight()));
            }
        }
    }

    @FXML
    private void handleCopyButton(ActionEvent event) {
        ShapeClipboard clipboard = ShapeClipboard.getInstance();
        clipboard.addShape(selectionTool.getSelectedShape());
        pasteButton.setDisable(false);
    }

    @FXML
    private void handlePasteButton(ActionEvent event) {
        operation.setTool(pasteTool);
    }

    @FXML
    private void handleCutButton(ActionEvent event) {
        ShapeClipboard clipboard = ShapeClipboard.getInstance();
        clipboard.addShape(selectionTool.getSelectedShape());
        Command command = new DeleteShapeCommand(drawingSurface, selectionTool.getSelectedShape());
        invoker.executeCommand(command);
        pasteButton.setDisable(false);
    }

    @FXML
    private void handleMouseRelease(MouseEvent event) {
        if (selectionTool.isSelected() && event.getTarget() instanceof Shape) {
            AdapterShape adapterShape = selectionTool.getSelectedShape();
            double x, y;
            x = adapterShape.getCenterX() + adapterShape.getTranslateX();
            y = adapterShape.getCenterY() + adapterShape.getTranslateY();
            //execution of move command only if the shape is still inside the drawingSurface;
            if (x >= 0 && y >= 0) {
                invoker.executeCommand(new MoveShapeCommand(adapterShape, x, y));
            }
            adapterShape.setTranslateX(0);
            adapterShape.setTranslateY(0);
        }
    }

    @FXML
    private void handleBringToFront(ActionEvent event) {
        Command command = new BringToFrontCommand(drawingSurface, selectionTool.getSelectedShape());
        invoker.executeCommand(command);
    }

    @FXML
    private void handleSendToBack(ActionEvent event) {
        Command command = new SendToBackCommand(drawingSurface, selectionTool.getSelectedShape());
        invoker.executeCommand(command);
    }

    @FXML
    private void handleMouseMovedDrawingSurface(MouseEvent event) {
        operation.previewShape(event);
    }
}
