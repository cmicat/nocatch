package com.github.nocatch;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Default wrapper exception for any checked exception. Also thrown if the API isn't used correctly.
 */

public class ExceptionAdapter extends RuntimeException {

    private final String stackTrace;
    public final Exception originalException;

    public ExceptionAdapter(Exception e) {
        super(e.toString());
        originalException = e;
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        stackTrace = sw.toString();
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(java.io.PrintStream s) {
        synchronized (s) {
            s.print(getClass().getName() + ": ");
            s.print(stackTrace);
        }
    }

    public void printStackTrace(java.io.PrintWriter s) {
        synchronized (s) {
            s.print(getClass().getName() + ": ");
            s.print(stackTrace);
        }
    }

    public void rethrow() {
        throw originalException;
    }
}
