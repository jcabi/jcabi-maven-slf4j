/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2025 Yegor Bugayenko
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
 *
 * @since 0.1
 */
final class JcabiLoggersTest {

    @BeforeAll
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
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
        new JcabiLoggers().getLogger("test").info(
            "this message should be visible in system stream"
        );
    }

}
