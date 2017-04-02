package de.aliceice.paper;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public class Field {
    
    public final void printOn(Paper paper) {
        paper.printField(this.name, this.rules.getDescription());
    }
    
    public final Boolean isNotValid() {
        return this.rules.areNotValid(this.text);
    }
    
    public final void markErrorOn(Paper paper) {
        paper.markErrorOn(this.name);
    }
    
    public final Boolean matches(String name) {
        return this.name.equals(name);
    }
    
    public final void write(String text) {
        this.text = text;
    }
    
    public final Map.Entry<String, String> asMapEntry() {
        return new SimpleImmutableEntry<>(this.name, this.text);
    }
    
    public Field(String name, Rule... rules) {
        this.name = name;
        this.rules = new Rules(rules);
        this.text = "";
    }
    
    private final String name;
    private final Rules  rules;
    private       String text;
}
