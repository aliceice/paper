package de.aliceice.paper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MaxLengthRuleTest {

    @Test
    public void getDescription() throws Exception {
        assertEquals("Max length 5", this.subject.getDescription());
    }
    
    @Test
    public void isValid() throws Exception {
        assertTrue(this.subject.isValid(""));
        assertTrue(this.subject.isValid("1"));
        assertTrue(this.subject.isValid("12"));
        assertTrue(this.subject.isValid("123"));
        assertTrue(this.subject.isValid("1234"));
        assertTrue(this.subject.isValid("12345"));
        assertFalse(this.subject.isValid("123456"));
        
        assertTrue(this.subject.isNotValid("123456"));
        assertFalse(this.subject.isNotValid("12345"));
        assertFalse(this.subject.isNotValid("1234"));
        assertFalse(this.subject.isNotValid("123"));
        assertFalse(this.subject.isNotValid("12"));
        assertFalse(this.subject.isNotValid("1"));
        assertFalse(this.subject.isNotValid(""));
    }
    
    private final MaxLengthRule subject = new MaxLengthRule(5);
}