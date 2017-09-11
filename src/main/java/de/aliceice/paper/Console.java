package de.aliceice.paper;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public interface Console {
    
    void println();
    
    void println(String line);
    
    void printf(String format, Object... args);
    
    default void printErr(Exception e, String format, Object... args) {
        printErr(format, args);
        printErr(String.format("%n%n"));
        printErr(e);
    }
    
    default void printErr(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(out));
        printErr(out.toString());
    }
    
    default void printErr(String format, Object... args) {
        printErr(String.format(format, args));
    }
    
    void printErr(String error);
    
    default String readLine() {
        return readLine("");
    }
    
    String readLine(String format, Object... args);
    
    default String readSecret() {
        return readSecret("");
    }
    
    String readSecret(String format, Object... args);
}
