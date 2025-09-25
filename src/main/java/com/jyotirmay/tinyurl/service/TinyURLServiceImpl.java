package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateTinyURLRequestTO;
import com.jyotirmay.tinyurl.entity.TinyURLEntity;
import com.jyotirmay.tinyurl.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.util.URLShortener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TinyURLServiceImpl implements TinyURLService {

    private static final Logger LOGGER = LogManager.getLogger(TinyURLServiceImpl.class);

    private TinyURLRepository tinyURLRepository;

    private URLShortener urlShortener;

    public TinyURLServiceImpl(TinyURLRepository tinyURLRepository, URLShortener urlShortener) {
        this.tinyURLRepository = tinyURLRepository;
        this.urlShortener = urlShortener;
    }

    @Override
    public TinyURLResponseTO createTinyURL(CreateTinyURLRequestTO createTinyURLRequestTO) {
        TinyURLEntity tinyUrlEntity = new TinyURLEntity();
        String tinyUrl = urlShortener.shortenUrl(createTinyURLRequestTO.originalUrl());
        Instant expirationDate = Instant.now().plus(6, ChronoUnit.MONTHS);
        tinyUrlEntity.setOriginalUrl(createTinyURLRequestTO.originalUrl());
        tinyUrlEntity.setTinyUrl(tinyUrl);
        tinyUrlEntity.setExpirationDate(expirationDate);
        tinyURLRepository.save(tinyUrlEntity);

        return new TinyURLResponseTO(tinyUrlEntity.getOriginalUrl(),
                tinyUrlEntity.getTinyUrl(),
                tinyUrlEntity.isActive(),
                tinyUrlEntity.getExpirationDate(),
                null);
    }

    @Override
    public TinyURLResponseTO getOriginalURL(String tinyURL) {
        return null;
    }

    @Override
    public TinyURLResponseTO updateTinyURL(UpdateTinyURLRequestTO updateTinyURLRequestTO) {
        return null;
    }

    @Override
    public TinyURLResponseTO deleteTinyURL(String tinyURL) {
        return null;
    }
}
