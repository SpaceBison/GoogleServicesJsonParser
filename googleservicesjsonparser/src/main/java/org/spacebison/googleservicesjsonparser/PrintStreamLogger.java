package org.spacebison.googleservicesjsonparser;

import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

import java.io.PrintStream;

public class PrintStreamLogger extends MarkerIgnoringBase implements Logger {
    private PrintStream mDebugStream = System.out;
    private PrintStream mLifecycleStream = System.out;
    private PrintStream mQuietStream = System.out;
    private PrintStream mInfoStream = System.out;
    private PrintStream mWarnStream = System.err;
    private PrintStream mErrorStream = System.err;
    private PrintStream mTraceStream = System.out;

    private static void log(PrintStream stream, String message) {
        stream.println(message);
    }

    private static void log(PrintStream stream, String message, Object... objects) {
        stream.format(message, objects);
    }

    private static void log(PrintStream stream, String message, Throwable throwable) {
        log(stream, message);
        throwable.printStackTrace(stream);
    }

    @Override
    public boolean isLifecycleEnabled() {
        return true;
    }

    @Override
    public void lifecycle(String message) {
        log(LogLevel.LIFECYCLE, message);
    }

    @Override
    public void lifecycle(String message, Object... objects) {
        log(LogLevel.LIFECYCLE, message, objects);
    }

    @Override
    public void lifecycle(String message, Throwable throwable) {
        log(LogLevel.LIFECYCLE, message, throwable);
    }

    @Override
    public boolean isQuietEnabled() {
        return true;
    }

    @Override
    public void quiet(String message) {
        log(LogLevel.QUIET, message);
    }

    @Override
    public void quiet(String message, Object... objects) {
        log(LogLevel.QUIET, message, objects);
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String message) {
        log(Level.TRACE, message);
    }

    @Override
    public void trace(String format, Object arg) {
        log(Level.TRACE, format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        log(Level.TRACE, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        log(Level.TRACE, format, arguments);
    }

    @Override
    public void trace(String message, Throwable t) {
        log(Level.TRACE, message, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    @Override
    public void debug(String format, Object arg) {
        log(Level.DEBUG, format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        log(Level.DEBUG, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        log(Level.DEBUG, format, arguments);
    }

    @Override
    public void debug(String message, Throwable t) {
        log(Level.DEBUG, message, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String message) {
        log(Level.INFO, message);
    }

    @Override
    public void info(String format, Object arg) {
        log(Level.INFO, format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        log(Level.INFO, format, arg1, arg2);
    }

    @Override
    public void info(String message, Object... objects) {
        log(Level.INFO, message, objects);
    }

    @Override
    public void info(String message, Throwable t) {
        log(Level.INFO, message, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String message) {
        log(Level.WARN, message);
    }

    @Override
    public void warn(String format, Object arg) {
        log(Level.WARN, format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        log(Level.WARN, format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        log(Level.WARN, format, arg1, arg2);
    }

    @Override
    public void warn(String message, Throwable t) {
        log(Level.WARN, message, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String message) {
        log(Level.ERROR, message);
    }

    @Override
    public void error(String format, Object arg) {
        log(Level.ERROR, format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        log(Level.ERROR, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        log(Level.ERROR, format, arguments);
    }

    @Override
    public void error(String message, Throwable t) {
        log(Level.ERROR, message, t);
    }

    @Override
    public void quiet(String message, Throwable throwable) {
        log(mQuietStream, message, throwable);
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return true;
    }

    @Override
    public void log(LogLevel level, String message) {
        log(getStreamForLevel(level), message);
    }

    @Override
    public void log(LogLevel level, String message, Object... objects) {
        log(getStreamForLevel(level), message, objects);
    }

    @Override
    public void log(LogLevel level, String message, Throwable throwable) {
        log(getStreamForLevel(level), message, throwable);
    }

    public void log(Level level, String message, Object... objects) {
        log(getStreamForLevel(level), message, objects);
    }

    public void log(Level level, String message, Throwable throwable) {
        log(getStreamForLevel(level), message, throwable);
    }

    private void log(Level level, String message) {
        log(getStreamForLevel(level), message);
    }

    private PrintStream getStreamForLevel(LogLevel level) {
        switch (level) {
            case DEBUG:
                return mDebugStream;
            case INFO:
                return mInfoStream;
            case LIFECYCLE:
                return mLifecycleStream;
            case WARN:
                return mWarnStream;
            case QUIET:
                return mQuietStream;
            case ERROR:
                return mErrorStream;
        }

        return null;
    }

    private PrintStream getStreamForLevel(Level level) {
        switch (level) {
            case DEBUG:
                return mDebugStream;
            case INFO:
                return mInfoStream;
            case WARN:
                return mWarnStream;
            case ERROR:
                return mErrorStream;
            case TRACE:
                return mTraceStream;
        }

        return null;
    }
}
