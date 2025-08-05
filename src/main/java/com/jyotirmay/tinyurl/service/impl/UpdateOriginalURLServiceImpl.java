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
package com.jyotirmay.tinyurl.service.impl;

import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateOriginalURLRequestTO;
import com.jyotirmay.tinyurl.entity.TinyURLEntity;
import com.jyotirmay.tinyurl.exception.TinyURLNotFoundException;
import com.jyotirmay.tinyurl.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.service.UpdateOriginalURLService;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.OffsetDateTime;

public class UpdateOriginalURLServiceImpl implements UpdateOriginalURLService {

    private static final Logger LOGGER = LogManager.getLogger(UpdateOriginalURLServiceImpl.class);

    private final TinyURLRepository tinyURLRepository;

    public UpdateOriginalURLServiceImpl(TinyURLRepository tinyURLRepository) {
        this.tinyURLRepository = tinyURLRepository;
    }

    @Override
    public TinyURLResponseTO updateOriginalURL(@NotNull UpdateOriginalURLRequestTO updateOriginalURLRequestTO) {

        TinyURLEntity tinyURLEntity = tinyURLRepository.findByTinyUrlAndActive(updateOriginalURLRequestTO.tinyURL(), true)
                .orElseThrow(() -> new TinyURLNotFoundException("ERR404", "Tiny URL %s not found.", updateOriginalURLRequestTO.tinyURL()));

        LOGGER.info("Found 1 active record with tiny url:{}", tinyURLEntity.getTinyUrl());

        tinyURLEntity.setOriginalUrl(updateOriginalURLRequestTO.originalURL());
        tinyURLEntity.setUpdatedOn(OffsetDateTime.now());
        tinyURLRepository.save(tinyURLEntity);

        LOGGER.info("Tiny URL {} updated with original URL as {}", tinyURLEntity.getTinyUrl(),
                tinyURLEntity.getOriginalUrl());

        return new TinyURLResponseTO(tinyURLEntity.getOriginalUrl(),
                tinyURLEntity.getTinyUrl(),
                tinyURLEntity.isActive(),
                tinyURLEntity.getExpirationDate(),
                null);
    }
}
