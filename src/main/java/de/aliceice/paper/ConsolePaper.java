package de.aliceice.paper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        this.fieldDescriptions.put(name, Stream.of(description.split(System.lineSeparator()))
                                               .collect(Collectors.joining(", ")));
    }
    
    @Override
    public void markAsInvalid() {
        this.console.printErr(String.format("There are errors on the form:%n"));
    }
    
    @Override
    public void markErrorOn(String fieldName) {
        this.console.printErr("  - %s: %s%n", fieldName, this.fieldDescriptions.get(fieldName));
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
        this.fieldDescriptions.keySet()
                              .forEach(field -> {
                                  this.fieldValues.merge(field,
                                                         getInput(field),
                                                         (oldValue, newValue) -> newValue.isEmpty()
                                                                                 ? oldValue
                                                                                 : newValue);
                              });
    }
    
    public ConsolePaper() {
        this(new IOStreamConsole());
    }
    
    public ConsolePaper(Console console) {
        this.console = console;
        this.fieldDescriptions = new LinkedHashMap<>();
        this.fieldValues = new HashMap<>();
    }
    
    private String getInput(String field) {
        String prompt = String.format(getPromptTemplate(field),
                                      field,
                                      this.fieldValues.get(field));
        return this.fieldDescriptions.get(field).contains("Secret")
               ? this.console.readSecret(prompt)
               : this.console.readLine(prompt);
    }
    
    private String getPromptTemplate(String field) {
        if (!this.fieldValues.getOrDefault(field, "")
                             .isEmpty()) {
            return "%s (Currently '%s'): ";
        }
        return "%s: ";
    }
    
    private final Console             console;
    private final Map<String, String> fieldDescriptions;
    private final Map<String, String> fieldValues;
}
