/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2026 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/**
 * Service provider.
 *
 * <p>The class is thread-safe.
 *
 * @since 0.12
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

    @Override
    public void initialize() {
        // nothing here
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return StaticLoggerBinder.getSingleton().getLoggerFactory();
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

}
