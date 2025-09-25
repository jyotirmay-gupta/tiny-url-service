package com.jyotirmay.tinyurl.util;

import com.jyotirmay.tinyurl.repository.TinyURLRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.concurrent.atomic.AtomicLong;

public class CounterBasedURLShortener implements URLShortener {

    private static final Logger LOGGER = LogManager.getLogger(URLShortener.class);

    private final AtomicLong counter = new AtomicLong();

    private final TinyURLRepository tinyUrlRepository;

    private volatile boolean initialized = false;

    public CounterBasedURLShortener(@Lazy TinyURLRepository tinyUrlRepository) {
        this.tinyUrlRepository = tinyUrlRepository;
    }

    private synchronized void initializeCounterIfNeeded() {
        if (!initialized) {
            long lastId = tinyUrlRepository.findMaxTinyUrlId();
            counter.set(lastId + 1);
            initialized = true;
        }
    }

    public String shortenUrl(String originalUrl) {
        initializeCounterIfNeeded();
        long id = counter.getAndIncrement();
        String shortCode = Base62Encoder.encode(id);
        LOGGER.info("The short-url generated for original-url: {} is {}", originalUrl, shortCode);
        return shortCode;
    }
}
