/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2026 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.logging.Log;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Regression tests for parameterized messages and their causes.
 *
 * @since 1.0
 */
final class Slf4jExceptionsTest {

    @Test
    void preservesCausesInFixedArityCalls() throws ReflectiveOperationException {
        final Throwable cause = new IllegalStateException("broken settings");
        for (final String level : new String[]{"trace", "debug", "info", "warn", "error"}) {
            final List<Object[]> calls = new ArrayList<>(1);
            final Slf4jAdapter logger = Slf4jExceptionsTest.logger(calls);
            Slf4jAdapter.class.getMethod(level, String.class, Object.class, Object.class)
                .invoke(logger, "Cannot open {}", "settings.xml", cause);
            Slf4jExceptionsTest.assertCall(calls, "Cannot open settings.xml", cause);
        }
    }

    @Test
    void preservesCausesPassedAsObjects() throws ReflectiveOperationException {
        final Throwable cause = new IllegalArgumentException("bad configuration");
        for (final String level : new String[]{"trace", "debug", "info", "warn", "error"}) {
            final List<Object[]> calls = new ArrayList<>(1);
            Slf4jAdapter.class.getMethod(level, String.class, Object.class)
                .invoke(Slf4jExceptionsTest.logger(calls), "Cannot configure", cause);
            Slf4jExceptionsTest.assertCall(calls, "Cannot configure", cause);
        }
    }

    @Test
    void preservesCausesInVarargsCalls() throws ReflectiveOperationException {
        final Throwable cause = new IllegalStateException("missing file");
        for (final String level : new String[]{"trace", "debug", "info", "warn", "error"}) {
            final List<Object[]> calls = new ArrayList<>(1);
            Slf4jAdapter.class.getMethod(level, String.class, Object[].class).invoke(
                Slf4jExceptionsTest.logger(calls), "Cannot open {} in {}",
                new Object[]{"settings.xml", "config", cause}
            );
            Slf4jExceptionsTest.assertCall(calls, "Cannot open settings.xml in config", cause);
        }
    }

    @Test
    void formatsOrdinaryVarargs() throws ReflectiveOperationException {
        for (final String level : new String[]{"trace", "debug", "info", "warn", "error"}) {
            final List<Object[]> calls = new ArrayList<>(1);
            Slf4jAdapter.class.getMethod(level, String.class, Object[].class).invoke(
                Slf4jExceptionsTest.logger(calls), "1: {} 2: {} 3: {}",
                new Object[]{"a", "b", "c"}
            );
            MatcherAssert.assertThat(
                "All three placeholders must be formatted",
                calls.get(0)[0].toString(), Matchers.endsWith("1: a 2: b 3: c")
            );
        }
    }

    private static Slf4jAdapter logger(final List<Object[]> calls) {
        return new Slf4jAdapter(
            (Log) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(), new Class<?>[]{Log.class},
                (proxy, method, arguments) -> {
                    calls.add(arguments);
                    return null;
                }
            ), "com.jcabi.test"
        );
    }

    private static void assertCall(final List<Object[]> calls,
        final String message, final Throwable cause) {
        MatcherAssert.assertThat("One Maven log call is expected", calls, Matchers.hasSize(1));
        final Object[] arguments = calls.get(0);
        MatcherAssert.assertThat(
            "The formatted message must be preserved",
            arguments[0].toString(), Matchers.endsWith(message)
        );
        MatcherAssert.assertThat(
            "The Maven overload must accept a cause", arguments.length, Matchers.equalTo(2)
        );
        MatcherAssert.assertThat(
            "The original throwable must reach Maven", arguments[1], Matchers.sameInstance(cause)
        );
    }
}
