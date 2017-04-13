package de.aliceice.paper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class URLRuleTest {
    
    @Test
    public void getDescription() {
        assertEquals("URL", this.subject.getDescription());
    }
    
    @Test
    public void isValid() {
        assertTrue(this.subject.isValid("http://www.github.com/"));
        assertFalse(this.subject.isNotValid("http://www.github.com/"));
        assertFalse(this.subject.isValid("unknown://something"));
        assertTrue(this.subject.isNotValid("unknown://something"));
    }
    
    private final URLRule subject = new URLRule();
}