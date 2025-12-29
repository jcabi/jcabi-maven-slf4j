/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2025 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package org.slf4j.impl;

import com.jcabi.slf4j.JcabiLoggers;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.maven.plugin.logging.Log;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * The binding of {@link ILoggerFactory} class with
 * an actual instance of {@link ILoggerFactory} is
 * performed using information returned by this class.
 *
 * <p>This is what you should do in your Maven plugin (before everything else):
 *
 * <pre> import org.apache.maven.plugin.AbstractMojo;
 * import org.slf4j.impl.StaticLoggerBinder;
 * public class MyMojo extends AbstractMojo {
 *   &#64;Override
 *   public void execute() {
 *     StaticLoggerBinder.getSingleton().setMavenLog(this.getLog());
 *     // ... all the rest
 *   }
 * }</pre>
 *
 * <p>All SLF4J calls will be forwarded to Maven Log.
 *
 * <p>The class is thread-safe.
 *
 * @see <a href="http://www.slf4j.org/faq.html#slf4j_compatible">SLF4J FAQ</a>
 * @since 0.1.6
 */
@ToString
@EqualsAndHashCode(of = "loggers")
public final class StaticLoggerBinder implements LoggerFactoryBinder {

    /**
     * Declare the version of the SLF4J API this implementation is compiled
     * against. The value of this field is usually modified with each release.
     */
    @SuppressWarnings("PMD.LongVariable")
    public static final String REQUESTED_API_VERSION = "2.0";

    /**
     * The unique instance of this class.
     */
    private static final StaticLoggerBinder SINGLETON =
        new StaticLoggerBinder();

    /**
     * The {@link ILoggerFactory} instance returned by the
     * {@link #getLoggerFactory()} method should always be
     * the same object.
     *
     * @checkstyle VisibilityModifierCheck (5 lines)
     */
    public final transient JcabiLoggers loggers = new JcabiLoggers();

    /**
     * Private ctor to avoid direct instantiation of the class.
     */
    private StaticLoggerBinder() {
        // intentionally empty
    }

    /**
     * Return the singleton of this class.
     * @return The StaticLoggerBinder singleton
     */
    public static StaticLoggerBinder getSingleton() {
        return StaticLoggerBinder.SINGLETON;
    }

    /**
     * Set Maven Log.
     * @param log The log from Maven plugin
     */
    public void setMavenLog(final Log log) {
        this.loggers.setMavenLog(log);
    }

    /**
     * Get Maven Log.
     * @return The log
     */
    public Log getMavenLog() {
        return this.loggers.getMavenLog();
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.loggers;
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return this.loggers.getClass().getName();
    }

}
