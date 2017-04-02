package de.aliceice.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Paper {
    
    public void printTitle(String name) {
        this.name = name;
    }
    
    public void printDescription(String description) {
        this.description = description;
    }
    
    public void printField(String name, String description) {
        this.printedFields.put(name, description);
    }
    
    public void markAsInvalid() {
        this.isMarkedInvalid = true;
    }
    
    public void markErrorOn(String name) {
        this.markedFields.add(name);
    }
    
    public void hasTitle(String name) {
        assertEquals(name, this.name);
    }
    
    public void hasDescription(String description) {
        assertEquals(description, this.description);
    }
    
    public void hasField(String name, String... rules) {
        assertTrue(this.printedFields.containsKey(name),
                   String.format("%s is not printed.%n Available fields are: %s",
                                 name, this.printedFields.keySet()));
        assertEquals(Stream.of(rules).collect(Collectors.joining(System.lineSeparator())),
                     this.printedFields.get(name));
        
    }
    
    public void isMarkedInvalid() {
        assertTrue(this.isMarkedInvalid);
    }
    
    public void hasMarkedFields(String... fields) {
        Stream.of(fields).forEach(field -> {
            assertTrue(this.markedFields.contains(field),
                       String.format("%s is not marked as invalid. Marked fields are: %s",
                                     field, this.markedFields));
        });
    }
    
    private String              name            = "";
    private String              description     = "";
    private Map<String, String> printedFields   = new HashMap<>();
    private boolean             isMarkedInvalid = false;
    private List<String>        markedFields    = new ArrayList<>();
}
