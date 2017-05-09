package de.aliceice.paper;

public interface Console {
    
    void println();
    
    void println(String line);
    
    void printf(String format, Object... args);
    
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
