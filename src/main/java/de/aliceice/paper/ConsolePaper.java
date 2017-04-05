package de.aliceice.paper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
        this.fieldDescriptions.put(name, description);
        this.out.printf("%s: ", name);
        this.fieldValues.put(name, this.in.nextLine());
    }
    
    @Override
    public void markAsInvalid() {
        this.out.println("There are errors on the form:");
    }
    
    @Override
    public void markErrorOn(String fieldName) {
        this.out.printf("  - %s: %s", fieldName, this.fieldDescriptions.get(fieldName));
    }
    
    @Override
    public void copyTo(Form form) {
        this.fieldValues.forEach(form::write);
    }
    
    @Override
    public Paper write(String name, String text) {
        return this;
    }
    
    public ConsolePaper() {
        this(System.in, System.out);
    }
    
    public ConsolePaper(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out, true);
        this.fieldValues = new HashMap<>();
        this.fieldDescriptions = new HashMap<>();
    }
    
    private final Scanner             in;
    private final PrintWriter         out;
    private final Map<String, String> fieldValues;
    private final Map<String, String> fieldDescriptions;
}
