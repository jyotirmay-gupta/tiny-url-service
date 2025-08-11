/*
 *
 *  * Copyright (c) 2025 Jyotirmay Gupta
 *  *
 *  * Project: Tiny URL
 *  * Description: This is a personal project by Jyotirmay Gupta that implements a
 *  * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 *  * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *  *
 *  * This code is intended for educational and personal use, demonstrating core backend
 *  * concepts such as encoding algorithms, database storage, and URL mapping.
 *  *
 *  * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 *
 *
 */

package com.jyotirmay.tinyurl.dto;

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
