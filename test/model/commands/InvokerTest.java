/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.commands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Calabrese
 */
public class InvokerTest {
    
    static Invoker instance; 
            
    public InvokerTest() {
    }
    
    class CommandStub implements Command{
        private String result;
        
        public CommandStub() {
        }

        public String getResult() {
            return result;
        }

        @Override
        public void execute() {
            result = "execute";
        }

        @Override
        public void undo() {
            result = "undo";
        }
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new Invoker();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of executeCommand method, of class Invoker.
     */
    @Test
    public void testExecuteCommand() {
        Command command = new CommandStub();
        instance.executeCommand(command);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(command,instance.getHistory().peek());
    }

    /**
     * Test of undoCommand method, of class Invoker.
     */
    @Test
    public void testUndoCommand() {
        //Test of undo method of an invoker with an empty history 
        Invoker invoker = new Invoker();
        invoker.undoCommand();
        assertTrue(invoker.getHistory().isEmpty());
        //Test of undo method of an invoker with an history not empty
        instance.undoCommand();
        assertTrue(instance.getHistory().isEmpty());

        
    }
    
}
