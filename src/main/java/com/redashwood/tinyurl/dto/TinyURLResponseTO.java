package com.redashwood.tinyurl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TinyURLResponseTO(

        String originalURL,

        String tinyURL,

        Boolean active,

        Instant expirationDate,

        ErrorTO error

) {
}
