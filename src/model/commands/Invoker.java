/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.commands;

import java.util.Stack;

/**
 *
 * @author Marco Calabrese
 */
public class Invoker {

    private Stack<Command> history;

    public Invoker() {
        history = new Stack<>();
    }

    public Stack<Command> getHistory() {
        return history;
    }

    public void executeCommand(Command command) {
        this.history.push(command);
        command.execute();
    }

    public void undoCommand() {
        if (!this.history.isEmpty()) {
            Command lastCommand = this.history.pop();
            lastCommand.undo();
        }
    }
}
