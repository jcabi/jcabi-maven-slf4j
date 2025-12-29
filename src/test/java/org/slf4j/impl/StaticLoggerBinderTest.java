/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2025 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package org.slf4j.impl;

import com.jcabi.slf4j.JcabiLoggers;
import org.apache.maven.monitor.logging.DefaultLog;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link StaticLoggerBinder}.
 *
 * @since 0.1
 */
final class StaticLoggerBinderTest {

    @BeforeAll
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void init() {
        StaticLoggerBinder.getSingleton().setMavenLog(
            new DefaultLog(
                new ConsoleLogger(
                    Logger.LEVEL_DEBUG,
                    StaticLoggerBinderTest.class.getName()
                )
            )
        );
    }

    @Test
    void createsLoggerFactoryOfCustomClass() {
        MatcherAssert.assertThat(
            StaticLoggerBinder.getSingleton().getLoggerFactory(),
            Matchers.instanceOf(JcabiLoggers.class)
        );
    }

    @Test
    void retrievesLoggerFactoryString() {
        MatcherAssert.assertThat(
            StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr(),
            Matchers.equalTo(JcabiLoggers.class.getName())
        );
    }

}
