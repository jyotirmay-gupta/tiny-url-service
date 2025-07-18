package com.redashwood.tinyurl.service.impl;

import com.redashwood.tinyurl.dto.CreateTinyURLRequestTO;
import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.entity.TinyURLEntity;
import com.redashwood.tinyurl.generator.TinyURLGenerator;
import com.redashwood.tinyurl.repository.TinyURLRepository;
import com.redashwood.tinyurl.service.CreateTinyURLService;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

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
        tinyURLEntity.setExpirationDate(Instant.now().plus(6L, ChronoUnit.MONTHS));
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
