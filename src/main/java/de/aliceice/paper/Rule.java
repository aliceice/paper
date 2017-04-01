package de.aliceice.paper;

public interface Rule {
    
    String getDescription();
    
    Boolean isValid(String value);
    
    default Boolean isNotValid(String value) {
        return !isValid(value);
    }
}
