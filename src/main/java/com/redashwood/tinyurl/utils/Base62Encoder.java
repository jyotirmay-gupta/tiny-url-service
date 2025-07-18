package com.redashwood.tinyurl.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Base62Encoder(){

    }

    public static String encode(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
        if (value == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % 62)));
            value /= 62;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        int entropy = ThreadLocalRandom.current().nextInt(1000, 9999);
        long uniqueId = Long.parseLong(timestamp + "" + entropy);

        System.out.println(Base62Encoder.encode(uniqueId));
    }
}
