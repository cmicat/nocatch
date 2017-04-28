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

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(java.io.PrintStream s) {
        synchronized (s) {
            s.print(stackTrace);
        }
    }

    @Override
    public void printStackTrace(java.io.PrintWriter s) {
        synchronized (s) {
            s.print(stackTrace);
        }
    }

    @Override
    public String getMessage() {
        return originalException.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return originalException.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return originalException.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return originalException.initCause(cause);
    }

    @Override
    public String toString() {
        return originalException.toString();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return originalException.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        originalException.setStackTrace(stackTrace);
    }

    public void rethrow() throws Exception {
        throw originalException;
    }


}
