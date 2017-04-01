package de.aliceice.paper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class Fields {
    
    public void printOn(Paper paper) {
        this.fields.forEach(field -> field.printOn(paper));
    }
    
    public Boolean isValid() {
        return this.fields.stream()
                          .noneMatch(Field::isNotValid);
    }
    
    public void markErrorsOn(Paper paper) {
        this.fields.stream()
                   .filter(Field::isNotValid)
                   .forEach(field -> field.markErrorOn(paper));
    }
    
    public void write(String name, String text) {
        this.fields.stream()
                   .filter(field -> field.matches(name))
                   .findAny()
                   .ifPresent(field -> field.write(text));
    }
    
    public Map<String, String> asMap() {
        return this.fields.stream()
                          .map(Field::asMapEntry)
                          .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
    
    public Fields(Field... fields) {
        this.fields = Arrays.asList(fields);
    }
    
    private final List<Field> fields;
}
