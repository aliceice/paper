package de.aliceice.paper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        
        subject.printErr(new Exception(), "An error occurred: %s", "Error");
        
        assertTrue(err.toString()
                      .contains(String.format("An error occurred: Error" +
                                              "%n" +
                                              "%n" +
                                              "java.lang.Exception%n" +
                                              "\tat de.aliceice.paper.IOStreamConsoleTest")));
    }
    
    @Test
    public void readLine() throws Exception {
        Console subject = new IOStreamConsole(new ByteArrayInputStream("Input".getBytes()));
        assertEquals("Input", subject.readLine());
    }
    
    @Test
    public void readSecret() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOStreamConsole subject = new IOStreamConsole(new ByteArrayInputStream("Secret".getBytes()), out);
        assertEquals("Secret", subject.readSecret());
        assertEquals(String.format("WARNING: I/O Stream Console can't hide secrets. All input will be visible.%n"),
                     out.toString());
    }
}