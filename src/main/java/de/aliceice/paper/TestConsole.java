package de.aliceice.paper;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestConsole implements Console {
    
    @Override
    public void println() {
        this.output.append(System.lineSeparator());
    }
    
    @Override
    public void println(String line) {
        this.output.append(line).append(System.lineSeparator());
    }
    
    @Override
    public void printf(String format, Object... args) {
        this.output.append(String.format(format, args));
    }
    
    @Override
    public void printErr(String error) {
        this.output.append(error);
    }
    
    @Override
    public String readLine(String format, Object... args) {
        printf(format, args);
        this.output.append(this.inputLines.peekFirst())
                   .append(System.lineSeparator());
        return this.inputLines.pollFirst();
    }
    
    @Override
    public String readSecret(String format, Object... args) {
        printf(format, args);
        this.output.append("***")
                   .append(System.lineSeparator());
        return this.inputLines.pollFirst();
    }
    
    public void hasOutput(String format, Object... arguments) {
        assertEquals(String.format(format, arguments), this.output.toString());
    }
    
    public void useAsInput(String... inputLines) {
        this.inputLines.addAll(Arrays.asList(inputLines));
    }
    
    private StringBuilder      output     = new StringBuilder();
    private LinkedList<String> inputLines = new LinkedList<>();
}
