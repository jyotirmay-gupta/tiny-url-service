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
