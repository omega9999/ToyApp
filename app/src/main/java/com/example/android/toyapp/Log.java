package com.example.android.toyapp;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {
    public static void v(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.v(tag, String.format(message, args));
    }

    public static void d(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.d(tag, String.format(message, args));
    }

    public static void i(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.i(tag, String.format(message, args));
    }

    public static void w(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.w(tag, String.format(message, args));
    }

    public static void e(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.e(tag, String.format(message, args));
    }

    public static void wtf(@NonNull final String tag, @NonNull final String message, Object... args) {
        android.util.Log.wtf(tag, String.format(message, args));
    }

    /**
     * method to print in verbose log stacktrace of caller
     *
     * @param tag
     * @param message
     */
    public static void logStacktrace(@NonNull final String tag, @NonNull final String message) {
        Log.v(tag, String.format("%s\n%s", message, toString(Thread.currentThread().getStackTrace())));
    }

    private static String toString(StackTraceElement[] trace) {
        final StringWriter sw = new StringWriter(256);
        final PrintWriter pw = new PrintWriter(sw, false);
        for (StackTraceElement traceElement : trace)
            pw.println("\tat " + traceElement);
        pw.flush();
        final String str = sw.toString();
        pw.close();
        try {
            sw.close();
        } catch (IOException ignored) {

        }
        return str;
    }

}
