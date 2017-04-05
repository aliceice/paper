package de.aliceice.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestPaper implements Paper {
    
    @Override
    public void printTitle(String title) {
        this.name = title;
    }
    
    @Override
    public void printDescription(String description) {
        this.description = description;
    }
    
    @Override
    public void printField(String name, String description) {
        this.printedFields.put(name, description);
    }
    
    @Override
    public void markAsInvalid() {
        this.isMarkedInvalid = true;
    }
    
    @Override
    public void markErrorOn(String fieldName) {
        this.markedFields.add(fieldName);
    }
    
    @Override
    public void copyTo(Form form) {
        this.fieldValues.forEach(form::write);
    }
    
    @Override
    public TestPaper write(String fieldName, String value) {
        this.fieldValues.put(fieldName, value);
        return this;
    }
    
    public TestPaper hasTitle(String title) {
        assertEquals(title, this.name);
        return this;
    }
    
    public TestPaper hasDescription(String description) {
        assertEquals(description, this.description);
        return this;
    }
    
    public TestPaper hasField(String name, String... rules) {
        assertTrue(this.printedFields.containsKey(name),
                   String.format("%s is not printed.%n Available fields are: %s",
                                 name, this.printedFields.keySet()));
        assertEquals(Stream.of(rules).collect(Collectors.joining(System.lineSeparator())),
                     this.printedFields.get(name));
        return this;
    }
    
    public TestPaper hasFieldValue(String name, String value) {
        assertTrue(this.fieldValues.containsKey(name),
                   String.format("%s has no value.%n Available fields are: %s",
                                 name, this.fieldValues.keySet()));
        assertEquals(value, this.fieldValues.get(name));
        return this;
    }
    
    public TestPaper isMarkedInvalid() {
        assertTrue(this.isMarkedInvalid);
        return this;
    }
    
    public TestPaper hasMarkedFields(String... fields) {
        Stream.of(fields).forEach(field -> {
            assertTrue(this.markedFields.contains(field),
                       String.format("%s is not marked as invalid. Marked fields are: %s",
                                     field, this.markedFields));
        });
        return this;
    }
    
    private String              name            = "";
    private String              description     = "";
    private Map<String, String> printedFields   = new HashMap<>();
    private Map<String, String> fieldValues     = new HashMap<>();
    private Boolean             isMarkedInvalid = false;
    private List<String>        markedFields    = new ArrayList<>();
}
