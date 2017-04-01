package de.aliceice.paper;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public final class Field {
    
    public void printOn(Paper paper) {
        paper.printField(this.name, this.rules.getDescription());
    }
    
    public Boolean isNotValid() {
        return this.rules.areNotValid(this.text);
    }
    
    public void markErrorOn(Paper paper) {
        paper.markErrorOn(this.name);
    }
    
    public Boolean matches(String name) {
        return this.name.equals(name);
    }
    
    public void write(String text) {
        this.text = text;
    }
    
    public Map.Entry<String, String> asMapEntry() {
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
