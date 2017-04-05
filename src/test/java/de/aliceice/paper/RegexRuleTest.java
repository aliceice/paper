package de.aliceice.paper;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EnglishLocaleExtension.class)
public final class RegexRuleTest {
    
    @Test
    public void description() throws Exception {
        assertEquals(String.format("Example: 12345 | Regular expression '%s'", Pattern.compile("\\d{5}")),
                     this.subject.getDescription());
    }
    
    @Test
    public void isValid() throws Exception {
        assertTrue(this.subject.isValid("11111"));
        assertTrue(this.subject.isValid("12345"));
        assertFalse(this.subject.isNotValid("12345"));
        assertTrue(this.subject.isNotValid("1234"));
        assertTrue(this.subject.isNotValid("1234a"));
        assertTrue(this.subject.isNotValid("abcde"));
    }
    
    private final Rule subject = new RegexRule("\\d{5}", "12345");
}
