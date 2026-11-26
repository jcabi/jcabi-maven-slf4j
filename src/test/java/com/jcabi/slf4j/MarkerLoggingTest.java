/*
 * SPDX-FileCopyrightText: Copyright (c) 2012-2026 Yegor Bugayenko
 * SPDX-License-Identifier: MIT
 */
package com.jcabi.slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.MarkerFactory;

/**
 * Marker forwarding tests for {@link Slf4jAdapter}.
 *
 * @since 0.1.6
 */
final class MarkerLoggingTest {

    @Test
    void forwardsFormattedMarkerMessageAndCause() {
        final AtomicReference<List<Object>> event = new AtomicReference<>();
        final Slf4jAdapter logger = new Slf4jAdapter(
            new SystemStreamLog() {
                @Override
                public void warn(final CharSequence content, final Throwable error) {
                    event.set(Arrays.asList(content, error));
                }
            },
            "example"
        );
        final Throwable problem = new IllegalStateException("broken");
        logger.warn(MarkerFactory.getMarker("AUDIT"), "Update {}", "failed", problem);
        MatcherAssert.assertThat(event.get(), Matchers.contains("Update failed", problem));
    }
}
