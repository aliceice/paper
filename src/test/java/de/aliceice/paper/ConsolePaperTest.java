package de.aliceice.paper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class ConsolePaperTest {
    
    @Test
    public void printsOutputAndReadsFromInput() throws Exception {
        this.console.useAsInput("Value");
        
        this.form.printOn(subject);
        this.subject.askForInput();
        this.subject.copyTo(this.form);
        
        this.console.hasOutput("Test Form%n" +
                               "Test Description%n%n" +
                               "Test Field: Value%n");
        
        assertTrue(this.form.isValid());
        this.form.onSubmit(fields -> assertEquals("Value", fields.get("Test Field")));
        this.form.submit();
    }
    
    @Test
    public void printsFieldDescriptionWhenFieldIsMarked() throws Exception {
        this.console.useAsInput("");
        
        this.form.printOn(this.subject);
        this.form.markErrorsOn(this.subject);
     
        this.console.hasOutput("Test Form%n" +
                               "Test Description%n" +
                               "%n" +
                               "There are errors on the form:%n" +
                               "  - Test Field: Mandatory, Max length 5%n" +
                               "%n");
    }
    
    @Test
    public void defaultConstructorUsesSystemInOut() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        InputStream oldIn = System.in;
        System.setIn(new ByteArrayInputStream(String.format("Value%n").getBytes()));
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));
        
        ConsolePaper subject = new ConsolePaper();
        this.form.printOn(subject);
        subject.askForInput();
        
        assertEquals(String.format("Test Form%n" +
                                   "Test Description%n" +
                                   "%n" +
                                   "Test Field: "),
                     out.toString());
        
        System.setIn(oldIn);
        System.setOut(oldOut);
    }
    
    @Test
    public void write() throws Exception {
        this.console.useAsInput("");
        this.form.write("Test Field", "Pre-Filled Value");
        this.form.printOn(subject);
        
        this.subject.askForInput();
        
        this.console.hasOutput("Test Form%n" +
                               "Test Description%n" +
                               "%n" +
                               "Test Field (Currently 'Pre-Filled Value'): %n");
        
        this.form.onSubmit(fields -> assertEquals("Pre-Filled Value", fields.get("Test Field")));
        this.form.submit();
    }
    
    private final Form         form    = new TestForm();
    private final TestConsole  console = new TestConsole();
    private final ConsolePaper subject = new ConsolePaper(this.console);
}