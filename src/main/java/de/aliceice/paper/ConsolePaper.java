package de.aliceice.paper;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ConsolePaper implements Paper {
    
    @Override
    public void printTitle(String title) {
        this.console.println(title);
    }
    
    @Override
    public void printDescription(String description) {
        this.console.println(description);
        this.console.println();
    }
    
    @Override
    public void printField(String name, String description) {
        this.fields.put(name, description);
    }
    
    @Override
    public void markAsInvalid() {
        this.console.println("There are errors on the form:");
    }
    
    @Override
    public void markErrorOn(String fieldName) {
        this.console.printf("  - %s: %s", fieldName, this.fields.get(fieldName));
    }
    
    @Override
    public void copyTo(Form form) {
        this.fieldValues.forEach(form::write);
    }
    
    @Override
    public Paper write(String name, String text) {
        this.fieldValues.put(name, text);
        return this;
    }
    
    public void askForInput() {
        this.fields.keySet()
                   .forEach(field -> {
                       this.console.printf(getPromptTemplate(field),
                                           field,
                                           this.fieldValues.get(field));
            
                       String value = this.console.readLine();
                       this.fieldValues.merge(field,
                                              value,
                                              (oldValue, newValue) -> newValue.isEmpty()
                                                                      ? oldValue
                                                                      : newValue);
                   });
    }
    
    public ConsolePaper() {
        this(new IOStreamConsole());
    }
    
    public ConsolePaper(InputStream in, OutputStream out) {
        this(new IOStreamConsole(in, out));
    }
    
    public ConsolePaper(Console console) {
        this.console = console;
        this.fields = new LinkedHashMap<>();
        this.fieldValues = new HashMap<>();
    }
    
    private String getPromptTemplate(String field) {
        if (!this.fieldValues.getOrDefault(field, "")
                             .isEmpty()) {
            return "%s (Currently '%s'): ";
        }
        return "%s: ";
    }
    
    private final Console             console;
    private final Map<String, String> fields;
    private final Map<String, String> fieldValues;
}
