package de.aliceice.paper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public final class IOStreamConsole implements Console {
    
    @Override
    public void println() {
        this.out.println();
    }
    
    @Override
    public void println(String line) {
        this.out.println(line);
    }
    
    @Override
    public void printf(String format, Object... args) {
        this.out.printf(format, args);
    }
    
    @Override
    public String readLine(String format, Object... args) {
        this.out.printf(format, args);
        return this.in.nextLine();
    }
    
    public IOStreamConsole() {
        this(System.in, System.out);
    }
    
    public IOStreamConsole(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out, true);
    }
    
    private final Scanner     in;
    private final PrintWriter out;
}
