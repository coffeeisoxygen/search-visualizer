package com.coffeecode.service.metrics;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceMetrics {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceMetrics.class);
    private final AtomicLong searchCount = new AtomicLong();
    private final AtomicLong totalSearchTime = new AtomicLong();

    public void recordSearch(long duration) {
        searchCount.incrementAndGet();
        totalSearchTime.addAndGet(duration);
        logMetrics();
    }

    private void logMetrics() {
        long count = searchCount.get();
        long totalTime = totalSearchTime.get();
        double avgTime = count > 0 ? (double) totalTime / count : 0;
        if (logger.isInfoEnabled()) {
            logger.info("Search metrics - Count: {}, Avg Time: {}ms", count, String.format("%.2f", avgTime));
        }
    }
}
