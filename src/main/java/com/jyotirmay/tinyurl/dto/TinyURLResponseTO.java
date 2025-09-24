package com.jyotirmay.tinyurl.dto;

import java.time.Instant;

public record TinyURLResponseTO(
        String originalUrl,

        String tinyUrl,

        Boolean active,

        Instant expirationDate,

        ErrorTO error
) {
}
