/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2026 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import org.apache.maven.monitor.logging.DefaultLog;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Slf4jAdapter}.
 *
 * @since 0.1
 * @checkstyle ExecutableStatementCountCheck (500 lines)
 */
public final class Slf4jAdapterTest {

    @Test
    public void sendsLogMessagesThrough() {
        final Slf4jAdapter logger = new Slf4jAdapter(
            new DefaultLog(
                new ConsoleLogger(
                    Logger.LEVEL_DEBUG,
                    Slf4jAdapterTest.class.getName()
                )
            ),
            "com.jcabi.test"
        );
        logger.isTraceEnabled();
        logger.isDebugEnabled();
        logger.isInfoEnabled();
        logger.isWarnEnabled();
        logger.isErrorEnabled();
        MatcherAssert.assertThat(
            Slf4jAdapter.class.getName(),
            Matchers.equalTo(logger.getName())
        );
        logger.trace("trace-test message");
        logger.trace("trace-test-2", new IllegalArgumentException("trace-ex"));
        logger.trace("trace-test {}", "trace-message");
        logger.trace("trace-test {} {}", "trc1", "trc2");
        logger.trace("trace-test-2 {} {}", "trace-1", "trace-2");
        logger.debug("debug-test message");
        logger.debug("debug-test-2", new IllegalArgumentException("debug-ex"));
        logger.debug("debug-test {}", "debug-message");
        logger.debug("debug-test {} {}", "dbg1", "dbg2");
        logger.debug("debug-test-2 {} {}", "debug-1", "debug-2");
        logger.info("info-test message");
        logger.info("info-test-2", new IllegalArgumentException("info-ex"));
        logger.info("info-test {}", "info-message");
        logger.info("info-test {} {}", "inf1", "inf2");
        logger.info("info-test-2 {} {}", "info-1", "info-2");
        logger.warn("warn-test message");
        logger.warn("warn-test-2", new IllegalArgumentException("warn-ex"));
        logger.warn("warn-test {}", "warn-message");
        logger.warn("warn-test {} {}", "warn--1", "warn--2");
        logger.warn("warn-test-2 {} {}", "warn-1", "warn-2");
        logger.error("error-test message");
        logger.error("error-test-2", new IllegalArgumentException("error-ex"));
        logger.error("error-test {}", "error-message");
        logger.error("error-test {} {}", "error-1", "error-2");
        logger.error("error-test-2 {} {}", "err-1", "err-2");
    }

}
