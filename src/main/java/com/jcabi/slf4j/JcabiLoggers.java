/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2025 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Implementation of {@link ILoggerFactory} returning the appropriate
 * named {@link Slf4jAdapter} instance.
 *
 * <p>The class is thread-safe.
 *
 * @see <a href="http://www.slf4j.org/faq.html#slf4j_compatible">SLF4J FAQ</a>
 * @since 0.1.6
 */
@ToString
@EqualsAndHashCode
public final class JcabiLoggers implements ILoggerFactory {

    /**
     * Maven log.
     */
    @SuppressWarnings("PMD.ProperLogger")
    private transient Log mlog = new SystemStreamLog();

    @Override
    public Logger getLogger(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("logger name can't be NULL");
        }
        return new Slf4jAdapter(this.mlog, name);
    }

    /**
     * Set Maven log.
     * @param log The log to set
     */
    public void setMavenLog(final Log log) {
        synchronized (JcabiLoggers.class) {
            this.mlog = log;
        }
    }

    /**
     * Get Maven log.
     * @return The log
     */
    public Log getMavenLog() {
        return this.mlog;
    }

}
