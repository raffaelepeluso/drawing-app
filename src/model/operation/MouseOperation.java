/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.operation;

import javafx.scene.input.MouseEvent;

/**
 *
 * @author Valerio
 */
public class MouseOperation {

    private Tool selectedTool;

    public MouseOperation(Tool initialTool) {
        this.selectedTool = initialTool;
    }

    public void setTool(Tool selectedTool) {
        if (this.selectedTool instanceof ResettableTool) {
            ((ResettableTool) this.selectedTool).resetTool();
        }
        this.selectedTool = selectedTool;
    }

    public void useTool(MouseEvent event) {
        selectedTool.useTool(event);
    }

    public void previewShape(MouseEvent event) {
        /*
        If the tool is an insertion tool and the insertion process is
        already started (first click has happened), the preview will 
        be updated.
        */
        if (this.selectedTool instanceof InsertionTool) {
            if (((InsertionTool) this.selectedTool).isStarted()) {
                ((InsertionTool) this.selectedTool).previewShape(event);
            }
        }
    }
}
