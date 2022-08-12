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
package com.jcabi.slf4j;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.maven.plugin.logging.Log;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/**
 * Service provider.
 *
 * <p>The class is thread-safe.
 *
 * @since 0.1.6
 */
@ToString
@EqualsAndHashCode
public final class MavenSlf4j implements SLF4JServiceProvider {

    /**
     * Declare the version of the SLF4J API this implementation is compiled
     * against. The value of this field is usually modified with each release.
     */
    @SuppressWarnings("PMD.LongVariable")
    public static final String REQUESTED_API_VERSION = "2.0.99";

    /**
     * The {@link ILoggerFactory} instance returned by the
     * {@link #getLoggerFactory()} method should always be
     * the same object.
     */
    private static final JcabiLoggers LOGGERS = new JcabiLoggers();

    @Override
    public void initialize() {
        // nothing here
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return MavenSlf4j.LOGGERS;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return new BasicMarkerFactory();
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return new BasicMDCAdapter();
    }

    @Override
    public String getRequestedApiVersion() {
        return MavenSlf4j.REQUESTED_API_VERSION;
    }

    /**
     * Set Maven Log.
     * @param log The log from Maven plugin
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void setMavenLog(final Log log) {
        MavenSlf4j.LOGGERS.setMavenLog(log);
    }

}
