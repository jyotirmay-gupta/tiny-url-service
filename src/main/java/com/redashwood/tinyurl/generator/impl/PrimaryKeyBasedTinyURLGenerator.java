/*
 * Copyright (c) 2025 Jyotirmay Gupta
 *
 * Project: Tiny URL
 * Description: This is a personal project by Jyotirmay Gupta that implements a
 * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *
 * This code is intended for educational and personal use, demonstrating core backend
 * concepts such as encoding algorithms, database storage, and URL mapping.
 *
 * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 */
package com.redashwood.tinyurl.generator.impl;

import com.redashwood.tinyurl.generator.TinyURLGenerator;
import com.redashwood.tinyurl.repository.TinyURLRepository;
import com.redashwood.tinyurl.utils.Base62Encoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

public class PrimaryKeyBasedTinyURLGenerator implements TinyURLGenerator {

    private final TinyURLRepository tinyURLRepository;

    private final AtomicLong counter = new AtomicLong();

    public PrimaryKeyBasedTinyURLGenerator(TinyURLRepository tinyURLRepository){
        this.tinyURLRepository = tinyURLRepository;
    }

    @Override
    public synchronized String generateNextTinyURL() {
        long lastId = tinyURLRepository.findMaxTinyUrlId();
        counter.set(lastId + 1);
        long id = counter.get();
        return Base62Encoder.encode(id);
    }
}
