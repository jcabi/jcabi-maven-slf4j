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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * Test case for {@link JcabiLoggers}.
 * @since 0.1
 */
final class JcabiLoggersTest {

    @BeforeAll
    static void init() {
        StaticLoggerBinder.getSingleton().setMavenLog(
            new DefaultLog(
                new ConsoleLogger(
                    Logger.LEVEL_DEBUG,
                    JcabiLoggersTest.class.getName()
                )
            )
        );
    }

    @Test
    void retrievesLoggerByName() {
        MatcherAssert.assertThat(
            "should retrieves logger by name",
            new JcabiLoggers().getLogger("root"),
            Matchers.instanceOf(Slf4jAdapter.class)
        );
    }

    @Test
    void worksWithoutMavenLog() {
        final org.slf4j.Logger logger = new JcabiLoggers().getLogger("test");
        logger.info("this message should be visible in system stream");
        MatcherAssert.assertThat(
            "logger is created",
            logger,
            Matchers.notNullValue()
        );
    }
}
