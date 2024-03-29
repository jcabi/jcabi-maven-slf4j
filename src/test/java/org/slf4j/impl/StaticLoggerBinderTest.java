/*
 * Copyright (c) 2012-2022, jcabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
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
public final class StaticLoggerBinderTest {

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
    public void createsLoggerFactoryOfCustomClass() {
        MatcherAssert.assertThat(
            StaticLoggerBinder.getSingleton().getLoggerFactory(),
            Matchers.instanceOf(JcabiLoggers.class)
        );
    }

    @Test
    public void retrievesLoggerFactoryString() {
        MatcherAssert.assertThat(
            StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr(),
            Matchers.equalTo(JcabiLoggers.class.getName())
        );
    }

}
