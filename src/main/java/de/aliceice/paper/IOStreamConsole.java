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
        printf(line + "%n");
    }
    
    @Override
    public void printf(String format, Object... args) {
        this.out.printf(format, args);
    }
    
    @Override
    public void printErr(String error) {
        this.err.printf(error);
    }
    
    @Override
    public String readLine(String format, Object... args) {
        printf(format, args);
        return this.in.nextLine();
    }
    
    @Override
    public String readSecret(String format, Object... args) {
        println("WARNING: I/O Stream Console can't hide secrets. All input will be visible.");
        return readLine(format, args);
    }
    
    public IOStreamConsole() {
        this(System.in, System.out);
    }
    
    public IOStreamConsole(OutputStream out) {
        this(System.in, out, System.err);
    }
    
    public IOStreamConsole(OutputStream out, OutputStream err) {
        this(System.in, out, err);
    }
    
    public IOStreamConsole(InputStream in) {
        this(in, System.out, System.err);
    }
    
    public IOStreamConsole(InputStream in, OutputStream out) {
        this(in, out, System.err);
    }
    
    public IOStreamConsole(InputStream in, OutputStream out, OutputStream err) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out, true);
        this.err = new PrintWriter(err, true);
    }
    
    private final Scanner     in;
    private final PrintWriter out;
    private final PrintWriter err;
}
