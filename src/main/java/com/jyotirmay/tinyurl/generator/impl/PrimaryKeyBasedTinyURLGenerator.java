package com.jyotirmay.tinyurl.generator.impl;

import com.jyotirmay.tinyurl.entity.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.generator.TinyURLGenerator;
import com.jyotirmay.tinyurl.utils.Base62Encoder;

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
