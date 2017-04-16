package de.aliceice.paper;

public interface Console {
    
    void println();
    
    void println(String line);
    
    void printf(String format, Object... args);
    
    default String readLine() {
        return readLine("");
    }
    
    String readLine(String format, Object...args);
    
}
