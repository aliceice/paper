package de.aliceice.paper;

import java.util.Map;
import java.util.function.Consumer;

public class Form {
    
    public final void printOn(Paper paper) {
        paper.printTitle(this.name);
        paper.printDescription(this.description);
        this.fields.printOn(paper);
    }
    
    public final Boolean isValid() {
        return this.fields.isValid();
    }
    
    public final void markErrorsOn(Paper paper) {
        paper.markAsInvalid();
        this.fields.markErrorsOn(paper);
    }
    
    public final void write(String fieldName, String text) {
        this.fields.write(fieldName, text);
    }
    
    public final void onSubmit(Consumer<Map<String, String>> onSubmit) {
        this.onSubmit = onSubmit;
    }
    
    public final void submit() {
        this.onSubmit.accept(this.fields.asMap());
    }

    public final String getName() {
        return this.name;
    }

    public Form(String name, Fields fields) {
        this(name, "", fields);
    }
    
    public Form(String name, String description, Fields fields) {
        this.name = name;
        this.description = description;
        this.fields = fields;
        this.onSubmit = __ -> {};
    }
    
    private final String name;
    private final String description;
    private final Fields fields;
    
    private Consumer<Map<String, String>> onSubmit;

}
