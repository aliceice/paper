package de.aliceice.paper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BooleanRuleTest {

    @Test
    public void getDescription() throws Exception {
        assertEquals("Yes/No", this.subject.getDescription());
    }
    
    @Test
    public void isValid() throws Exception {
        assertTrue(this.subject.isValid("Yes"));
        assertTrue(this.subject.isValid("No"));
        assertFalse(this.subject.isValid("Y"));
        assertFalse(this.subject.isValid("N"));
    }
    
    private final BooleanRule subject = new BooleanRule();
}