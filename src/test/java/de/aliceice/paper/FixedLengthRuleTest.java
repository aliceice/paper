package de.aliceice.paper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class FixedLengthRuleTest {
    
    @Test
    public void getDescription() throws Exception {
        assertEquals("Fixed length 5", this.subject.getDescription());
    }

    @Test
    public void isValid() throws Exception {
        assertTrue(this.subject.isValid("12345"));
        assertTrue(this.subject.isNotValid("123456"));
    }
    
    private final FixedLengthRule subject = new FixedLengthRule(5);
}