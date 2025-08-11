package com.jyotirmay.tinyurl.service.impl;

import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.entity.TinyURLEntity;
import com.jyotirmay.tinyurl.entity.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.exception.TinyURLNotFoundException;
import com.jyotirmay.tinyurl.service.GetOriginalURLService;
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
