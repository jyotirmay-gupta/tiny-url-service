package com.redashwood.tinyurl.service.impl;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.entity.TinyURLEntity;
import com.redashwood.tinyurl.exception.TinyURLNotFoundException;
import com.redashwood.tinyurl.repository.TinyURLRepository;
import com.redashwood.tinyurl.service.DeleteTinyURLService;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.OffsetDateTime;

public class DeleteTinyURLServiceImpl implements DeleteTinyURLService {

    private static  final Logger LOGGER = LogManager.getLogger(DeleteTinyURLServiceImpl.class);

    private final TinyURLRepository tinyURLRepository;

    public DeleteTinyURLServiceImpl(TinyURLRepository tinyURLRepository) {
        this.tinyURLRepository = tinyURLRepository;
    }

    @Override
    public TinyURLResponseTO deleteTinyURL(@NotNull String tinyURL) {

        TinyURLEntity tinyURLEntity = tinyURLRepository.findByTinyUrlAndActive(tinyURL, true)
                .orElseThrow(() -> new TinyURLNotFoundException("ERR404", "Tiny URL %s not found.", tinyURL));

        LOGGER.info("Found 1 active record with tiny url:{}", tinyURL);

        tinyURLEntity.setActive(false);
        tinyURLEntity.setUpdatedOn(OffsetDateTime.now());
        tinyURLRepository.save(tinyURLEntity);

        LOGGER.info("Tiny URL {} deactivated with id: {}", tinyURLEntity.getTinyUrl(), tinyURLEntity.getTinyUrlId());

        return new TinyURLResponseTO(tinyURLEntity.getOriginalUrl(),
                tinyURLEntity.getTinyUrl(),
                tinyURLEntity.isActive(),
                tinyURLEntity.getExpirationDate(),
                null);
    }
}
