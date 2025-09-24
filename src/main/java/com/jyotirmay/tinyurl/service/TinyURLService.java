package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateTinyURLRequestTO;

public interface TinyURLService {

    TinyURLResponseTO createTinyURL(CreateTinyURLRequestTO createTinyURLRequestTO);

    TinyURLResponseTO getOriginalURL(String tinyURL);

    TinyURLResponseTO updateTinyURL(UpdateTinyURLRequestTO updateTinyURLRequestTO);

    TinyURLResponseTO deleteTinyURL(String tinyURL);
}
