package de.aliceice.paper;

public interface Paper {
    
    void printTitle(String title);
    
    void printDescription(String description);
    
    void printField(String name, String description);
    
    void markAsInvalid();
    
    void markErrorOn(String fieldName);
    
    void copyTo(Form form);
}
