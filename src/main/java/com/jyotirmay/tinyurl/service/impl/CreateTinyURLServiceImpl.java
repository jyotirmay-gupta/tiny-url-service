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

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.entity.TinyURLEntity;
import com.jyotirmay.tinyurl.generator.TinyURLGenerator;
import com.jyotirmay.tinyurl.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.service.CreateTinyURLService;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CreateTinyURLServiceImpl implements CreateTinyURLService {

    private static final Logger LOGGER = LogManager.getLogger(CreateTinyURLServiceImpl.class);

    private final TinyURLRepository tinyURLRepository;

    private final TinyURLGenerator tinyURLGenerator;

    public CreateTinyURLServiceImpl(TinyURLRepository tinyURLRepository, TinyURLGenerator tinyURLGenerator) {
        this.tinyURLRepository = tinyURLRepository;
        this.tinyURLGenerator = tinyURLGenerator;
    }

    @Override
    public TinyURLResponseTO createTinyURL(@NotNull CreateTinyURLRequestTO createTinyURLRequestTO) {

        TinyURLEntity tinyURLEntity = new TinyURLEntity();
        tinyURLEntity.setOriginalUrl(createTinyURLRequestTO.originalURL());
        tinyURLEntity.setTinyUrl(tinyURLGenerator.generateNextTinyURL());
        tinyURLEntity.setActive(true);

        Instant expirationDate = Instant.now()
                .atZone(ZoneOffset.UTC)
                .plusMonths(6L)
                .toInstant();

        tinyURLEntity.setExpirationDate(expirationDate);
        tinyURLEntity.setCreatedOn(OffsetDateTime.now());
        tinyURLRepository.save(tinyURLEntity);

        LOGGER.info("Tiny URL {} persisted with id: {}", tinyURLEntity.getTinyUrl(), tinyURLEntity.getTinyUrlId());

        return new TinyURLResponseTO(tinyURLEntity.getOriginalUrl(),
                tinyURLEntity.getTinyUrl(),
                tinyURLEntity.isActive(),
                tinyURLEntity.getExpirationDate(),
                null);
    }
}
