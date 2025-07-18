package com.redashwood.tinyurl.service.impl;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.dto.UpdateOriginalURLRequestTO;
import com.redashwood.tinyurl.entity.TinyURLEntity;
import com.redashwood.tinyurl.exception.TinyURLNotFoundException;
import com.redashwood.tinyurl.repository.TinyURLRepository;
import com.redashwood.tinyurl.service.UpdateOriginalURLService;
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
