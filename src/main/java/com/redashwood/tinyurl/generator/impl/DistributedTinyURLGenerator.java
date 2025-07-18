package com.redashwood.tinyurl.generator.impl;

import com.redashwood.tinyurl.generator.TinyURLGenerator;
import com.redashwood.tinyurl.utils.Base62Encoder;

import java.util.concurrent.ThreadLocalRandom;

public class DistributedTinyURLGenerator implements TinyURLGenerator {

    @Override
    public String generateNextTinyURL() {
        long timestamp = System.currentTimeMillis();
        int entropy = ThreadLocalRandom.current().nextInt(1000, 9999);
        long uniqueId = Long.parseLong(timestamp + "" + entropy);
        return Base62Encoder.encode(uniqueId);
    }
}
