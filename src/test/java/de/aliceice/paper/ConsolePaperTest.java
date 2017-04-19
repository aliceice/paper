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
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsolePaper subject = new ConsolePaper(new ByteArrayInputStream(String.format("Value%n").getBytes()), out);
        
        this.form.printOn(subject);
        subject.askForInput();
        subject.copyTo(this.form);
        
        assertEquals(String.format("Test Form%n" +
                                   "Test Description%n%n" +
                                   "Test Field: "),
                     out.toString());
        
        assertTrue(this.form.isValid());
        this.form.onSubmit(fields -> assertEquals("Value", fields.get("Test Field")));
        this.form.submit();
    }
    
    @Test
    public void printsFieldDescriptionWhenFieldIsMarked() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsolePaper subject = new ConsolePaper(new ByteArrayInputStream(System.lineSeparator().getBytes()), out);
        
        this.form.printOn(subject);
        out.reset();
        
        this.form.markErrorsOn(subject);
        
        assertEquals(String.format("There are errors on the form:%n" +
                                   "  - Test Field: Mandatory%n"),
                     out.toString());
    }
    
    @Test
    public void defaultConstructorUsesSystemInOut() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        InputStream oldIn = System.in;
        System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes()));
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));
        
        ConsolePaper subject = new ConsolePaper();
        subject.printTitle("Hello World");
        assertEquals(String.format("Hello World%n"), out.toString());
        
        System.setIn(oldIn);
        System.setOut(oldOut);
    }
    
    @Test
    public void write() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsolePaper subject = new ConsolePaper(new ByteArrayInputStream(System.lineSeparator().getBytes()),
                                                new PrintStream(out));
        
        this.form.write("Test Field", "Pre-Filled Value");
        this.form.printOn(subject);
        
        subject.askForInput();
        
        assertEquals(String.format("Test Form%n" +
                                   "Test Description%n" +
                                   "%n" +
                                   "Test Field (Currently 'Pre-Filled Value'): "), out.toString());
        
        this.form.onSubmit(fields -> assertEquals("Pre-Filled Value", fields.get("Test Field")));
        this.form.submit();
    }
    
    private final Form form = new TestForm();
}