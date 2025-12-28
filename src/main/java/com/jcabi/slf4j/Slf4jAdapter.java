/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2025 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.maven.plugin.logging.Log;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.LegacyAbstractLogger;
import org.slf4j.helpers.MessageFormatter;

/**
 * Implementation of {@link org.slf4j.Logger} transforming SLF4J messages
 * to Maven log messages.
 *
 * <p>The class has too many methods, but
 * we can't do anything with this since the parent class requires
 * us to implement them all.
 *
 * <p>The class is thread-safe.
 *
 * @since 0.1.6
 * @see <a href="http://www.slf4j.org/faq.html#slf4j_compatible">SLF4J FAQ</a>
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("PMD.TooManyMethods")
final class Slf4jAdapter extends LegacyAbstractLogger {

    /**
     * Serialization ID.
     */
    public static final long serialVersionUID = 0x12C0976798AB5439L;

    /**
     * The log to use.
     */
    @SuppressWarnings("PMD.ProperLogger")
    private final transient Log mlog;

    /**
     * The name of the log.
     */
    private final transient String label;

    /**
     * Public ctor.
     * @param log The log to use
     * @param name The label of the logger
     */
    Slf4jAdapter(final Log log, final String name) {
        super();
        this.mlog = log;
        this.label = name;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void trace(final String msg) {
        this.mlog.debug(this.decorate(msg));
    }

    @Override
    public void trace(final String format, final Object arg) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, arg)));
    }

    @Override
    public void trace(final String format, final Object first,
        final Object second) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, first, second)));
    }

    @Override
    public void trace(final String format, final Object... array) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, array)));
    }

    @Override
    public void trace(final String msg, final Throwable th) {
        this.mlog.debug(this.decorate(msg), th);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.mlog.isDebugEnabled();
    }

    @Override
    public void debug(final String msg) {
        this.mlog.debug(this.decorate(msg));
    }

    @Override
    public void debug(final String format, final Object arg) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, arg)));
    }

    @Override
    public void debug(final String format, final Object first,
        final Object second) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, first, second)));
    }

    @Override
    public void debug(final String format, final Object... array) {
        this.mlog.debug(this.decorate(Slf4jAdapter.format(format, array)));
    }

    @Override
    public void debug(final String msg, final Throwable th) {
        this.mlog.debug(this.decorate(msg), th);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(final String msg) {
        this.mlog.info(msg);
    }

    @Override
    public void info(final String format, final Object arg) {
        this.mlog.info(Slf4jAdapter.format(format, arg));
    }

    @Override
    public void info(final String format, final Object first,
        final Object second) {
        this.mlog.info(Slf4jAdapter.format(format, first, second));
    }

    @Override
    public void info(final String format, final Object... array) {
        this.mlog.info(Slf4jAdapter.format(format, array));
    }

    @Override
    public void info(final String msg, final Throwable th) {
        this.mlog.info(msg, th);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(final String msg) {
        this.mlog.warn(msg);
    }

    @Override
    public void warn(final String format, final Object arg) {
        this.mlog.warn(Slf4jAdapter.format(format, arg));
    }

    @Override
    public void warn(final String format, final Object... array) {
        this.mlog.warn(Slf4jAdapter.format(format, array));
    }

    @Override
    public void warn(final String format, final Object first,
        final Object second) {
        this.mlog.warn(Slf4jAdapter.format(format, first, second));
    }

    @Override
    public void warn(final String msg, final Throwable th) {
        this.mlog.warn(msg, th);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(final String msg) {
        this.mlog.error(msg);
    }

    @Override
    public void error(final String format, final Object arg) {
        this.mlog.error(Slf4jAdapter.format(format, arg));
    }

    @Override
    public void error(final String format, final Object first,
        final Object second) {
        this.mlog.error(Slf4jAdapter.format(format, first, second));
    }

    @Override
    public void error(final String format, final Object... array) {
        this.mlog.error(Slf4jAdapter.format(format, array));
    }

    @Override
    public void error(final String msg, final Throwable th) {
        this.mlog.error(msg, th);
    }

    @Override
    public String getFullyQualifiedCallerName() {
        return "jcabi-maven-slf4j";
    }

    @Override
    public void handleNormalizedLoggingCall(final Level level, final Marker marker,
        final String msg, final Object[] arguments, final Throwable throwable) {
        throw new UnsupportedOperationException("we should not reach this point ever");
    }

    /**
     * Format with one object.
     * @param format Format to use
     * @param arg One argument
     * @return The message
     */
    private static String format(final String format, final Object arg) {
        final FormattingTuple tuple =
            MessageFormatter.format(format, arg);
        return tuple.getMessage();
    }

    /**
     * Format with two objects.
     * @param format Format to use
     * @param first First argument
     * @param second Second argument
     * @return The message
     */
    private static String format(final String format, final Object first,
        final Object second) {
        final FormattingTuple tuple =
            MessageFormatter.format(format, first, second);
        return tuple.getMessage();
    }

    /**
     * Format with array.
     * @param format Format to use
     * @param array List of arguments
     * @return The message
     */
    private static String format(final String format, final Object... array) {
        final FormattingTuple tuple =
            MessageFormatter.format(format, array);
        return tuple.getMessage();
    }

    /**
     * Decorate a message with a label prefix.
     * @param msg The text to decorate
     * @return The message decorated
     */
    private String decorate(final String msg) {
        return String.format(
            "%s %s: %s",
            Thread.currentThread().getName(),
            this.label,
            msg
        );
    }

}
