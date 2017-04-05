package de.aliceice.paper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class ConsolePaperTest {
    
    @Test
    public void printsOutputAndReadsFromInput() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsolePaper subject = new ConsolePaper(new ByteArrayInputStream(String.format("Value%n").getBytes()), out);
        
        this.form.printOn(subject);
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
                                   "  - Test Field: Mandatory"),
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
    
    /**
     * @todo #26 write(name, text) must be implemented for ConsolePaper.
     */
    @Test
    public void write() throws Exception {
        ConsolePaper subject = new ConsolePaper();
        assertSame(subject, subject.write("", ""));
    }
    
    private final Form form = new TestForm();
}