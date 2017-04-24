package de.aliceice.paper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class IOStreamConsoleTest {
    
    @Test
    public void printToOut() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Console subject = new IOStreamConsole(out);
        
        subject.println("Hello there!");
        subject.println();
        subject.printf("How are %s?%n", "you");
        
        assertEquals(String.format("Hello there!%n" +
                                   "%n" +
                                   "How are you?%n"),
                     out.toString());
    }
    
    @Test
    public void printErr() throws Exception {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        Console subject = new IOStreamConsole(new ByteArrayOutputStream(), err);
        
        subject.printErr("An error occurred: %s", "Error");
        
        assertEquals("An error occurred: Error", err.toString());
    }
    
    @Test
    public void readLine() throws Exception {
        Console subject = new IOStreamConsole(new ByteArrayInputStream("Input".getBytes()));
        assertEquals("Input", subject.readLine());
    }
    
}