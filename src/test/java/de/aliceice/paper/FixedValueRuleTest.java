package de.aliceice.paper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class FixedValueRuleTest {
    
    @Test
    public void description() throws Exception {
        assertEquals("Must be 'Fixed'", this.subject.getDescription());
    }
    
    @Test
    public void isValid() throws Exception {
        assertTrue(this.subject.isValid("Fixed"));
        assertFalse(this.subject.isNotValid("Fixed"));
        assertFalse(this.subject.isValid("Something else"));
        assertTrue(this.subject.isNotValid("Something else"));
    }
    
    private final Rule subject = new FixedValueRule("Fixed");
}
