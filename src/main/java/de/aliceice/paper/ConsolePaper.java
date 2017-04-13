package de.aliceice.paper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public final class ConsolePaper implements Paper {
    
    @Override
    public void printTitle(String title) {
        this.out.println(title);
    }
    
    @Override
    public void printDescription(String description) {
        this.out.println(description);
        this.out.println();
    }
    
    @Override
    public void printField(String name, String description) {
        this.fields.put(name, description);
    }
    
    @Override
    public void markAsInvalid() {
        this.out.println("There are errors on the form:");
    }
    
    @Override
    public void markErrorOn(String fieldName) {
        this.out.printf("  - %s: %s", fieldName, this.fields.get(fieldName));
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
                       this.out.printf(getPromptTemplate(field),
                                       field,
                                       this.fieldValues.get(field));
            
                       String value = this.in.nextLine();
                       this.fieldValues.merge(field,
                                              value,
                                              (oldValue, newValue) -> newValue.isEmpty()
                                                                      ? oldValue
                                                                      : newValue);
                   });
    }
    
    public ConsolePaper() {
        this(System.in, System.out);
    }
    
    public ConsolePaper(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out, true);
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
    private final Scanner             in;
    private final PrintWriter         out;
    private final Map<String, String> fields;
    private final Map<String, String> fieldValues;
}
