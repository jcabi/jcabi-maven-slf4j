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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * Test case for {@link JcabiLoggers}.
 *
 * @since 0.1
 */
public final class JcabiLoggersTest {

    @BeforeAll
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void init() {
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
    public void retrievesLoggerByName() {
        MatcherAssert.assertThat(
            new JcabiLoggers().getLogger("root"),
            Matchers.instanceOf(Slf4jAdapter.class)
        );
    }

    public void throwsWhenLoggerNameIsNull() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new JcabiLoggers().getLogger(null)
        );
    }

    @Test
    public void worksWithoutMavenLog() {
        new JcabiLoggers().getLogger("test").info(
            "this message should be visible in system stream"
        );
    }

}
