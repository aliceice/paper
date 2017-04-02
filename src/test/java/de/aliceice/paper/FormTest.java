package de.aliceice.paper;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class FormTest {
    
    @Test
    public void canCreateFormWithoutDescription() throws Exception {
        new Form("Form without description", new Fields()).printOn(this.paper);
        
        this.paper.hasTitle("Form without description");
        this.paper.hasDescription("");
    }
    
    @Test
    public void printOnPaperPrintsTitleAndDescription() throws Exception {
        this.subject.printOn(this.paper);
        
        this.paper.hasTitle("Test Form");
        this.paper.hasDescription("Testing a form");
    }
    
    @Test
    public void printOnPaperPrintsAllFields() throws Exception {
        this.subject.printOn(this.paper);
    
        this.paper.hasField("Field 1", "Mandatory", "Max length 5");
        this.paper.hasField("Field 2", "Mandatory", "Fixed length 12");
    }
    
    @Test
    public void isValidIsFalseIfNothingHasBeenWrittenIntoTheForm() throws Exception {
        assertFalse(this.subject.isValid());
        
        this.subject.markErrorsOn(this.paper);
        
        this.paper.isMarkedInvalid();
        this.paper.hasMarkedFields("Field 1", "Field 2");
    }
    
    @Test
    public void isValidIsTrueWhenAllFieldsAreFilledCorrectly() throws Exception {
        fillOutForm();
        assertTrue(this.subject.isValid());
    }
    
    @Test
    public void executesOnSubmitActionIfSubmitIsTriggered() throws Exception {
        AtomicReference<String> reference = new AtomicReference<>("");
    
        fillOutForm();
        this.subject.submit();
        
        assertEquals("", reference.get());
        
        this.subject.onSubmit(fields -> reference.set(fields.toString()));
        this.subject.submit();
        
        assertEquals("{Field 1=Hi, Field 2=Hello World!}", reference.get());
    }
    
    private void fillOutForm() {
        this.subject.write("Field 1", "Hi");
        this.subject.write("Field 2", "Hello World!");
    }
    
    private final Form  subject = new Form("Test Form",
                                           "Testing a form",
                                           new Fields(new Field("Field 1",
                                                                new MandatoryRule(),
                                                                new MaxLengthRule(5)),
                                                      new Field("Field 2",
                                                                new MandatoryRule(),
                                                                new FixedLengthRule(12))));
    private final Paper paper   = new Paper();
}
