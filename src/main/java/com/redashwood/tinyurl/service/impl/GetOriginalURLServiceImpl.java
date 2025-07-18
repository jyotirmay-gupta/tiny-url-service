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
package com.redashwood.tinyurl.service.impl;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.entity.TinyURLEntity;
import com.redashwood.tinyurl.exception.TinyURLNotFoundException;
import com.redashwood.tinyurl.repository.TinyURLRepository;
import com.redashwood.tinyurl.service.GetOriginalURLService;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetOriginalURLServiceImpl implements GetOriginalURLService {

    private static final Logger LOGGER = LogManager.getLogger(GetOriginalURLServiceImpl.class);

    private final TinyURLRepository tinyURLRepository;

    public GetOriginalURLServiceImpl(TinyURLRepository tinyURLRepository) {
        this.tinyURLRepository = tinyURLRepository;
    }

    @Override
    public TinyURLResponseTO getOriginalURL(@NotNull String tinyURL) {

        TinyURLEntity tinyURLEntity = tinyURLRepository.findByTinyUrlAndActive(tinyURL, true)
                .orElseThrow(() -> new TinyURLNotFoundException("ERR404", "Tiny URL %s not found.", tinyURL));

        LOGGER.info("Found 1 active record with tiny url:{}", tinyURL);

        return new TinyURLResponseTO(tinyURLEntity.getOriginalUrl(),
                tinyURLEntity.getTinyUrl(),
                tinyURLEntity.isActive(),
                tinyURLEntity.getExpirationDate(),
                null);
    }
}
